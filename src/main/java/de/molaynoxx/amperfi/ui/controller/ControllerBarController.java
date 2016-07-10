package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.player.PlayerAPI;
import de.molaynoxx.amperfi.ui.controls.ControllerBar;
import de.molaynoxx.amperfi.ui.helper.TimeStringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ControllerBarController extends AbstractController<ControllerBar> {

    public final EventHandler<? super MouseEvent> playPauseClickedHandler = new PlayPauseClickedHandler();
    public final EventHandler<? super MouseEvent> settingsClickHandler = new SettingsClickHandler();
    public final EventHandler<? super MouseEvent> prevClickedHandler = new PrevClickHandler();
    public final EventHandler<? super MouseEvent> nextClickedHandler = new NextClickHandler();

    public ControllerBarController(ControllerBar control) {
        super(control);
        Amperfi.playbackController.statusProperty().addListener(new PlaybackStatusChangedHandler());
        Amperfi.playbackController.positionProperty().addListener(new PositionChangeListener());
        Amperfi.playbackController.lengthProperty().addListener(new LengthChangeListener());
    }

    private class PlaybackStatusChangedHandler implements ChangeListener<PlayerAPI.PlayerStatus> {

        private final Image play = new Image(Amperfi.class.getResourceAsStream("/icn/icnPlay.png"));
        private final Image pause = new Image(Amperfi.class.getResourceAsStream("/icn/icnPause.png"));

        @Override
        public void changed(ObservableValue<? extends PlayerAPI.PlayerStatus> observable, PlayerAPI.PlayerStatus oldValue, PlayerAPI.PlayerStatus newValue) {
            if (newValue == PlayerAPI.PlayerStatus.STOPPED || newValue == PlayerAPI.PlayerStatus.PAUSED) {
                control.playPauseProperty.set(play);
            } else {
                control.playPauseProperty.set(pause);
            }
        }

    }

    private class PlayPauseClickedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (Amperfi.playbackController.getStatus() == PlayerAPI.PlayerStatus.STOPPED || Amperfi.playbackController.getStatus() == PlayerAPI.PlayerStatus.PAUSED) {
                Amperfi.playbackController.play();
            } else {
                Amperfi.playbackController.pause();
            }

        }

    }

    private class PositionChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            control.currentTimeProperty.set(TimeStringUtils.millisToString(newValue.longValue()));
            control.pbTime.controller.setValue(Amperfi.playbackController.getPosition() / (double) Amperfi.playbackController.getLength());
        }

    }

    private class LengthChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            control.lengthProperty.set(TimeStringUtils.millisToString(newValue.longValue()));
        }

    }

    private class SettingsClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (Amperfi.ui.activeView.get() == Amperfi.ui.settings) {
                Amperfi.ui.showView(Amperfi.ui.libraryView);
            } else {
                Amperfi.ui.showView(Amperfi.ui.settings);
            }
        }

    }

    private class PrevClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Amperfi.playbackController.previous();
        }

    }

    private class NextClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Amperfi.playbackController.next();
        }

    }
}

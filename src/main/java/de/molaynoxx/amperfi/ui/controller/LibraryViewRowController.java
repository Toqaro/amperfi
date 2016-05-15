package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controls.row.LibraryViewRow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class LibraryViewRowController extends AbstractController<LibraryViewRow> {

    private static PseudoClass playing = PseudoClass.getPseudoClass("playing");

    public final EventHandler<? super MouseEvent> mouseClickHandler = new MouseClickHandler();

    public LibraryViewRowController(LibraryViewRow control) {
        super(control);

        Amperfi.playbackController.currentTitleProperty().addListener(new TitleChangeListener());
    }

    public void handleUpdate() {
        if (control.getItem() == null || Amperfi.playbackController.getCurrentTitle() == null) return;
        control.pseudoClassStateChanged(playing, control.getItem().getFileId() == Amperfi.playbackController.getCurrentTitle().getFileId() && Amperfi.ui.libraryView.controller.getCurrentPlaylist() == Amperfi.playbackController.getCurrentlyPlaying());
    }

    private class TitleChangeListener implements ChangeListener<LibraryFile> {

        @Override
        public void changed(ObservableValue<? extends LibraryFile> observable, LibraryFile oldValue, LibraryFile newValue) {
            if (control.getItem() == null) return;
            control.pseudoClassStateChanged(playing, control.getItem().getFileId() == newValue.getFileId() && Amperfi.ui.libraryView.controller.getCurrentPlaylist() == Amperfi.playbackController.getCurrentlyPlaying());
        }

    }

    private class MouseClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Amperfi.playbackController.playPlaylist(Amperfi.ui.libraryView.controller.getCurrentPlaylist(), control.getItem());
            }
        }

    }

}

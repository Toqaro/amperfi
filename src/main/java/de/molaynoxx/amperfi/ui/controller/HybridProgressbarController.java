package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controls.HybridProgressbar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class HybridProgressbarController extends AbstractController<HybridProgressbar> {

    public final ChangeListener<Number> sliderValueChanged = new SliderValueChangedListener();
    public final EventHandler<MouseEvent> onMouseReleased = new MouseReleasedHandler();
    public final EventHandler<MouseEvent> onMouseClicked = new MouseClickedHandler();

    private double lastValue = 0;

    public HybridProgressbarController(HybridProgressbar control) {
        super(control);
    }


    private class SliderValueChangedListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            lastValue = newValue.doubleValue();
        }

    }

    private class MouseClickedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.isStillSincePress()) {
                double pos = event.getX() / control.slider.getWidth();
                Amperfi.playbackController.jumpToPercent(pos);
            }
        }

    }

    private class MouseReleasedHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (!event.isStillSincePress()) {
                setValue(lastValue);
                Amperfi.playbackController.jumpToPercent(lastValue);
            }
        }

    }

    /**
     * Sets the value of both the Slider & ProgressBar
     *
     * @param v Double value between 0 and 1
     */
    public void setValue(double v) {
        if (!control.slider.isValueChanging() && !control.slider.isPressed()) {
            control.slider.setValue(v);
            control.progressBar.setProgress(v);
        }
    }

}

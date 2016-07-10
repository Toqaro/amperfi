package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.ui.controller.HybridProgressbarController;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class HybridProgressbar extends StackPane {

    public final ProgressBar progressBar;
    public final Slider slider;

    public final HybridProgressbarController controller;

    public HybridProgressbar() {
        progressBar = new ProgressBar();
        slider = new Slider();

        controller = new HybridProgressbarController(this);

        slider.setMin(0);
        slider.setMax(1);

        progressBar.getStyleClass().add("hybrid-pb-progress");
        slider.getStyleClass().add("hybrid-pb-slider");

        progressBar.prefWidthProperty().bind(this.widthProperty());
        progressBar.prefHeightProperty().bind(this.heightProperty().divide(10));
        slider.prefWidthProperty().bind(this.widthProperty());
        slider.prefHeightProperty().bind(this.heightProperty().divide(10));

        slider.valueProperty().addListener((observeable, oldValue, newValue) -> {
            double progress = newValue.doubleValue();
            progressBar.setProgress(progress);
        });

        slider.valueProperty().addListener(controller.sliderValueChanged);
        slider.setOnMouseReleased(controller.onMouseReleased);
        slider.setOnMouseClicked(controller.onMouseClicked);

        this.getChildren().add(progressBar);
        this.getChildren().add(slider);
    }

}

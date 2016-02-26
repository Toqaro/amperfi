package de.molaynoxx.ammp.ui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AMMPScene extends Scene {

    private final StackPane root;

    public AMMPScene() {
        super(new StackPane());
        root = (StackPane) this.getRoot();

        this.getStylesheets().add("dark.css");

        VBox verticalWrapper = new VBox();
        root.getChildren().add(verticalWrapper);

        HBox horizontalWrapper = new HBox();
        horizontalWrapper.setStyle("-fx-background-color: FF0000;");
        verticalWrapper.getChildren().add(horizontalWrapper);

        Sidebar sidebar = new Sidebar();
        horizontalWrapper.getChildren().add(sidebar);

        ControllerBar controllerBar = new ControllerBar();
        verticalWrapper.getChildren().add(controllerBar);

        // Make sure horizontalWrapper covers all the space above the ControllerBar
        horizontalWrapper.prefHeightProperty().bind(root.heightProperty().subtract(controllerBar.heightProperty()));
    }

}

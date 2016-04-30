package de.molaynoxx.amperfi.ui;

import de.molaynoxx.amperfi.ui.view.Mp3Import;
import de.molaynoxx.amperfi.ui.view.Mp3ImportProgress;
import de.molaynoxx.amperfi.ui.view.SettingsOverview;
import de.molaynoxx.amperfi.ui.view.Viewable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AmperfiScene extends Scene {

    private final StackPane root;
    private final StackPane centerPane;

    public final ControllerBar controllerBar;
    public final Sidebar sidebar;

    public final SettingsOverview settings;
    public final Mp3Import mp3Import;
    public final Mp3ImportProgress mp3ImportProgress;

    public AmperfiScene() {
        super(new StackPane());
        root = (StackPane) this.getRoot();

        this.getStylesheets().add("dark.css");

        VBox verticalWrapper = new VBox();
        root.getChildren().add(verticalWrapper);

        HBox horizontalWrapper = new HBox();
        horizontalWrapper.getStyleClass().add("center-pane");
        verticalWrapper.getChildren().add(horizontalWrapper);

        sidebar = new Sidebar();
        horizontalWrapper.getChildren().add(sidebar);

        controllerBar = new ControllerBar();
        verticalWrapper.getChildren().add(controllerBar);

        // Make sure horizontalWrapper covers all the space above the ControllerBar
        horizontalWrapper.prefHeightProperty().bind(root.heightProperty().subtract(controllerBar.heightProperty()));

        centerPane = new StackPane();
        horizontalWrapper.getChildren().add(centerPane);
        centerPane.prefWidthProperty().bind(horizontalWrapper.widthProperty().subtract(sidebar.widthProperty()));
        centerPane.prefHeightProperty().bind(horizontalWrapper.heightProperty());

        settings = new SettingsOverview();
        settings.setVisible(false);
        centerPane.getChildren().add(settings);

        mp3Import = new Mp3Import();
        mp3Import.setVisible(false);
        centerPane.getChildren().add(mp3Import);

        mp3ImportProgress = new Mp3ImportProgress();
        mp3ImportProgress.setVisible(false);
        centerPane.getChildren().add(mp3ImportProgress);
    }

    public void showView(Viewable control) {
        for(Node n : centerPane.getChildren()) {
            n.setVisible(n == control);
        }
    }

    public void lockView() {
        sidebar.setDisable(true);
        controllerBar.setDisable(true);
    }

    public void unlockView() {
        sidebar.setDisable(false);
        controllerBar.setDisable(false);
    }

}
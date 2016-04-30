package de.molaynoxx.amperfi.ui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class Mp3ImportProgress extends VBox implements Viewable {

    public final ProgressBar pbImporting;
    public final Label lblPath;
    public final Button btnCancel;

    public Mp3ImportProgress() {
        this.getStyleClass().add("import-progress");

        setAlignment(Pos.CENTER);
        setSpacing(35);

        Label lblTitle = new Label("Importing mp3s");
        lblTitle.getStyleClass().add("settings-page-title");

        pbImporting = new ProgressBar();
        pbImporting.setProgress(0.5);
        pbImporting.maxWidthProperty().bind(this.widthProperty().multiply(.8));

        lblPath = new Label();
        lblPath.getStyleClass().add("settings-page-title2");

        btnCancel = new Button("Cancel");
        btnCancel.getStyleClass().add("btn-cancel");

        this.getChildren().addAll(lblTitle, pbImporting, lblPath, btnCancel);
    }

}

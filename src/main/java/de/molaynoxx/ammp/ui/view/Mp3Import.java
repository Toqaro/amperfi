package de.molaynoxx.ammp.ui.view;

import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.ui.action.Mp3ImportController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

public class Mp3Import extends VBox implements Viewable {

    public Mp3Import() {
        Mp3ImportController controller = new Mp3ImportController();

        getStyleClass().add("mp3-import");
        setAlignment(Pos.CENTER);

        Label lblTitle = new Label("Import mp3s");
        lblTitle.getStyleClass().add("settings-page-title");

        Label lblHelp = new Label("Here you can add folders to your library and thereby make their contents available for playback.");
        lblHelp.getStyleClass().add("settings-page-title2");

        this.getChildren().addAll(lblTitle, lblHelp);
        VBox.setMargin(lblTitle, new Insets(10, 0, 10, 0));
        VBox.setMargin(lblHelp, new Insets(0, 0, 25, 0));

        ListView<String> lvFolders = new ListView<>();
        lvFolders.getItems().addAll(AMMP.db.getFolders());
        lvFolders.maxWidthProperty().bind(lblHelp.widthProperty());

        this.getChildren().add(lvFolders);
        VBox.setMargin(lvFolders, new Insets(0, 0, 15, 0));

        GridPane gpButtons = new GridPane();
        gpButtons.maxWidthProperty().bind(lblHelp.widthProperty());
        gpButtons.minWidthProperty().bind(lblHelp.widthProperty());
        gpButtons.setHgap(30);

        Button btnAdd = new Button("Add");
        btnAdd.setMaxWidth(Double.MAX_VALUE);
        btnAdd.getStyleClass().add("mp3-import-button");
        btnAdd.setOnAction(controller.addFolderHandler);

        Button btnRemove = new Button("Remove");
        btnRemove.setMaxWidth(Double.MAX_VALUE);
        btnRemove.getStyleClass().add("mp3-import-button");

        Button btnSynchronize = new Button("Synchronize");
        btnSynchronize.setMaxWidth(Double.MAX_VALUE);
        btnSynchronize.getStyleClass().add("mp3-import-button");

        gpButtons.add(btnAdd, 0, 0);
        gpButtons.add(btnRemove, 1, 0);
        gpButtons.add(btnSynchronize, 2, 0);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        gpButtons.getColumnConstraints().add(cc);
        gpButtons.getColumnConstraints().add(cc);
        gpButtons.getColumnConstraints().add(cc);

        this.getChildren().add(gpButtons);
    }

}

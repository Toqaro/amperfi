package de.molaynoxx.ammp.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SettingsItem extends VBox {

    public SettingsItem(Image image, String text) {
        getStyleClass().add("settings-item");
        setSpacing(15);
        setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(image);
        iv.setFitWidth(86);
        iv.setFitHeight(86);
        Label lb = new Label(text);
        getChildren().addAll(iv, lb);
    }

}

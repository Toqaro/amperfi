package de.molaynoxx.ammp.ui;

import de.molaynoxx.ammp.AMMP;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControllerBar extends HBox {

    public ControllerBar() {
        super();
        this.getStyleClass().add("control-bar");

        setMinHeight(60);
        setMaxHeight(60);

        this.setAlignment(Pos.CENTER_LEFT);

        ImageView btnPrev = new ImageView(new Image(AMMP.class.getResourceAsStream("/icn/icnPrev.png")));
        btnPrev.setFitHeight(60);
        btnPrev.setFitWidth(60);
        btnPrev.getStyleClass().add("icn-button");
        ImageView btnPlayPause = new ImageView(new Image(AMMP.class.getResourceAsStream("/icn/icnPlay.png")));
        btnPlayPause.setFitHeight(35);
        btnPlayPause.setFitWidth(35);
        btnPlayPause.getStyleClass().add("icn-button");
        ImageView btnNext = new ImageView(new Image(AMMP.class.getResourceAsStream("/icn/icnNext.png")));
        btnNext.setFitHeight(60);
        btnNext.setFitWidth(60);
        btnNext.getStyleClass().add("icn-button");

        this.getChildren().addAll(btnPrev, btnPlayPause, btnNext);
        HBox.setMargin(btnPrev, new Insets(0, 22, 0, 50));
        HBox.setMargin(btnPlayPause, new Insets(0, 22, 0, 0));
    }
}

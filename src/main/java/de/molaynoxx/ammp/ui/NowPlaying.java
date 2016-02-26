package de.molaynoxx.ammp.ui;

import de.molaynoxx.ammp.AMMP;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class NowPlaying extends VBox {

    private final ImageView coverView;
    private final Label title;
    private final Label artist;

    public NowPlaying() {
        super();

        this.getStyleClass().add("now-playing");

        coverView = new ImageView();
        coverView.setFitHeight(200);
        coverView.setFitWidth(200);
        coverView.setImage(new Image(AMMP.class.getResourceAsStream("/NoCover.png")));
        getChildren().add(coverView);

        title = new Label("Generic Title");
        title.getStyleClass().add("title-label");
        artist = new Label("Generic Artist");
        artist.getStyleClass().add("artist-label");

        this.setAlignment(Pos.CENTER);

        getChildren().addAll(title, artist);
    }
}

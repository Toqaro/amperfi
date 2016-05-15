package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.NowPlayingController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class NowPlaying extends VBox {

    public final ImageView coverView;
    public final Label title;
    public final Label artist;

    public final NowPlayingController controller = new NowPlayingController(this);

    public NowPlaying() {
        super();

        this.getStyleClass().add("now-playing");

        coverView = new ImageView();
        coverView.setFitHeight(200);
        coverView.setFitWidth(200);
        coverView.setImage(new Image(Amperfi.class.getResourceAsStream("/NoCover.png")));
        getChildren().add(coverView);
        VBox.setMargin(coverView, new Insets(0, 0, 10, 0));

        title = new Label("Generic Title");
        title.getStyleClass().add("title-label");
        artist = new Label("Generic Artist");
        artist.getStyleClass().add("artist-label");

        this.setAlignment(Pos.CENTER);

        getChildren().addAll(title, artist);
    }
}

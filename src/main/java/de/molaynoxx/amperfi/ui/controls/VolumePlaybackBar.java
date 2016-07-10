package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.VolumePlaybackBarController;
import de.molaynoxx.amperfi.ui.controller.helper.VolumeBarExecutor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class VolumePlaybackBar extends HBox {

    public final VolumePlaybackBarController controller = new VolumePlaybackBarController(this);

    public VolumePlaybackBar() {
        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);
        getStyleClass().add("volume-pb-bar");

        ImageView btnLoop = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnLoop.png")));
        HBox.setMargin(btnLoop, new Insets(0, 0, 0, 5));
        btnLoop.setFitHeight(30);
        btnLoop.setFitWidth(30);
        btnLoop.getStyleClass().add("icn-button");
        btnLoop.setPickOnBounds(true);

        ImageView btnRepeat = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnRepeat.png")));
        btnRepeat.setFitHeight(30);
        btnRepeat.setFitWidth(30);
        btnRepeat.getStyleClass().add("icn-button");
        btnRepeat.setPickOnBounds(true);

        ImageView btnRandom = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnShuffle.png")));
        btnRandom.setFitHeight(30);
        btnRandom.setFitWidth(30);
        btnRandom.getStyleClass().add("icn-button");
        btnRandom.setPickOnBounds(true);

        ImageView icnVol = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnVol.png")));
        HBox.setMargin(icnVol, new Insets(0, 0, 0, 15));
        icnVol.setFitHeight(20);
        icnVol.setFitWidth(20);

        HybridProgressbar pbVolume = new HybridProgressbar();
        pbVolume.controller.setExecutor(new VolumeBarExecutor());
        pbVolume.controller.setValue(Amperfi.playbackController.getVolume());
        HBox.setMargin(pbVolume, new Insets(0, 10, 0, 0));
        pbVolume.getStyleClass().add("volume-bar");
        HBox.setHgrow(pbVolume, Priority.ALWAYS);

        this.getChildren().addAll(btnLoop, btnRepeat, btnRandom, icnVol, pbVolume);
    }

}

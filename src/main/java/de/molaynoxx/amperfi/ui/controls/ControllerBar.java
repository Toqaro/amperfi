package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.ControllerBarController;
import de.molaynoxx.amperfi.ui.controller.helper.TimeBarExecutor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ControllerBar extends HBox {

    public final ControllerBarController controller = new ControllerBarController(this);

    public final HybridProgressbar pbTime;
    public final StringProperty currentTimeProperty;
    public final StringProperty lengthProperty;
    public final ObjectProperty<Image> playPauseProperty;

    public ControllerBar() {
        super();
        this.getStyleClass().add("control-bar");

        setMinHeight(60);
        setMaxHeight(60);

        HBox hbControls = new HBox();
        hbControls.setMinHeight(60);
        hbControls.setMaxHeight(60);
        hbControls.setMinWidth(300);
        hbControls.setMaxWidth(300);
        hbControls.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().add(hbControls);

        this.setAlignment(Pos.CENTER_LEFT);

        ImageView btnPrev = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnPrev.png")));
        btnPrev.setFitHeight(60);
        btnPrev.setFitWidth(60);
        btnPrev.getStyleClass().add("icn-button");
        btnPrev.setOnMouseClicked(controller.prevClickedHandler);
        btnPrev.setPickOnBounds(true);

        ImageView btnPlayPause = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnPlay.png")));
        btnPlayPause.setFitHeight(35);
        btnPlayPause.setFitWidth(35);
        btnPlayPause.getStyleClass().add("icn-button");
        playPauseProperty = btnPlayPause.imageProperty();
        btnPlayPause.setOnMouseClicked(controller.playPauseClickedHandler);
        btnPlayPause.setPickOnBounds(true);

        ImageView btnNext = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnNext.png")));
        btnNext.setFitHeight(60);
        btnNext.setFitWidth(60);
        btnNext.getStyleClass().add("icn-button");
        btnNext.setOnMouseClicked(controller.nextClickedHandler);
        btnNext.setPickOnBounds(true);

        hbControls.getChildren().addAll(btnPrev, btnPlayPause, btnNext);
        HBox.setMargin(btnPrev, new Insets(0, 22, 0, 50));
        HBox.setMargin(btnPlayPause, new Insets(0, 22, 0, 0));

        Label lblCurrTime = new Label("0:00");
        lblCurrTime.setEllipsisString("");
        lblCurrTime.getStyleClass().add("lbl-time");
        lblCurrTime.setMinSize(Label.USE_COMPUTED_SIZE, Label.USE_COMPUTED_SIZE);
        currentTimeProperty = lblCurrTime.textProperty();

        Label lblEndTime = new Label("0:00");
        lblEndTime.setEllipsisString("");
        lblEndTime.getStyleClass().add("lbl-time");
        lblEndTime.setMinSize(Label.USE_COMPUTED_SIZE, Label.USE_COMPUTED_SIZE);
        lengthProperty = lblEndTime.textProperty();

        // Make pbTime grow with space available in ControllerBar (setHGrow did not work for whatever reason)
        HBox hbProgress = new HBox();
        hbProgress.setAlignment(Pos.CENTER_LEFT);

        pbTime = new HybridProgressbar();
        pbTime.controller.setExecutor(new TimeBarExecutor());
        pbTime.getStyleClass().add("time-bar");
        HBox.setMargin(pbTime, new Insets(0, 10, 0, 10));
        HBox.setHgrow(pbTime, Priority.ALWAYS);
        hbProgress.getChildren().add(pbTime);
        this.getChildren().addAll(lblCurrTime, hbProgress, lblEndTime);
        pbTime.prefWidthProperty().bind(hbProgress.widthProperty().subtract(20));

        HBox.setHgrow(hbProgress, Priority.ALWAYS);

        ImageView btnSettings = new ImageView(new Image(Amperfi.class.getResourceAsStream("/icn/icnSettings.png")));
        btnSettings.getStyleClass().add("btn-settings");
        btnSettings.setFitHeight(25);
        btnSettings.setFitWidth(25);
        HBox.setMargin(btnSettings, new Insets(0, 10, 0, 20));
        btnSettings.setPickOnBounds(true);
        btnSettings.setOnMouseClicked(controller.settingsClickHandler);
        this.getChildren().add(btnSettings);
    }

}

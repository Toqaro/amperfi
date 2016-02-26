package de.molaynoxx.ammp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    private final NowPlaying nowPlaying;

    public Sidebar() {
        super();

        setMinWidth(300);
        setMaxWidth(300);

        this.getStyleClass().add("sidebar-top");

        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Search...");
        tfSearch.getStyleClass().add("search-input");
        this.getChildren().add(tfSearch);
        VBox.setMargin(tfSearch, new Insets(20, 20, 20, 20));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMinWidth(300);
        scrollPane.setMaxWidth(300);
        scrollPane.getStyleClass().add("sidebar-scroll-pane");
        getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox sidebarContainer = new VBox();
        sidebarContainer.setPrefWidth(300);
        sidebarContainer.getStyleClass().add("sidebar");

        Accordion acc = new Accordion();

        TitledPane tpArtists = new TitledPane();
        tpArtists.setText("Artists");
        tpArtists.getStyleClass().add("titled-pane-sidebar");
        TitledPane tpAlbums = new TitledPane();
        tpAlbums.setText("Albums");
        tpAlbums.getStyleClass().add("titled-pane-sidebar");
        TitledPane tpGenres = new TitledPane();
        tpGenres.setText("Genres");
        tpGenres.getStyleClass().add("titled-pane-sidebar");

        acc.getPanes().addAll(tpArtists, tpAlbums, tpGenres);
        sidebarContainer.getChildren().add(acc);

        scrollPane.setContent(sidebarContainer);

        this.nowPlaying = new NowPlaying();
        getChildren().add(nowPlaying);
        VBox.setVgrow(nowPlaying, Priority.NEVER);

        // Make sure the upper half of the sidebar is always big enough so the NowPlaying Control is touching the ControlBar
        sidebarContainer.minHeightProperty().bind(this.heightProperty().subtract(nowPlaying.heightProperty()));
    }

}

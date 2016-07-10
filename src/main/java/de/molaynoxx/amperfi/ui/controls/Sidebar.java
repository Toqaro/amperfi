package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.player.playlist.Playlist;
import de.molaynoxx.amperfi.ui.controller.SidebarController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    public final NowPlaying nowPlaying;
    public final VolumePlaybackBar volumeBar;
    public final SidebarTreeView<Playlist> treeViewGenres;
    public final SidebarTreeView<Playlist> treeViewAlbums;
    public final SidebarTreeView<Playlist> treeViewArtists;
    public final Label lblLibrary;

    public final SidebarController controller = new SidebarController(this);

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

        lblLibrary = new Label("Library");
        lblLibrary.setPrefWidth(300);
        lblLibrary.setOnMousePressed(controller.displayLibraryHandler);
        lblLibrary.setOnMouseClicked(controller.playLibraryHandler);
        sidebarContainer.getChildren().add(lblLibrary);

        Accordion acc = new Accordion();

        TitledPane tpArtists = new TitledPane();
        tpArtists.setText("Artists");
        tpArtists.getStyleClass().add("titled-pane-sidebar");

        treeViewArtists = new SidebarTreeView<>();
        treeViewArtists.getSelectionModel().getSelectedItems().addListener(controller.displayArtistHandler);
        treeViewArtists.setOnMouseClicked(controller.playArtistHandler);
        tpArtists.setContent(treeViewArtists);

        TitledPane tpAlbums = new TitledPane();
        tpAlbums.setText("Albums");
        tpAlbums.getStyleClass().add("titled-pane-sidebar");

        treeViewAlbums = new SidebarTreeView<>();
        treeViewAlbums.getSelectionModel().getSelectedItems().addListener(controller.displayAlbumHandler);
        treeViewAlbums.setOnMouseClicked(controller.playAlbumHandler);
        tpAlbums.setContent(treeViewAlbums);

        TitledPane tpGenres = new TitledPane();
        tpGenres.setText("Genres");
        tpGenres.getStyleClass().add("titled-pane-sidebar");

        treeViewGenres = new SidebarTreeView<>();
        treeViewGenres.getSelectionModel().getSelectedItems().addListener(controller.displayGenreHandler);
        treeViewGenres.setOnMouseClicked(controller.playGenreHandler);
        tpGenres.setContent(treeViewGenres);

        acc.getPanes().addAll(tpArtists, tpAlbums, tpGenres);
        sidebarContainer.getChildren().add(acc);

        scrollPane.setContent(sidebarContainer);

        this.nowPlaying = new NowPlaying();
        getChildren().add(nowPlaying);
        VBox.setVgrow(nowPlaying, Priority.NEVER);

        this.volumeBar = new VolumePlaybackBar();
        getChildren().add(volumeBar);
        VBox.setVgrow(volumeBar, Priority.NEVER);

        // Make sure the upper half of the sidebar is always big enough so the NowPlaying Control is touching the ControlBar
        sidebarContainer.minHeightProperty().bind(this.heightProperty().subtract(nowPlaying.heightProperty()));

        controller.updateSidebar();
    }

}

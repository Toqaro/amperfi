package de.molaynoxx.amperfi.ui.controls;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.model.QLibraryFile;
import de.molaynoxx.amperfi.id3.ID3Helper;
import de.molaynoxx.amperfi.player.playlist.DatabasePlaylist;
import de.molaynoxx.amperfi.player.playlist.Playlist;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    private final NowPlaying nowPlaying;
    private final SidebarTreeView<Playlist> treeViewGenres;
    private final SidebarTreeView<Playlist> treeViewAlbums;
    private final SidebarTreeView<Playlist> treeViewArtists;

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

        treeViewArtists = new SidebarTreeView<>();
        tpArtists.setContent(treeViewArtists);

        TitledPane tpAlbums = new TitledPane();
        tpAlbums.setText("Albums");
        tpAlbums.getStyleClass().add("titled-pane-sidebar");

        treeViewAlbums = new SidebarTreeView<>();
        tpAlbums.setContent(treeViewAlbums);

        TitledPane tpGenres = new TitledPane();
        tpGenres.setText("Genres");
        tpGenres.getStyleClass().add("titled-pane-sidebar");

        treeViewGenres = new SidebarTreeView<>();
        tpGenres.setContent(treeViewGenres);

        acc.getPanes().addAll(tpArtists, tpAlbums, tpGenres);
        sidebarContainer.getChildren().add(acc);

        scrollPane.setContent(sidebarContainer);

        this.nowPlaying = new NowPlaying();
        getChildren().add(nowPlaying);
        VBox.setVgrow(nowPlaying, Priority.NEVER);

        // Make sure the upper half of the sidebar is always big enough so the NowPlaying Control is touching the ControlBar
        sidebarContainer.minHeightProperty().bind(this.heightProperty().subtract(nowPlaying.heightProperty()));

        updateSidebar();
    }

    public void updateSidebar() {
        treeViewArtists.getRoot().getChildren().clear();
        for (String artist : Amperfi.db.getTags(ID3Helper.ID3Tag.ARTIST)) {
            if(artist == null) continue;
            treeViewArtists.getRoot().getChildren().add(new TreeItem<>(new DatabasePlaylist(artist, QLibraryFile.LibraryFile.artist.like(artist))));
        }

        treeViewAlbums.getRoot().getChildren().clear();
        for (String album : Amperfi.db.getTags(ID3Helper.ID3Tag.ALBUM)) {
            if(album == null) continue;
            treeViewAlbums.getRoot().getChildren().add(new TreeItem<>(new DatabasePlaylist(album, QLibraryFile.LibraryFile.album.like(album))));
        }

        treeViewGenres.getRoot().getChildren().clear();
        for (String genre : Amperfi.db.getTags(ID3Helper.ID3Tag.GENRE)) {
            if(genre == null) continue;
            treeViewGenres.getRoot().getChildren().add(new TreeItem<>(new DatabasePlaylist(genre, QLibraryFile.LibraryFile.genre.like(genre))));
        }
    }

}

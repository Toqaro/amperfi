package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.player.playlist.Playlist;
import de.molaynoxx.amperfi.ui.view.LibraryView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LibraryViewController extends AbstractController<LibraryView> {

    private final ObjectProperty<Playlist> currentPlaylist = new SimpleObjectProperty<>();

    public LibraryViewController(LibraryView control) {
        super(control);
    }

    public void showPlaylist(Playlist p) {
        if (p == null) throw new IllegalArgumentException("Playlist object cannot be null.");
        control.getItems().setAll(p.getTracks());
        currentPlaylist.set(p);
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist.get();
    }

    public ObjectProperty<Playlist> currentPlaylistProperty() {
        return currentPlaylist;
    }

}

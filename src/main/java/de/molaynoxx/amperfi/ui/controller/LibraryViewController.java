package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.player.playlist.Playlist;
import de.molaynoxx.amperfi.ui.view.LibraryView;

public class LibraryViewController extends Controller<LibraryView> {

    public LibraryViewController(LibraryView control) {
        super(control);
    }

    public void showPlaylist(Playlist p) {
        if (p == null) throw new IllegalArgumentException("Playlist object cannot be null.");
        control.getItems().setAll(p.getTracks());
    }

}

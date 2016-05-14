package de.molaynoxx.amperfi.ui.controls.state;

import de.molaynoxx.amperfi.id3.ID3Helper;
import de.molaynoxx.amperfi.player.playlist.Playlist;
import de.molaynoxx.amperfi.ui.controller.SidebarController;

public class SidebarState {

    private final SidebarController.LibraryViewMode libraryViewMode;
    private final Playlist playlist;
    private final ID3Helper.ID3Tag tag;

    public SidebarState(SidebarController.LibraryViewMode libraryViewMode, Playlist playlist) {
        this(libraryViewMode, playlist, null);
    }

    public SidebarState(SidebarController.LibraryViewMode libraryViewMode, Playlist playlist, ID3Helper.ID3Tag tag) {
        this.libraryViewMode = libraryViewMode;
        this.playlist = playlist;
        this.tag = tag;
    }

    public SidebarController.LibraryViewMode getLibraryViewMode() {
        return libraryViewMode;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public ID3Helper.ID3Tag getTag() {
        return tag;
    }

}
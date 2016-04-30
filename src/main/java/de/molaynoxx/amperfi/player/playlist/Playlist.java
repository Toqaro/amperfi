package de.molaynoxx.amperfi.player.playlist;

import de.molaynoxx.amperfi.database.projection.LibraryFile;

import java.util.List;

public interface Playlist {

    List<LibraryFile> getTracks();

}

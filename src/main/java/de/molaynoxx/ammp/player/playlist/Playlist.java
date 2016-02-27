package de.molaynoxx.ammp.player.playlist;

import de.molaynoxx.ammp.database.projection.LibraryFile;

import java.util.List;

public interface Playlist {

    List<LibraryFile> getTracks();

}

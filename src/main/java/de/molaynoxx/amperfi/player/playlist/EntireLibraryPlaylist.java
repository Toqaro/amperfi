package de.molaynoxx.amperfi.player.playlist;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;

import java.util.List;

public class EntireLibraryPlaylist implements Playlist {

    @Override
    public List<LibraryFile> getTracks() {
        return Amperfi.db.getFiles();
    }

}

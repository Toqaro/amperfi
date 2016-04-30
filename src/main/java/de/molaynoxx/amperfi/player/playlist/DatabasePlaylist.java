package de.molaynoxx.amperfi.player.playlist;

import com.querydsl.core.types.Predicate;
import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;

import java.util.List;

public class DatabasePlaylist implements Playlist {

    private final Predicate predicate;
    private final String name;

    public DatabasePlaylist(String name, Predicate predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    @Override
    public List<LibraryFile> getTracks() {
        return Amperfi.db.getLibraryFilesByPredicate(predicate);
    }

    @Override
    public String toString() {
        return name;
    }

}

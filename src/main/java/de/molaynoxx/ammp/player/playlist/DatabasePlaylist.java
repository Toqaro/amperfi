package de.molaynoxx.ammp.player.playlist;

import com.querydsl.core.types.Predicate;
import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.database.projection.LibraryFile;

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
        return AMMP.db.getLibraryFilesByPredicate(predicate);
    }

    @Override
    public String toString() {
        return name;
    }

}

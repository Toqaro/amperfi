package de.molaynoxx.ammp.task;

import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.database.LibraryDatabase;
import de.molaynoxx.ammp.player.PlayerAPI;
import javafx.concurrent.Task;

/**
 * Initializes the needed instances of LibraryDatabase and other classed required to run the UI
 */
public class TaskInitialize extends Task<Boolean> {

    @Override
    protected Boolean call() throws Exception {
        AMMP.db = new LibraryDatabase();
        AMMP.playerAPI = new PlayerAPI();
        AMMP.playerAPI.setCurrentPlaylist(AMMP.db.getFiles());
        return true;
    }

}

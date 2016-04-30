package de.molaynoxx.amperfi.task;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.LibraryDatabase;
import de.molaynoxx.amperfi.player.PlayerAPI;
import javafx.concurrent.Task;

/**
 * Initializes the needed instances of LibraryDatabase and other classed required to run the UI
 */
public class TaskInitialize extends Task<Boolean> {

    @Override
    protected Boolean call() throws Exception {
        Amperfi.db = new LibraryDatabase();
        Amperfi.playerAPI = new PlayerAPI();
        Amperfi.playerAPI.setCurrentPlaylist(Amperfi.db.getFiles());
        return true;
    }

}

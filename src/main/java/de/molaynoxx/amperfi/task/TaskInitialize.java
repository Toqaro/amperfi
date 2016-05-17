package de.molaynoxx.amperfi.task;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.LibraryDatabase;
import java.io.File;
import javafx.concurrent.Task;

/**
 * Initializes the needed instances of LibraryDatabase and other classed required to run the UI
 */
public class TaskInitialize extends Task<Boolean> {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    @Override
    protected Boolean call() throws Exception {
        if(isWindows()) Amperfi.db = new LibraryDatabase(new File(System.getenv("APPDATA") + "\\Amperfi\\", "library.db"));
        else if(isUnix()) Amperfi.db = new LibraryDatabase(new File(System.getProperty("user.home") + "/.amperfi/library.db"));
        else return false;
        return true;
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

}

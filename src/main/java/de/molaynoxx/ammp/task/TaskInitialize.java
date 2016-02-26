package de.molaynoxx.ammp.task;

import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.database.LibraryDatabase;
import javafx.concurrent.Task;

public class TaskInitialize extends Task<Boolean> {

    @Override
    protected Boolean call() throws Exception {
        AMMP.db = new LibraryDatabase();
        return true;
    }

}

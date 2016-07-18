package de.molaynoxx.amperfi.task;

import de.molaynoxx.amperfi.Amperfi;
import javafx.concurrent.Task;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskSyncFolders extends Task<Void> {

    private static final Logger logger = LoggerFactory.getLogger(TaskSyncFolders.class);
    private final ArrayList<File> folders = new ArrayList<>();

    public TaskSyncFolders(File... folders) {
        Collections.addAll(this.folders, folders);
    }

    @Override
    protected Void call() throws Exception {
        try {
            Amperfi.ui.showView(Amperfi.ui.mp3ImportProgress);
            Amperfi.ui.lockView();

            List<File> mp3s = new ArrayList<>();
            for(File folder : folders) {
                mp3s.addAll(FileUtils.listFiles(folder, new String[]{"mp3"}, true));
            }

            int progressCounter = 0;
            for(File mp3file : mp3s) {
                updateMessage(mp3file.getAbsolutePath());
                try {
                    Amperfi.db.importFile(mp3file);
                } catch (Exception e) {
                    logger.warn("Error importing file", e);
                }
                progressCounter++;
                updateProgress(progressCounter, mp3s.size());
                if(isCancelled())
                    break;
            }
            return null;
        } finally {
            Amperfi.db.reloadFiles();
            Amperfi.ui.unlockView();
            Amperfi.ui.showView(Amperfi.ui.mp3Import);
        }
    }

}

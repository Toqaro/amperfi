package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.task.TaskSyncFolders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.List;

public final class Mp3ImportController {

    public final AddFolderHandler addFolderHandler = new AddFolderHandler();
    public final SynchronizeFolderHandler synchronizeFolderHandler = new SynchronizeFolderHandler();

    final class AddFolderHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("Choose directory to import from");
            File directory = dc.showDialog(Amperfi.ui.getWindow());

            if(directory == null) return;
            Amperfi.db.addFolder(directory.getAbsolutePath()); // TODO Check for duplicate/invalid folder
            Amperfi.ui.mp3Import.reloadFolders();

            TaskSyncFolders task = new TaskSyncFolders(directory);
            Amperfi.ui.mp3ImportProgress.btnCancel.setOnAction(action -> task.cancel());
            new Thread(task).start();

            Amperfi.ui.mp3ImportProgress.pbImporting.progressProperty().bind(task.progressProperty());
            Amperfi.ui.mp3ImportProgress.lblPath.textProperty().bind(task.messageProperty());
        }

    }

    final class SynchronizeFolderHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            List<String> folders = Amperfi.db.getFolders();
            File[] arFolders = new File[folders.size()];
            for(int i = 0; i < folders.size(); i++) {
                arFolders[i] = new File(folders.get(i));
            }

            TaskSyncFolders task = new TaskSyncFolders(arFolders);
            Amperfi.ui.mp3ImportProgress.btnCancel.setOnAction(action -> task.cancel());
            new Thread(task).start();

            Amperfi.ui.mp3ImportProgress.pbImporting.progressProperty().bind(task.progressProperty());
            Amperfi.ui.mp3ImportProgress.lblPath.textProperty().bind(task.messageProperty());
        }

    }

}

package de.molaynoxx.ammp.ui.action;

import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.task.TaskSyncFolders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;

import java.io.File;

public final class Mp3ImportController {

    public final AddFolderHandler addFolderHandler = new AddFolderHandler();

    final class AddFolderHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("Choose directory to import from");
            File directory = dc.showDialog(AMMP.ui.getWindow());

            if(directory == null) return;
            AMMP.db.addFolder(directory.getAbsolutePath());
            AMMP.ui.mp3Import.reloadFolders();

            TaskSyncFolders task = new TaskSyncFolders(directory);
            AMMP.ui.mp3ImportProgress.btnCancel.setOnAction(action -> task.cancel());
            new Thread(task).start();

            AMMP.ui.mp3ImportProgress.pbImporting.progressProperty().bind(task.progressProperty());
            AMMP.ui.mp3ImportProgress.lblPath.textProperty().bind(task.messageProperty());
        }

    }

}

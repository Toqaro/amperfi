package de.molaynoxx.ammp;

import de.molaynoxx.ammp.database.LibraryDatabase;
import de.molaynoxx.ammp.player.PlayerAPI;
import de.molaynoxx.ammp.task.TaskInitialize;
import de.molaynoxx.ammp.ui.helper.AMMPUIInitializer;
import de.molaynoxx.ammp.ui.AMMPScene;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class AMMP extends Application {

    public static LibraryDatabase db;
    public static PlayerAPI playerAPI;
    public static AMMPScene ui;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TaskInitialize initializer = new TaskInitialize();
        initializer.setOnSucceeded((workerEvent) -> initializeUI(primaryStage));
        new Thread(initializer).start();
    }

    private void initializeUI(Stage primaryStage) {
        AMMPUIInitializer initializer = new AMMPUIInitializer(primaryStage);
        initializer.initialize();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        AMMP.launch(args);
    }

}

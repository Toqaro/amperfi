package de.molaynoxx.ammp;

import de.molaynoxx.ammp.database.LibraryDatabase;
import de.molaynoxx.ammp.player.PlayerAPI;
import de.molaynoxx.ammp.task.TaskInitialize;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class AMMP extends Application {

    public static LibraryDatabase db;
    public static PlayerAPI playerAPI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TaskInitialize initializer = new TaskInitialize();
        initializer.setOnSucceeded((workerEvent) -> initializeUI(primaryStage));
        new Thread(initializer).start();
    }

    private void initializeUI(Stage primaryStage) {
        primaryStage.show();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        AMMP.launch(args);
    }

}

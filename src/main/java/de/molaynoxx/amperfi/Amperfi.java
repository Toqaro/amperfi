package de.molaynoxx.amperfi;

import de.molaynoxx.amperfi.database.LibraryDatabase;
import de.molaynoxx.amperfi.player.PlayerAPI;
import de.molaynoxx.amperfi.task.TaskInitialize;
import de.molaynoxx.amperfi.ui.AmperfiScene;
import de.molaynoxx.amperfi.ui.helper.AmperfiUIInitializer;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class Amperfi extends Application {

    public static LibraryDatabase db;
    public static PlayerAPI playerAPI;
    public static AmperfiScene ui;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TaskInitialize initializer = new TaskInitialize();
        initializer.setOnSucceeded((workerEvent) -> initializeUI(primaryStage));
        new Thread(initializer).start();
    }

    private void initializeUI(Stage primaryStage) {
        AmperfiUIInitializer initializer = new AmperfiUIInitializer(primaryStage);
        initializer.initialize();
    }

    public static void main(String[] args) {
        System.out.println(BuildConfig.IS_PORTABLE);
        BasicConfigurator.configure();
        Amperfi.launch(args);
    }

}

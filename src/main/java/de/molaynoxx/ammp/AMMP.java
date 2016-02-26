package de.molaynoxx.ammp;

import de.molaynoxx.ammp.database.LibraryDatabase;
import de.molaynoxx.ammp.task.TaskInitialize;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class AMMP extends Application {

    public static LibraryDatabase db;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
        new Thread(new TaskInitialize()).start();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        AMMP.launch(args);
    }

}

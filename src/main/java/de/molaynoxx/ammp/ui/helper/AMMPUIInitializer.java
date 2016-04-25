package de.molaynoxx.ammp.ui.helper;

import de.molaynoxx.ammp.AMMP;
import de.molaynoxx.ammp.ui.AMMPScene;
import de.molaynoxx.ammp.ui.SettingsItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AMMPUIInitializer {

    private final Stage primaryStage;

    public AMMPUIInitializer(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        AMMP.ui = new AMMPScene();
        primaryStage.setScene(AMMP.ui);
        primaryStage.setTitle("AMMP!");

        /* Add settings to UI */
        SettingsItem si_mp3Import = new SettingsItem(new Image(AMMP.class.getResourceAsStream("/icn/icnImport.png")), "Import mp3s");
        si_mp3Import.setOnMouseClicked(action -> AMMP.ui.showView(AMMP.ui.mp3Import));
        AMMP.ui.settings.addSettingsItem(si_mp3Import);
        
        AMMP.ui.settings.addSettingsItem(new SettingsItem(new Image(AMMP.class.getResourceAsStream("/icn/icnVisualizer.png")), "Visualizer"));
        AMMP.ui.settings.addSettingsItem(new SettingsItem(new Image(AMMP.class.getResourceAsStream("/icn/icnWebInterface.png")), "Web Interface"));

        AMMP.ui.showView(AMMP.ui.settings);

        primaryStage.show();
    }

}

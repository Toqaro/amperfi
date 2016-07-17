package de.molaynoxx.amperfi.ui.helper;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.player.playlist.EntireLibraryPlaylist;
import de.molaynoxx.amperfi.ui.AmperfiScene;
import de.molaynoxx.amperfi.ui.controller.SidebarController;
import de.molaynoxx.amperfi.ui.controls.SettingsItem;
import de.molaynoxx.amperfi.ui.controls.state.SidebarState;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AmperfiUIInitializer {

    private final Stage primaryStage;

    public AmperfiUIInitializer(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        Amperfi.ui = new AmperfiScene();
        primaryStage.setScene(Amperfi.ui);
        primaryStage.setTitle("Amperfi");

        /* Add settings to UI */
        SettingsItem si_browse = new SettingsItem(new Image(Amperfi.class.getResourceAsStream("/icn/icnBrowse.png")), "Browse Library");
        si_browse.setOnMouseClicked(action -> Amperfi.ui.showView(Amperfi.ui.libraryView));
        Amperfi.ui.settings.addSettingsItem(si_browse);

        SettingsItem si_mp3Import = new SettingsItem(new Image(Amperfi.class.getResourceAsStream("/icn/icnImport.png")), "Import mp3s");
        si_mp3Import.setOnMouseClicked(action -> Amperfi.ui.showView(Amperfi.ui.mp3Import));
        Amperfi.ui.settings.addSettingsItem(si_mp3Import);

        SettingsItem si_visualizer = new SettingsItem(new Image(Amperfi.class.getResourceAsStream("/icn/icnVisualizer.png")), "Visualizer");
        si_visualizer.setOnMouseClicked(action -> Amperfi.ui.showView(Amperfi.ui.visualizerView));
        Amperfi.ui.settings.addSettingsItem(si_visualizer);

        Amperfi.ui.settings.addSettingsItem(new SettingsItem(new Image(Amperfi.class.getResourceAsStream("/icn/icnWebInterface.png")), "Web Interface"));

        Amperfi.ui.showView(Amperfi.ui.libraryView);

        Amperfi.ui.sidebar.controller.showMode(new SidebarState(SidebarController.LibraryViewMode.LIBRARY, EntireLibraryPlaylist.INSTANCE));

        primaryStage.show();
    }

}

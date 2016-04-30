package de.molaynoxx.amperfi.ui.view;

import de.molaynoxx.amperfi.ui.SettingsItem;
import javafx.scene.layout.FlowPane;

public class SettingsOverview extends FlowPane implements Viewable {

    public SettingsOverview() {
        setHgap(25);
        setVgap(25);
        setStyle("-fx-padding: 20px;");
    }

    public void addSettingsItem(SettingsItem si) {
        getChildren().add(si);
    }

}

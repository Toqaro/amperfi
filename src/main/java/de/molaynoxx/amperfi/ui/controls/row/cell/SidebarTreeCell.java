package de.molaynoxx.amperfi.ui.controls.row.cell;

import de.molaynoxx.amperfi.Amperfi;
import javafx.css.PseudoClass;
import javafx.scene.control.TreeCell;

public class SidebarTreeCell<T> extends TreeCell<T> {

    private static PseudoClass playing = PseudoClass.getPseudoClass("playing");

    public SidebarTreeCell() {
        super();
        setMaxWidth(285);
        setMinWidth(285);
        setPrefWidth(285);
        setWidth(285);

        getStyleClass().add("treecell-sidebar");

        Amperfi.playbackController.currentlyPlayingProperty().addListener((obv, oldVal, newVal) -> {
            this.pseudoClassStateChanged(playing, getItem() == newVal);
        });
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            setText(item.toString());
        } else {
            setText(null);
        }
    }

}

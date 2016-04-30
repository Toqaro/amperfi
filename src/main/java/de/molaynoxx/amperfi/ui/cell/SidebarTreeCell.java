package de.molaynoxx.amperfi.ui.cell;

import javafx.scene.control.TreeCell;

public class SidebarTreeCell<T> extends TreeCell<T> {

    public SidebarTreeCell() {
        super();
        setMaxWidth(285);
        setMinWidth(285);
        setPrefWidth(285);
        setWidth(285);

        getStyleClass().add("treecell-sidebar");
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

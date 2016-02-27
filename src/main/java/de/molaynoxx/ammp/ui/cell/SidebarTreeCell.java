package de.molaynoxx.ammp.ui.cell;

import javafx.scene.control.TreeCell;

public class SidebarTreeCell<T> extends TreeCell<T> {

    public SidebarTreeCell() {
        super();

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

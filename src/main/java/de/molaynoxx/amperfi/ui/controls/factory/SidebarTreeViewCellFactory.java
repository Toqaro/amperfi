package de.molaynoxx.amperfi.ui.controls.factory;

import de.molaynoxx.amperfi.ui.controls.SidebarTreeView;
import de.molaynoxx.amperfi.ui.controls.row.cell.SidebarTreeCell;
import javafx.application.Platform;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class SidebarTreeViewCellFactory<T> implements Callback<TreeView<T>,TreeCell<T>> {

    private TreeCell<T> lastCell;
    private final Runnable resizer;

    public SidebarTreeViewCellFactory(SidebarTreeView<T> treeView) {
        // Resizes the TreeView as the titled pane expands based on children count and size
        resizer = () -> {
            if(lastCell == null) return;
            double size = lastCell.getHeight() * treeView.getRoot().getChildren().size();
            lastCell.getTreeView().setPrefHeight(size);
            lastCell.getTreeView().setMinHeight(size);
            lastCell.getTreeView().setMaxHeight(size);
        };
    }

    @Override
    public TreeCell<T> call(TreeView<T> param) {
        lastCell = new SidebarTreeCell<>();
        Platform.runLater(resizer);
        return lastCell;
    }

}

package de.molaynoxx.amperfi.ui.controls.row;

import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controller.LibraryViewRowController;
import javafx.scene.control.TableRow;

public class LibraryViewRow extends TableRow<LibraryFile> {

    private final LibraryViewRowController controller = new LibraryViewRowController(this);

    public LibraryViewRow() {
        super();

        setOnMouseClicked(controller.mouseClickHandler);
    }

    @Override
    protected void updateItem(LibraryFile item, boolean empty) {
        super.updateItem(item, empty);
        controller.handleUpdate();
    }

}

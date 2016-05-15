package de.molaynoxx.amperfi.ui.controls.factory;

import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controls.row.LibraryViewRow;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class LibraryViewRowFactory implements Callback<TableView<LibraryFile>, TableRow<LibraryFile>> {

    @Override
    public TableRow<LibraryFile> call(TableView<LibraryFile> param) {
        return new LibraryViewRow();
    }

}

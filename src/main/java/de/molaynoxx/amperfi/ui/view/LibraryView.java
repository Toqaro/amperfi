package de.molaynoxx.amperfi.ui.view;

import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.id3.ID3Helper;
import de.molaynoxx.amperfi.ui.controller.LibraryViewController;
import de.molaynoxx.amperfi.ui.controls.factory.LibraryViewRowFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LibraryView extends TableView<LibraryFile> implements Viewable {

    public final LibraryViewController controller = new LibraryViewController(this);

    public LibraryView(ID3Helper.ID3Tag... tags) {
        getStyleClass().add("library-view");
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setRowFactory(new LibraryViewRowFactory());

        for (ID3Helper.ID3Tag tag : tags) {
            TableColumn<LibraryFile, String> column = new TableColumn<>(tag.toString());
            column.setCellValueFactory(param -> param.getValue().getTagProperty(tag));
            getColumns().add(column);
        }
    }

}

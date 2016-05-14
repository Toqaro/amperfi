package de.molaynoxx.amperfi.ui.view;

import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.id3.ID3Helper;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LibraryView extends TableView<LibraryFile> implements Viewable {

    public LibraryView(ID3Helper.ID3Tag... tags) {
        getStyleClass().add("library-view");
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (ID3Helper.ID3Tag tag : tags) {
            TableColumn<LibraryFile, String> column = new TableColumn<>(tag.toString());
            column.setCellValueFactory(param -> param.getValue().getTagProperty(tag));
            getColumns().add(column);
        }
    }

}

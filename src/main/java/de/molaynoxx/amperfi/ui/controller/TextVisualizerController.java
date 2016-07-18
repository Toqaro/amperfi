package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controls.visualizer.TextVisualizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TextVisualizerController extends AbstractController<TextVisualizer> {

    public TextVisualizerController(TextVisualizer control) {
        super(control);

        Amperfi.playbackController.currentTitleProperty().addListener(new CurrentTitleChangeListener());
    }

    private class CurrentTitleChangeListener implements ChangeListener<LibraryFile> {

        @Override
        public void changed(ObservableValue<? extends LibraryFile> observable, LibraryFile oldValue, LibraryFile newValue) {
            control.setText(newValue.getTitle(), newValue.getArtist());
        }

    }
}

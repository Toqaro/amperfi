package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controls.NowPlaying;
import de.molaynoxx.amperfi.ui.helper.CoverUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NowPlayingController extends AbstractController<NowPlaying> {

    public NowPlayingController(NowPlaying control) {
        super(control);

        Amperfi.playbackController.currentTitleProperty().addListener(new CurrentTitleChangeListener());
    }

    private class CurrentTitleChangeListener implements ChangeListener<LibraryFile> {

        @Override
        public void changed(ObservableValue<? extends LibraryFile> observable, LibraryFile oldValue, LibraryFile newValue) {
            control.title.setText(newValue.getTitle());
            control.artist.setText(newValue.getArtist());

            try {
                control.coverView.setImage(CoverUtils.loadCover(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

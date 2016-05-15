package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.ui.controls.NowPlaying;
import de.molaynoxx.amperfi.ui.helper.CoverUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NowPlayingController extends AbstractController<NowPlaying> {

    private static Logger log = LoggerFactory.getLogger(NowPlayingController.class);

    public NowPlayingController(NowPlaying control) {
        super(control);

        Amperfi.playbackController.currentTitleProperty().addListener(new CurrentTitleChangeListener());
    }

    private class CurrentTitleChangeListener implements ChangeListener<LibraryFile> {

        private Image genericCover = new Image(Amperfi.class.getResourceAsStream("/NoCover.png"));

        @Override
        public void changed(ObservableValue<? extends LibraryFile> observable, LibraryFile oldValue, LibraryFile newValue) {
            control.title.setText(newValue.getTitle());
            control.artist.setText(newValue.getArtist());

            try {
                control.coverView.setImage(CoverUtils.loadCover(newValue));
            } catch (Exception e) {
                log.debug("Couldn't load cover", e);
                control.coverView.setImage(genericCover);
            }
        }

    }

}

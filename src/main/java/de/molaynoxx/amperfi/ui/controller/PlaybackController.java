package de.molaynoxx.amperfi.ui.controller;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.player.PlayerAPI;
import de.molaynoxx.amperfi.player.playlist.Playlist;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PlaybackController {

    private final PlayerAPI playerAPI = new PlayerAPI();

    private final ObjectProperty<Playlist> currentlyPlaying = new SimpleObjectProperty<>();
    private final ObjectProperty<LibraryFile> currentTitle = new SimpleObjectProperty<>();
    private final ObjectProperty<PlayerAPI.PlayerStatus> status = new SimpleObjectProperty<>();
    private final IntegerProperty currentIndex = new SimpleIntegerProperty(-1);
    private final IntegerProperty position = new SimpleIntegerProperty(0);
    private final IntegerProperty length = new SimpleIntegerProperty(0);

    public PlaybackController() {
        Thread thStatusUpdater = new Thread(new StatusPropertyUpdater());
        thStatusUpdater.setName("PlayerAPI UI Property Updater Thread");
        thStatusUpdater.setDaemon(true);
        thStatusUpdater.start();
    }

    public void playPlaylist(Playlist pls) {
        if (playerAPI.getStatus() != PlayerAPI.PlayerStatus.STOPPED) playerAPI.stop();
        currentlyPlaying.set(pls);
        playerAPI.setCurrentPlaylist(pls.getTracks());
        playerAPI.setCurrentIndex(0);
        playerAPI.play();
    }

    public void playPlaylist(Playlist pls, LibraryFile lf) {
        if (playerAPI.getStatus() != PlayerAPI.PlayerStatus.STOPPED) playerAPI.stop();
        if (pls != currentlyPlaying.get()) {
            playerAPI.setCurrentPlaylist(pls.getTracks());
            currentlyPlaying.set(pls);
        }

        int index = 0;
        for (index = 0; index < playerAPI.getCurrentPlaylist().size(); index++) {
            if (playerAPI.getCurrentPlaylist().get(index).getFileId() == lf.getFileId()) break;
        }
        playerAPI.setCurrentIndex(index);
        playerAPI.play();
    }

    public void play() {
        if (playerAPI.getCurrentPlaylist().size() == 0) {
            playerAPI.setCurrentPlaylist(Amperfi.ui.libraryView.controller.getCurrentPlaylist().getTracks());
            currentlyPlaying.set(Amperfi.ui.libraryView.controller.getCurrentPlaylist());
        }
        playerAPI.play();
    }

    public void pause() {
        playerAPI.pause();
    }

    public void stop() {
        playerAPI.stop();
    }

    public void previous() {
        playerAPI.previous();
    }

    public void next() {
        playerAPI.next();
    }

    public void jumpToPercent(double percent) {
        playerAPI.jumpToPercent(percent);
    }

    public Playlist getCurrentlyPlaying() {
        return currentlyPlaying.get();
    }

    public ObjectProperty<Playlist> currentlyPlayingProperty() {
        return currentlyPlaying;
    }

    public int getCurrentIndex() {
        return currentIndex.get();
    }

    public IntegerProperty currentIndexProperty() {
        return currentIndex;
    }

    public PlayerAPI.PlayerStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<PlayerAPI.PlayerStatus> statusProperty() {
        return status;
    }

    public int getPosition() {
        return position.get();
    }

    public IntegerProperty positionProperty() {
        return position;
    }

    public int getLength() {
        return length.get();
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public LibraryFile getCurrentTitle() {
        return currentTitle.get();
    }

    public ObjectProperty<LibraryFile> currentTitleProperty() {
        return currentTitle;
    }

    private final class StatusPropertyUpdater implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) break;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }

                if (playerAPI.getStatus() != status.get()) {
                    Platform.runLater(() -> status.set(playerAPI.getStatus()));
                }
                if (playerAPI.getCurrentIndex() != currentIndex.get()) {
                    Platform.runLater(() -> currentIndex.set(playerAPI.getCurrentIndex()));
                }
                if (playerAPI.getPosition() != position.get()) {
                    Platform.runLater(() -> position.set(playerAPI.getPosition()));
                }
                if (playerAPI.getLength() != length.get()) {
                    Platform.runLater(() -> length.set(playerAPI.getLength()));
                }
                if (playerAPI.getCurrentTitle() != currentTitle.get()) {
                    Platform.runLater(() -> currentTitle.set(playerAPI.getCurrentTitle()));
                }
            }
        }

    }

}

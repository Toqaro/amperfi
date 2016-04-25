package de.molaynoxx.ammp.player;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import de.molaynoxx.ammp.database.projection.LibraryFile;
import de.molaynoxx.ammp.player.minim.DefaultMinimHelper;
import de.molaynoxx.ammp.player.minim.PlaybackUpdater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAPI {

    public enum PlayerStatus {
        STOPPED, PLAYING, PAUSED
    }

    private final Minim minim = new Minim(new DefaultMinimHelper());
    private AudioPlayer currentPlayer;

    private Thread playbackUpdater;

    private final ArrayList<LibraryFile> originalPlaylist = new ArrayList<>();
    private final ArrayList<LibraryFile> currentPlaylist = new ArrayList<>();
    private int currentIndex = 0;

    /**
     * Repeat a playlist after playing the last song of it
     */
    private boolean repeat = false;

    /**
     * Play a single song in a loop
     */
    private boolean loop = false;

    /**
     * Randomize the playlist order
     */
    private boolean random = false;

    /**
     * Volume as float from 0.0 to 1.0
     */
    private float volume = 0.5f;

    private PlayerStatus status = PlayerStatus.STOPPED;

    /**
     * Changes the list of songs currently queued for playback
     * @param currentPlaylist New list of songs to be queued for playback
     */
    public void setCurrentPlaylist(List<LibraryFile> currentPlaylist) {
        this.currentPlaylist.clear();
        this.currentPlaylist.addAll(currentPlaylist);
        this.originalPlaylist.clear();
        this.originalPlaylist.addAll(currentPlaylist);
        if(random) randomizePlaylist();
    }

    /**
     * Returns the list of songs currently queued for playback
     * @return The list of songs currently queued for playback.
     */
    public List<LibraryFile> getCurrentPlaylist() {
        return this.currentPlaylist;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        if(random) randomizePlaylist();
        else undoRandomization();
        this.random = random;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if(currentPlayer != null) currentPlayer.setGain(-80 * (1 - volume));
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    private void setStatus(PlayerStatus status) {
        this.status = status;
    }

    private void randomizePlaylist() {
        Collections.shuffle(currentPlaylist);
    }

    private void undoRandomization() {
        this.currentPlaylist.clear();
        this.currentPlaylist.addAll(originalPlaylist);
    }

    private void startPlayback() {
        setStatus(PlayerStatus.PLAYING);
        currentPlayer = minim.loadFile(currentPlaylist.get(currentIndex).getPath());
        currentPlayer.play();
        currentPlayer.setGain(-80 * (1 - volume));
        playbackUpdater = new Thread(new PlaybackUpdater(currentPlayer, this::afterPlayback, this));
        playbackUpdater.start();
    }

    private void stopPlayback() {
        setStatus(PlayerStatus.STOPPED);
    }

    private void afterPlayback() {
        currentPlayer.close();
        if(loop) {
            startPlayback();
        } else {
            currentIndex++;
            if(currentIndex >= currentPlaylist.size()) {
                if(repeat) {
                    currentIndex = 0;
                    startPlayback();
                } else {
                    stopPlayback();
                }
            } else {
                startPlayback();
            }
        }
    }

    public void pause() {
        if(currentPlayer == null) return;
        currentPlayer.pause();
        setStatus(PlayerStatus.PAUSED);
    }

    public void stop() {
        if(currentPlayer == null) return;
        playbackUpdater.interrupt();
        currentPlayer.close();
        currentPlayer = null;
        currentIndex = 0;
        setStatus(PlayerStatus.STOPPED);
    }

    public void play() {
        if(status == PlayerStatus.PAUSED) {
            currentPlayer.play();
        } else if(status == PlayerStatus.STOPPED) {
            startPlayback();
        }
    }

    public void next() {
        playbackUpdater.interrupt();
        if(loop) currentIndex++;
        afterPlayback();
    }

    public void previous() {
        playbackUpdater.interrupt();
        currentIndex -= loop ? 1 : 2;
        currentIndex %= currentPlaylist.size();
        afterPlayback();
    }

}

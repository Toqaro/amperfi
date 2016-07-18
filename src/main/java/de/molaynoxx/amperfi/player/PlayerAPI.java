package de.molaynoxx.amperfi.player;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.player.minim.DefaultMinimHelper;
import de.molaynoxx.amperfi.player.minim.PlaybackUpdater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAPI {

    public enum PlayerStatus {
        STOPPED, PLAYING, PAUSED, FORWARDING
    }

    private final Minim minim = new Minim(new DefaultMinimHelper());
    private AudioPlayer currentPlayer;

    private Thread playbackUpdater;

    private final ArrayList<LibraryFile> originalPlaylist = new ArrayList<>();
    private final ArrayList<LibraryFile> currentPlaylist = new ArrayList<>();
    private int currentIndex = 0;

    private FFT fft;

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

    // TODO Better Volume Ranges
    public void setVolume(float volume) {
        this.volume = volume;
        if (currentPlayer != null) currentPlayer.setGain(-80 * (1.0f - volume));
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

        fft = new FFT(currentPlayer.bufferSize(), currentPlayer.sampleRate());

        playbackUpdater = new Thread(new PlaybackUpdater(currentPlayer, this::afterPlayback, this));
        playbackUpdater.setDaemon(true);
        playbackUpdater.start();
    }

    private void stopPlayback() {
        setStatus(PlayerStatus.STOPPED);
    }

    private void afterPlayback() {
        fft = null;
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
            setStatus(PlayerStatus.PLAYING);
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
        if (currentIndex == 0) {
            currentIndex = currentPlaylist.size() - (loop ? 1 : 2);
        } else {
            currentIndex -= loop ? 1 : 2;
        }
        currentIndex %= currentPlaylist.size();
        afterPlayback();
    }

    public int getPosition() {
        if (currentPlayer == null) return 0;
        return currentPlayer.position();
    }

    public int getLength() {
        if (currentPlayer == null) return 0;
        return currentPlayer.length();
    }

    public LibraryFile getCurrentTitle() {
        if (currentPlaylist.size() == 0) return null;
        return currentPlaylist.get(currentIndex);
    }

    public FFT getFFT() {
        return fft;
    }

    public AudioPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Jumps to the position of the song in percent (0-1)
     *
     * @param percent Double value from 0 to 1 expressing the position to jump to in the current song in percent
     */
    public void jumpToPercent(double percent) {
        if (currentPlayer == null) return;
        System.out.println("Jumping to: " + ((int) (currentPlayer.getMetaData().length() * percent)) + "ms");
        setStatus(PlayerStatus.FORWARDING);
        currentPlayer.cue((int) (currentPlayer.getMetaData().length() * percent));
        setStatus(PlayerStatus.PLAYING);
    }

}

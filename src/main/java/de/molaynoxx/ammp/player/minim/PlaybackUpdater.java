package de.molaynoxx.ammp.player.minim;

import ddf.minim.AudioPlayer;

public class PlaybackUpdater implements Runnable {

    private final AudioPlayer player;
    private final Runnable afterPlayback;

    public PlaybackUpdater(AudioPlayer player, Runnable afterPlayback) {
        this.player = player;
        this.afterPlayback = afterPlayback;
    }

    @Override
    public void run() {
        while (player.isPlaying()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
        new Thread(afterPlayback).start();
    }

}

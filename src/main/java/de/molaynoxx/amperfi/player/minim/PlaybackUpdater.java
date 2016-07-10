package de.molaynoxx.amperfi.player.minim;

import ddf.minim.AudioPlayer;
import de.molaynoxx.amperfi.player.PlayerAPI;

public class PlaybackUpdater implements Runnable {

    private final AudioPlayer player;
    private final Runnable afterPlayback;
    private final PlayerAPI api;

    public PlaybackUpdater(AudioPlayer player, Runnable afterPlayback, PlayerAPI api) {
        this.player = player;
        this.afterPlayback = afterPlayback;
        this.api = api;
    }

    @Override
    public void run() {
        while (player.isPlaying() || (!player.isPlaying() && (api.getStatus() == PlayerAPI.PlayerStatus.PAUSED || api.getStatus() == PlayerAPI.PlayerStatus.FORWARDING))) {
            if(Thread.currentThread().isInterrupted()) return;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println(api.getStatus());
        System.out.println(api.getCurrentIndex());
        new Thread(afterPlayback).start();
    }

}

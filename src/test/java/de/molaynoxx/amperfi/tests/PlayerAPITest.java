package de.molaynoxx.amperfi.tests;

import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.player.PlayerAPI;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerAPITest {

    @Test
    public void playbackTest() throws InterruptedException {
        //if(new Minim(new DefaultMinimHelper()).getLineOut() == null) return;

        LibraryFile lf = new LibraryFile();
        lf.setPath(new File("testResources/noise.mp3").getAbsolutePath());
        LibraryFile lf2 = new LibraryFile();
        lf2.setPath(new File("testResources/noise2.mp3").getAbsolutePath());

        ArrayList<LibraryFile> playlist = new ArrayList<>();
        playlist.add(lf);
        playlist.add(lf2);

        PlayerAPI player = new PlayerAPI();
        player.setCurrentPlaylist(playlist);

        assertEquals(PlayerAPI.PlayerStatus.STOPPED, player.getStatus());

        player.play();

        Thread.sleep(1000);

        assertEquals(PlayerAPI.PlayerStatus.PLAYING, player.getStatus());

        player.pause();

        Thread.sleep(1000);

        assertEquals(PlayerAPI.PlayerStatus.PAUSED, player.getStatus());
        assertEquals(0, player.getCurrentIndex());

        player.next();

        Thread.sleep(1000);

        assertEquals(PlayerAPI.PlayerStatus.PLAYING, player.getStatus());
        assertEquals(1, player.getCurrentIndex());

        player.setRepeat(true);
        player.next();

        Thread.sleep(1000);

        assertEquals(0, player.getCurrentIndex());

        player.previous();

        Thread.sleep(1000);

        assertEquals(1, player.getCurrentIndex());

        player.setVolume(.5f);

        Thread.sleep(1000);

        assertEquals(.5f, player.getVolume(), 0.01);

        player.stop();
    }

}
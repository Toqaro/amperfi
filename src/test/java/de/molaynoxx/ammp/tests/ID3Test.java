package de.molaynoxx.ammp.tests;

import static org.junit.Assert.*;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.molaynoxx.ammp.id3.ID3Helper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ID3Test {

    @Test
    public void testReading() throws InvalidDataException, IOException, UnsupportedTagException {
        ID3Helper reader = new ID3Helper(new File("testResources/noise.mp3"));

        assertEquals(reader.getTag(ID3Helper.ID3Tag.TITLE), "Brown Noise");
        assertEquals(reader.getTag(ID3Helper.ID3Tag.ARTIST), "NoiseMaker");
        assertEquals(reader.getTag(ID3Helper.ID3Tag.ALBUM), "Noises");
    }

}

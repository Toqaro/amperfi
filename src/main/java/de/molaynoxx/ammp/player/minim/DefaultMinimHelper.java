package de.molaynoxx.ammp.player.minim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DefaultMinimHelper {

    public String sketchPath(String fileName) {
        return new File(fileName).getAbsolutePath();
    }

    public InputStream createInput(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}

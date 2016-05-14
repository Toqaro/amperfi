package de.molaynoxx.amperfi.ui.helper;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CoverUtils {

    public static Image loadCover(LibraryFile lf) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3 = new Mp3File(lf.getPath());
        ByteArrayInputStream bais = new ByteArrayInputStream(mp3.getId3v2Tag().getAlbumImage());
        BufferedImage bufImg = ImageIO.read(bais);
        return SwingFXUtils.toFXImage(bufImg, null);
    }

}

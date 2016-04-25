package de.molaynoxx.ammp.tests;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.molaynoxx.ammp.database.LibraryDatabase;
import de.molaynoxx.ammp.database.model.QLibraryFile;
import de.molaynoxx.ammp.id3.ID3Helper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void addRemoveFolder() throws SQLException, UnsupportedTagException, InvalidDataException, IOException {
        LibraryDatabase db = new LibraryDatabase(new File("db.db"));

        db.addFolder(new File("testResources/").getAbsolutePath());
        assertEquals(db.getFolders().size(), 1);

        db.removeFolder(new File("testResources/").getAbsolutePath());
        assertEquals(db.getFolders().size(), 0);
    }

    @Test
    public void importFile() throws SQLException, UnsupportedTagException, InvalidDataException, IOException {
        LibraryDatabase db = new LibraryDatabase(new File("db.db"));

        db.importFile(new File("testResources/noise.mp3"));

        assertEquals(db.getFiles().size(), 1);
        assertEquals(db.getTags(ID3Helper.ID3Tag.ARTIST).size(), 1);
        assertEquals(db.getTags(ID3Helper.ID3Tag.GENRE).size(), 1);
        assertEquals(db.getFiles().get(0).getTag(ID3Helper.ID3Tag.TITLE), "Brown Noise");
        assertEquals(db.getFiles().get(0).getTitle(), "Brown Noise");

        List<String> artists = db.getTags(ID3Helper.ID3Tag.ARTIST);
        assertEquals(artists.size(), 1);

        assertEquals(db.getLibraryFilesByPredicate(QLibraryFile.LibraryFile.title.contains("Noise")).size(), 1);
        assertEquals(db.getLibraryFilesByPredicate(QLibraryFile.LibraryFile.artist.eq(artists.get(0))).size(), 1);

        db.importFile(new File("testResources/noise.mp3"));
    }

}

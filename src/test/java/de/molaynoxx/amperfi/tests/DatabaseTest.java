package de.molaynoxx.amperfi.tests;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.molaynoxx.amperfi.database.LibraryDatabase;
import de.molaynoxx.amperfi.database.model.QLibraryFile;
import de.molaynoxx.amperfi.id3.ID3Helper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @Test
    public void addRemoveFolder() throws SQLException, UnsupportedTagException, InvalidDataException, IOException {
        LibraryDatabase db = new LibraryDatabase(new File("test.db"));

        db.addFolder(new File("testResources/").getAbsolutePath());
        assertEquals(1, db.getFolders().size());

        db.removeFolder(new File("testResources/").getAbsolutePath());
        assertEquals(0, db.getFolders().size());
        new File("test.db").delete();
    }

    @Test
    public void importFile() throws SQLException, UnsupportedTagException, InvalidDataException, IOException {
        LibraryDatabase db = new LibraryDatabase(new File("test2.db"));

        db.importFile(new File("testResources/noise.mp3"));

        db.reloadFiles();

        assertEquals(1, db.getFiles().size());
        assertEquals(1, db.getTags(ID3Helper.ID3Tag.ARTIST).size());
        assertEquals(1, db.getTags(ID3Helper.ID3Tag.GENRE).size());
        assertEquals("Brown Noise", db.getFiles().get(0).getTag(ID3Helper.ID3Tag.TITLE));
        assertEquals("Brown Noise", db.getFiles().get(0).getTitle());

        List<String> artists = db.getTags(ID3Helper.ID3Tag.ARTIST);
        assertEquals(artists.size(), 1);

        assertEquals(1, db.getLibraryFilesByPredicate(QLibraryFile.LibraryFile.title.contains("Noise")).size());
        assertEquals(1, db.getLibraryFilesByPredicate(QLibraryFile.LibraryFile.artist.eq(artists.get(0))).size());

        db.importFile(new File("testResources/noise.mp3"));
        new File("test2.db").delete();
    }

}

package de.molaynoxx.ammp.database;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLiteTemplates;
import de.molaynoxx.ammp.database.model.QLibraryFile;
import de.molaynoxx.ammp.database.projection.LibraryFile;
import de.molaynoxx.ammp.id3.ID3Helper;
import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibraryDatabase {

    private final SQLQueryFactory queryFactory;
    private final ArrayList<LibraryFile> files = new ArrayList<>();

    public LibraryDatabase() throws SQLException, UnsupportedTagException, InvalidDataException, IOException {
        this(new File(System.getenv("APPDATA") + "\\AMMP\\", "library.db"));
    }

    public LibraryDatabase(File databaseLocation) throws SQLException, InvalidDataException, IOException, UnsupportedTagException {
        if (!databaseLocation.exists()) {
            if (!databaseLocation.getParentFile().mkdirs() && !databaseLocation.getParentFile().exists())
                throw new IOException("Unable to create database directory");

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation.getAbsolutePath());
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS LibraryFolder (path TEXT PRIMARY KEY)");

            StringBuilder builderFileTable = new StringBuilder();
            builderFileTable.append("CREATE TABLE IF NOT EXISTS LibraryFile (file_id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT UNIQUE");
            for (ID3Helper.ID3Tag id3Tag : ID3Helper.ID3Tag.values()) {
                builderFileTable
                        .append(", ")
                        .append(id3Tag.toString().toLowerCase())
                        .append(" TEXT");
            }
            builderFileTable.append(")");
            stmt.executeUpdate(builderFileTable.toString());
            conn.close();
        }

        /*Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation.getAbsolutePath());

        MetaDataExporter exporter = new MetaDataExporter();
        exporter.setPackageName("de.molaynoxx.ammp.database.model");
        exporter.setTargetFolder(new File("src/main/java/"));
        exporter.setBeanPackageName("de.molaynoxx.ammp.database.projection");
        exporter.setBeansTargetFolder(new File("src/main/java/"));
        exporter.setBeanSerializer(new BeanSerializer());
        exporter.setExportAll(true);
        exporter.export(conn.getMetaData());*/

        SQLiteTemplates sqLiteTemplate = new SQLiteTemplates();
        Configuration config = new Configuration(sqLiteTemplate);

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + databaseLocation.getAbsolutePath());

        queryFactory = new SQLQueryFactory(config, dataSource);

        QLibraryFile libFile = QLibraryFile.LibraryFile;
        List<LibraryFile> files = queryFactory.select(Projections.bean(LibraryFile.class, libFile.all())).from(libFile).fetch();
        this.files.addAll(files);
    }

    /**
     * Returns the list containing all indexed files, loaded from the database on startup
     * Adding/Removing items might have unwanted side-effects but won't affect the database
     *
     * To remove/add files from/to the database use the removeFile/addFile methods of LibraryDatabase and make sure to reload modules dependent on this information afterwards
     * @return Returns the list containing all indexed files, loaded from the database on startup
     */
    public List<LibraryFile> getFiles() {
        return this.files;
    }

    public void importFile(File mp3File) throws InvalidDataException, IOException, UnsupportedTagException {
        ID3Helper id3Helper = new ID3Helper(mp3File);

        QLibraryFile libFile = QLibraryFile.LibraryFile;

        List<Path<?>> columns = libFile.getColumns();
        Iterator<Path<?>> pathIterator = columns.iterator();
        while (pathIterator.hasNext()) {
            Path<?> column = pathIterator.next();
            if(column.getMetadata().getName().equalsIgnoreCase("file_id")) pathIterator.remove();
            if(column.getMetadata().getName().equalsIgnoreCase("path")) pathIterator.remove();
        }

        columns.add(libFile.path);
        Path<?>[] columnsArray = columns.toArray(new Path<?>[columns.size()]);

        String[] values = new String[columnsArray.length];

        for(ID3Helper.ID3Tag id3Tag : ID3Helper.ID3Tag.values()) {
            int column = -1;
            for(int i = 0; i < columnsArray.length; i++) {
                if(columnsArray[i].getMetadata().getName().equalsIgnoreCase(id3Tag.toString())) column = i;
            }
            values[column] = id3Helper.getTag(id3Tag);
        }
        values[values.length - 1] = mp3File.getAbsolutePath();

        queryFactory.insert(libFile)
                .columns(columnsArray)
                .values(values)
                .execute();

    }

}

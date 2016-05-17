package de.molaynoxx.amperfi.database;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLiteTemplates;
import com.querydsl.sql.dml.SQLUpdateClause;
import de.molaynoxx.amperfi.database.model.QLibraryFile;
import de.molaynoxx.amperfi.database.model.QLibraryFolder;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.database.projection.LibraryFolder;
import de.molaynoxx.amperfi.id3.ID3Helper;
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
import java.util.stream.Collectors;

public class LibraryDatabase {

    private final SQLQueryFactory queryFactory;
    private final ArrayList<LibraryFile> files = new ArrayList<>();
    private final ArrayList<String> folders = new ArrayList<>();

    public LibraryDatabase(File databaseLocation) throws SQLException, InvalidDataException, IOException, UnsupportedTagException {
        if(!databaseLocation.isAbsolute()) databaseLocation = databaseLocation.getAbsoluteFile();
        System.out.println(databaseLocation.getAbsolutePath());
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

        reloadFiles();
        reloadFolders();
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

    /**
     * Returns the list containing all folders that have been added to the library.
     * Used for synchronizing contents from hard drive into the database.
     * @return the list containing all folders that have been added to the library.
     */
    public List<String> getFolders() {
        return this.folders;
    }

    /**
     * Adds a folder to the library. (No validity checking done here)
     * @param path Absolute path to a folder on the hard drive
     */
    public void addFolder(String path) {
        queryFactory.insert(QLibraryFolder.LibraryFolder)
                .values(path)
                .execute();
        folders.add(path);
    }

    /**
     * Removes a folder from database & running instance if it was previously added.
     * @param path Absolute path to a folder on the hard drive
     */
    public void removeFolder(String path) {
        QLibraryFolder libraryFolder = QLibraryFolder.LibraryFolder;
        queryFactory.delete(libraryFolder)
                .where(libraryFolder.path.eq(path))
                .execute();

        reloadFolders();
    }

    private void reloadFolders() {
        folders.clear();
        QLibraryFolder libFolder = QLibraryFolder.LibraryFolder;
        List<LibraryFolder> folders = queryFactory.select(Projections.bean(LibraryFolder.class, libFolder.all())).from(libFolder).fetch();
        this.folders.addAll(folders.stream().map(LibraryFolder::getPath).collect(Collectors.toList()));
    }

    public void reloadFiles() {
        files.clear();
        QLibraryFile libFile = QLibraryFile.LibraryFile;
        List<LibraryFile> files = queryFactory.select(Projections.bean(LibraryFile.class, libFile.all())).from(libFile).fetch();
        this.files.addAll(files);
    }

    /**
     * "Imports" a mp3 file from hard drive to database by reading and caching its id3 tags for later use.
     * @param mp3File location of the mp3 file on the hard drive
     * @throws InvalidDataException
     * @throws IOException thrown if there are any problems related to the file path/reading the file.
     * @throws UnsupportedTagException thrown if the id3 tag library doesn't support the mp3's tag.
     */
    public void importFile(File mp3File) throws InvalidDataException, IOException, UnsupportedTagException {
        QLibraryFile libFile = QLibraryFile.LibraryFile;

        List<LibraryFile> existingFiles = queryFactory.select(libFile).from(libFile)
                .where(libFile.path.eq(mp3File.getAbsolutePath()))
                .fetch();

        ID3Helper id3Helper = new ID3Helper(mp3File);
        if(existingFiles.size() == 0) {
            List<Path<?>> columns = libFile.getColumns();
            Iterator<Path<?>> pathIterator = columns.iterator();
            while (pathIterator.hasNext()) {
                Path<?> column = pathIterator.next();
                if (column.getMetadata().getName().equalsIgnoreCase("file_id")) pathIterator.remove();
                if (column.getMetadata().getName().equalsIgnoreCase("path")) pathIterator.remove();
            }

            columns.add(libFile.path);
            Path<?>[] columnsArray = columns.toArray(new Path<?>[columns.size()]);

            String[] values = new String[columnsArray.length];

            for (ID3Helper.ID3Tag id3Tag : ID3Helper.ID3Tag.values()) {
                int column = -1;
                for (int i = 0; i < columnsArray.length; i++) {
                    if (columnsArray[i].getMetadata().getName().equalsIgnoreCase(id3Tag.toString())) column = i;
                }
                values[column] = id3Helper.getTag(id3Tag);
            }
            values[values.length - 1] = mp3File.getAbsolutePath();

            queryFactory.insert(libFile)
                    .columns(columnsArray)
                    .values((Object[]) values)
                    .execute();
        } else {
            LibraryFile lf = existingFiles.get(0);

            SQLUpdateClause updateOp = queryFactory.update(libFile);

            for(ID3Helper.ID3Tag tag : ID3Helper.ID3Tag.values()) {
                lf.setTag(tag, id3Helper.getTag(tag));
                updateOp.set(libFile.getFieldByTag(tag), lf.getTag(tag));
            }

            updateOp.execute();
        }
    }

    public List<LibraryFile> getLibraryFilesByPredicate(Predicate predicate) {
        QLibraryFile libFile = QLibraryFile.LibraryFile;
        return queryFactory.select(Projections.bean(LibraryFile.class, libFile.all())).from(libFile).where(predicate).fetch();
    }

    public List<String> getTags(ID3Helper.ID3Tag tag) {
        QLibraryFile libFile = QLibraryFile.LibraryFile;
        return queryFactory.select(libFile.getFieldByTag(tag)).distinct().from(libFile).orderBy(libFile.getFieldByTag(tag).asc()).fetch();
    }

}

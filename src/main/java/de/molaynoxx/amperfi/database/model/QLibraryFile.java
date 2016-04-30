package de.molaynoxx.amperfi.database.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import de.molaynoxx.amperfi.database.projection.LibraryFile;
import de.molaynoxx.amperfi.id3.ID3Helper;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QLibraryFile is a Querydsl query type for LibraryFile
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QLibraryFile extends com.querydsl.sql.RelationalPathBase<LibraryFile> {

    private static final long serialVersionUID = 1661823524;

    public static final QLibraryFile LibraryFile = new QLibraryFile("LibraryFile");

    public final StringPath album = createString("album");

    public final StringPath albumartist = createString("albumartist");

    public final StringPath artist = createString("artist");

    public final StringPath bpm = createString("bpm");

    public final StringPath comment = createString("comment");

    public final StringPath composer = createString("composer");

    public final StringPath date = createString("date");

    public final NumberPath<Integer> fileId = createNumber("fileId", Integer.class);

    public final StringPath genre = createString("genre");

    public final StringPath length = createString("length");

    public final StringPath path = createString("path");

    public final StringPath publisher = createString("publisher");

    public final StringPath rating = createString("rating");

    public final StringPath title = createString("title");

    public final StringPath track = createString("track");

    public final StringPath year = createString("year");

    public final com.querydsl.sql.PrimaryKey<LibraryFile> libraryFilePK = createPrimaryKey(fileId);

    public QLibraryFile(String variable) {
        super(LibraryFile.class, forVariable(variable), "null", "LibraryFile");
        addMetadata();
    }

    public QLibraryFile(String variable, String schema, String table) {
        super(LibraryFile.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QLibraryFile(Path<? extends LibraryFile> path) {
        super(path.getType(), path.getMetadata(), "null", "LibraryFile");
        addMetadata();
    }

    public QLibraryFile(PathMetadata metadata) {
        super(LibraryFile.class, metadata, "null", "LibraryFile");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(album, ColumnMetadata.named("album").withIndex(3).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(albumartist, ColumnMetadata.named("albumartist").withIndex(2).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(artist, ColumnMetadata.named("artist").withIndex(4).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(bpm, ColumnMetadata.named("bpm").withIndex(5).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(comment, ColumnMetadata.named("comment").withIndex(10).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(composer, ColumnMetadata.named("composer").withIndex(6).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(date, ColumnMetadata.named("date").withIndex(7).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(fileId, ColumnMetadata.named("file_id").withIndex(0).ofType(Types.INTEGER).withSize(2000000000).withDigits(10));
        addMetadata(genre, ColumnMetadata.named("genre").withIndex(11).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(length, ColumnMetadata.named("length").withIndex(8).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(path, ColumnMetadata.named("path").withIndex(1).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(publisher, ColumnMetadata.named("publisher").withIndex(9).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(rating, ColumnMetadata.named("rating").withIndex(15).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(title, ColumnMetadata.named("title").withIndex(12).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(track, ColumnMetadata.named("track").withIndex(13).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(year, ColumnMetadata.named("year").withIndex(14).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
    }
    public StringPath getFieldByTag(ID3Helper.ID3Tag id3Tag) {
        switch (id3Tag) {
            case ALBUM:
                return album;
            case ALBUMARTIST:
                return albumartist;
            case ARTIST:
                return artist;
            case BPM:
                return bpm;
            case COMMENT:
                return comment;
            case COMPOSER:
                return composer;
            case DATE:
                return date;
            case GENRE:
                return genre;
            case LENGTH:
                return length;
            case PUBLISHER:
                return publisher;
            case RATING:
                return rating;
            case TITLE:
                return title;
            case TRACK:
                return track;
            case YEAR:
                return year;
            default:
                return null;
        }
    }

}


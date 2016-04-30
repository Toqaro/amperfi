package de.molaynoxx.amperfi.id3;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

public class ID3Helper {

    public enum ID3Tag {
        ALBUMARTIST, ALBUM, ARTIST, BPM, COMPOSER, DATE, LENGTH, PUBLISHER, COMMENT, GENRE, TITLE, TRACK, YEAR, RATING
    }

    private final Mp3File mp3File;
    private final boolean hasID3v1Tag;
    private final boolean hasID3v2Tag;

    public ID3Helper(File file) throws InvalidDataException, IOException, UnsupportedTagException {
        this(file.getAbsolutePath());
    }

    public ID3Helper(String fileName) throws InvalidDataException, IOException, UnsupportedTagException {
        this.mp3File = new Mp3File(fileName);
        this.hasID3v1Tag = mp3File.hasId3v1Tag();
        this.hasID3v2Tag = mp3File.hasId3v2Tag();
    }

    public String getTag(ID3Tag id3Tag) {
        switch (id3Tag) {
            case ALBUMARTIST:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getAlbumArtist() : "";
            case ALBUM:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getAlbum() : hasID3v1Tag ? mp3File.getId3v1Tag().getAlbum() : "";
            case ARTIST:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getArtist() : hasID3v1Tag ? mp3File.getId3v1Tag().getArtist() : "";
            case BPM:
                return "" + (hasID3v2Tag ? mp3File.getId3v2Tag().getBPM() : "");
            case COMPOSER:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getComposer() : "";
            case DATE:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getDate() : "";
            case LENGTH:
                return "" + (hasID3v2Tag ? mp3File.getId3v2Tag().getLength() : "");
            case PUBLISHER:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getPublisher() : "";
            case COMMENT:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getComment() : hasID3v1Tag ? mp3File.getId3v1Tag().getComment() : "";
            case GENRE:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getGenreDescription() : hasID3v1Tag ? mp3File.getId3v1Tag().getGenreDescription() : "";
            case TITLE:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getTitle() : hasID3v1Tag ? mp3File.getId3v1Tag().getTitle() : "";
            case TRACK:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getTrack() : hasID3v1Tag ? mp3File.getId3v1Tag().getTrack() : "";
            case YEAR:
                return hasID3v2Tag ? mp3File.getId3v2Tag().getYear() : hasID3v1Tag ? mp3File.getId3v1Tag().getYear() : "";
            case RATING:
                return "" + (hasID3v2Tag ? mp3File.getId3v2Tag().getWmpRating() : "");
            default:
                return "";
        }
    }

}

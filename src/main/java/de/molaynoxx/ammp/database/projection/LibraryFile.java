package de.molaynoxx.ammp.database.projection;

import de.molaynoxx.ammp.id3.ID3Helper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.annotation.Generated;

/**
 * LibraryFile is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class LibraryFile {

    private String album;
    private StringProperty albumProperty = new SimpleStringProperty();

    private String albumartist;
    private StringProperty albumartistProperty = new SimpleStringProperty();

    private String artist;
    private StringProperty artistProperty = new SimpleStringProperty();

    private String bpm;
    private StringProperty bpmProperty = new SimpleStringProperty();

    private String comment;
    private StringProperty commentProperty = new SimpleStringProperty();

    private String composer;
    private StringProperty composerProperty = new SimpleStringProperty();

    private String date;
    private StringProperty dateProperty = new SimpleStringProperty();

    private int fileId;
    private IntegerProperty fileIdProperty = new SimpleIntegerProperty();

    private String genre;
    private StringProperty genreProperty = new SimpleStringProperty();

    private String length;
    private StringProperty lengthProperty = new SimpleStringProperty();

    private String path;
    private StringProperty pathProperty = new SimpleStringProperty();

    private String publisher;
    private StringProperty publisherProperty = new SimpleStringProperty();

    private String rating;
    private StringProperty ratingProperty = new SimpleStringProperty();

    private String title;
    private StringProperty titleProperty = new SimpleStringProperty();

    private String track;
    private StringProperty trackProperty = new SimpleStringProperty();

    private String year;
    private StringProperty yearProperty = new SimpleStringProperty();

    public String getAlbum() {
        return albumProperty.get();
    }

    public StringProperty albumProperty() {
        return albumProperty;
    }

    public void setAlbum(String album) {
        this.albumProperty.set(album);
        this.album = album;
    }

    public String getAlbumartist() {
        return albumartistProperty.get();
    }

    public StringProperty albumartistProperty() {
        return albumartistProperty;
    }

    public void setAlbumartist(String albumartist) {
        this.albumartistProperty.set(albumartist);
        this.albumartist = albumartist;
    }

    public String getArtist() {
        return artistProperty.get();
    }

    public StringProperty artistProperty() {
        return artistProperty;
    }

    public void setArtist(String artist) {
        this.artistProperty.set(artist);
        this.artist = artist;
    }

    public String getBpm() {
        return bpmProperty.get();
    }

    public StringProperty bpmProperty() {
        return bpmProperty;
    }

    public void setBpm(String bpm) {
        this.bpmProperty.set(bpm);
        this.bpm = bpm;
    }

    public String getComment() {
        return commentProperty.get();
    }

    public StringProperty commentProperty() {
        return commentProperty;
    }

    public void setComment(String comment) {
        this.commentProperty.set(comment);
        this.comment = comment;
    }

    public String getComposer() {
        return composerProperty.get();
    }

    public StringProperty composerProperty() {
        return composerProperty;
    }

    public void setComposer(String composer) {
        this.composerProperty.set(composer);
        this.composer = composer;
    }

    public String getDate() {
        return dateProperty.get();
    }

    public StringProperty dateProperty() {
        return dateProperty;
    }

    public void setDate(String date) {
        this.dateProperty.set(date);
        this.date = date;
    }

    public int getFileId() {
        return fileIdProperty.get();
    }

    public IntegerProperty fileIdProperty() {
        return fileIdProperty;
    }

    public void setFileId(int fileId) {
        this.fileIdProperty.set(fileId);
        this.fileId = fileId;
    }

    public String getGenre() {
        return genreProperty.get();
    }

    public StringProperty genreProperty() {
        return genreProperty;
    }

    public void setGenre(String genre) {
        this.genreProperty.set(genre);
        this.genre = genre;
    }

    public String getLength() {
        return lengthProperty.get();
    }

    public StringProperty lengthProperty() {
        return lengthProperty;
    }

    public void setLength(String length) {
        this.lengthProperty.set(length);
        this.length = length;
    }

    public String getPath() {
        return pathProperty.get();
    }

    public StringProperty pathProperty() {
        return pathProperty;
    }

    public void setPath(String path) {
        this.pathProperty.set(path);
        this.path = path;
    }

    public String getPublisher() {
        return publisherProperty.get();
    }

    public StringProperty publisherProperty() {
        return publisherProperty;
    }

    public void setPublisher(String publisher) {
        this.publisherProperty.set(publisher);
        this.publisher = publisher;
    }

    public String getRating() {
        return ratingProperty.get();
    }

    public StringProperty ratingProperty() {
        return ratingProperty;
    }

    public void setRating(String rating) {
        this.ratingProperty.set(rating);
        this.rating = rating;
    }

    public String getTitle() {
        return titleProperty.get();
    }

    public StringProperty titleProperty() {
        return titleProperty;
    }

    public void setTitle(String title) {
        this.titleProperty.set(title);
        this.title = title;
    }

    public String getTrack() {
        return trackProperty.get();
    }

    public StringProperty trackProperty() {
        return trackProperty;
    }

    public void setTrack(String track) {
        this.trackProperty.set(track);
        this.track = track;
    }

    public String getYear() {
        return yearProperty.get();
    }

    public StringProperty yearProperty() {
        return yearProperty;
    }

    public void setYear(String year) {
        this.yearProperty.set(year);
        this.year = year;
    }

    public void setTag(ID3Helper.ID3Tag tag, String value) {
        switch (tag) {
            case ALBUMARTIST:
                albumartistProperty.set(value);
                break;
            case ALBUM:
                albumProperty.set(value);
                break;
            case ARTIST:
                artistProperty.set(value);
                break;
            case BPM:
                bpmProperty.set(value);
                break;
            case COMPOSER:
                composerProperty.set(value);
                break;
            case DATE:
                dateProperty.set(value);
                break;
            case LENGTH:
                lengthProperty.set(value);
                break;
            case PUBLISHER:
                publisherProperty.set(value);
                break;
            case COMMENT:
                commentProperty.set(value);
                break;
            case GENRE:
                genreProperty.set(value);
                break;
            case TITLE:
                titleProperty.set(value);
                break;
            case TRACK:
                trackProperty.set(value);
                break;
            case YEAR:
                yearProperty.set(value);
                break;
            case RATING:
                ratingProperty.set(value);
                break;
        }
    }

    public String getTag(ID3Helper.ID3Tag tag) {
        switch (tag) {
            case ALBUMARTIST:
                return albumartistProperty.get();
            case ALBUM:
                return albumProperty.get();
            case ARTIST:
                return artistProperty.get();
            case BPM:
                return bpmProperty.get();
            case COMPOSER:
                return composerProperty.get();
            case DATE:
                return dateProperty.get();
            case LENGTH:
                return lengthProperty.get();
            case PUBLISHER:
                return publisherProperty.get();
            case COMMENT:
                return commentProperty.get();
            case GENRE:
                return genreProperty.get();
            case TITLE:
                return titleProperty.get();
            case TRACK:
                return trackProperty.get();
            case YEAR:
                return yearProperty.get();
            case RATING:
                return ratingProperty.get();
            default:
                return null;
        }
    }

}


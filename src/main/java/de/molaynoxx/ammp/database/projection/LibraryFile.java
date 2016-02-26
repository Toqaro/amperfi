package de.molaynoxx.ammp.database.projection;

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

    private StringProperty album = new SimpleStringProperty();

    private StringProperty albumartist = new SimpleStringProperty();

    private StringProperty artist = new SimpleStringProperty();

    private StringProperty bpm = new SimpleStringProperty();

    private StringProperty comment = new SimpleStringProperty();

    private StringProperty composer = new SimpleStringProperty();

    private StringProperty date = new SimpleStringProperty();

    private IntegerProperty fileId = new SimpleIntegerProperty();

    private StringProperty genre = new SimpleStringProperty();

    private StringProperty length = new SimpleStringProperty();

    private StringProperty path = new SimpleStringProperty();

    private StringProperty publisher = new SimpleStringProperty();

    private StringProperty rating = new SimpleStringProperty();

    private StringProperty title = new SimpleStringProperty();

    private StringProperty track = new SimpleStringProperty();

    private StringProperty year = new SimpleStringProperty();

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public String getAlbumartist() {
        return albumartist.get();
    }

    public StringProperty albumartistProperty() {
        return albumartist;
    }

    public void setAlbumartist(String albumartist) {
        this.albumartist.set(albumartist);
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public String getBpm() {
        return bpm.get();
    }

    public StringProperty bpmProperty() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm.set(bpm);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getComposer() {
        return composer.get();
    }

    public StringProperty composerProperty() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer.set(composer);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getFileId() {
        return fileId.get();
    }

    public IntegerProperty fileIdProperty() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId.set(fileId);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getLength() {
        return length.get();
    }

    public StringProperty lengthProperty() {
        return length;
    }

    public void setLength(String length) {
        this.length.set(length);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTrack() {
        return track.get();
    }

    public StringProperty trackProperty() {
        return track;
    }

    public void setTrack(String track) {
        this.track.set(track);
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

}


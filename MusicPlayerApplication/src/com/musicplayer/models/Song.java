package com.musicplayer.models;

public class Song {
    private final String title;
    private final String artist;
    private final String path;
    private final int durationSecs;

    public Song(String title, String artist, String path, int durationSecs) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.durationSecs = durationSecs;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public int getDurationSecs() {
        return durationSecs;
    }

    @Override
    public String toString() {
        return String.format("'%s' by %s (Duration: %ds)", title, artist, durationSecs);
    }
}

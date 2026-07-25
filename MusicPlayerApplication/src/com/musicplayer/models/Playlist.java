package com.musicplayer.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Playlist {
    private final String name;
    private final List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new CopyOnWriteArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        if (song != null) {
            songs.add(song);
        }
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public List<Song> getSongs() {
        
        return new ArrayList<>(songs);
    }

    @Override
    public String toString() {
        return String.format("Playlist: %s (%d songs)", name, songs.size());
    }
}

package com.musicplayer;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import com.musicplayer.managers.PlaylistManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MusicPlayerApplication {
    private static MusicPlayerApplication instance;
    private final List<Song> songs;

    private MusicPlayerApplication() {
        this.songs = new CopyOnWriteArrayList<>();
    }

    public static synchronized MusicPlayerApplication getInstance() {
        if (instance == null) {
            instance = new MusicPlayerApplication();
        }
        return instance;
    }
    public Song createSong(String title, String artist, String path, int durationSecs) {
        Song song = new Song(title, artist, path, durationSecs);
        songs.add(song);
        System.out.println("[MusicPlayerApplication] Created song in library: " + song);
        return song;
    }
    public Playlist createPlaylist(String name) {
        return PlaylistManager.getInstance().createPlaylist(name);
    }
    public void addSong(String songName, String playlistName) {
        Song foundSong = null;
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(songName)) {
                foundSong = s;
                break;
            }
        }

        if (foundSong != null) {
            PlaylistManager.getInstance().addSongToPL(playlistName, foundSong);
        } else {
            System.out.printf("[MusicPlayerApplication] Error: Song '%s' not found in system library.%n", songName);
        }
    }

  public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }
}

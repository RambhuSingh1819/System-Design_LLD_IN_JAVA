package com.musicplayer.managers;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PlaylistManager {
    private static PlaylistManager instance;
    private final Map<String, Playlist> playlists;

    private PlaylistManager() {
        this.playlists = new ConcurrentHashMap<>();
    }

   
    public static synchronized PlaylistManager getInstance() {
        if (instance == null) {
            instance = new PlaylistManager();
        }
        return instance;
    }

    
    public Playlist createPlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        Playlist pl = new Playlist(name);
        playlists.put(name, pl);
        System.out.println("[PlaylistManager] Created playlist: " + name);
        return pl;
    }

    public void addSongToPL(String playlistName, Song song) {
        Playlist pl = playlists.get(playlistName);
        if (pl != null && song != null) {
            pl.addSong(song);
            System.out.println("[PlaylistManager] Added '" + song.getTitle() + "' to '" + playlistName + "'");
        } else {
            System.out.println("[PlaylistManager] Error: Playlist '" + playlistName + "' not found or song is null.");
        }
    }

  
    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }
}

package com.musicplayer.strategies;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import java.util.List;

/**
 * Sequential playback strategy iterating through the playlist from index 0 to N-1.
 */
public class SequentialPlayStrategy extends PlayStrategy {
    private int currentIndex = -1;

    @Override
    public void setPlaylist(Playlist pl) {
        super.setPlaylist(pl);
        this.currentIndex = -1;
    }

    @Override
    public Song next() {
        if (playlist == null) return null;
        List<Song> songs = playlist.getSongs();
        if (hasNext()) {
            currentIndex++;
            return songs.get(currentIndex);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (playlist == null) return false;
        return currentIndex + 1 < playlist.getSongs().size();
    }

    @Override
    public boolean hasPrevious() {
        if (playlist == null) return false;
        return currentIndex - 1 >= 0;
    }

    @Override
    public Song previous() {
        if (playlist == null) return null;
        List<Song> songs = playlist.getSongs();
        if (hasPrevious()) {
            currentIndex--;
            return songs.get(currentIndex);
        }
        return null;
    }

    @Override
    public Song getCurrentSong() {
        if (playlist == null) return null;
        List<Song> songs = playlist.getSongs();
        if (currentIndex >= 0 && currentIndex < songs.size()) {
            return songs.get(currentIndex);
        }
        return null;
    }
}

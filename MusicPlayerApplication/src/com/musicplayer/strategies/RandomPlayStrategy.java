package com.musicplayer.strategies;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPlayStrategy extends PlayStrategy {
    private final List<Song> shuffledSongs = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    public void setPlaylist(Playlist pl) {
        super.setPlaylist(pl);
        shuffledSongs.clear();
        if (pl != null) {
            shuffledSongs.addAll(pl.getSongs());
            Collections.shuffle(shuffledSongs);
        }
        this.currentIndex = -1;
    }

    @Override
    public Song next() {
        if (shuffledSongs.isEmpty()) return null;
        if (hasNext()) {
            currentIndex++;
            return shuffledSongs.get(currentIndex);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentIndex + 1 < shuffledSongs.size();
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex - 1 >= 0;
    }

    @Override
    public Song previous() {
        if (shuffledSongs.isEmpty()) return null;
        if (hasPrevious()) {
            currentIndex--;
            return shuffledSongs.get(currentIndex);
        }
        return null;
    }

    @Override
    public Song getCurrentSong() {
        if (shuffledSongs.isEmpty() || currentIndex < 0 || currentIndex >= shuffledSongs.size()) {
            return null;
        }
        return shuffledSongs.get(currentIndex);
    }
}

package com.musicplayer.strategies;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import java.util.ArrayList;
import java.util.List;

public class CustomPlayStrategy extends PlayStrategy {
    private final List<Song> customQueue = new ArrayList<>();
    private int currentIndex = -1;

    @Override
    public void setPlaylist(Playlist pl) {
        super.setPlaylist(pl);
        customQueue.clear();
        if (pl != null) {
            customQueue.addAll(pl.getSongs());
        }
        this.currentIndex = -1;
    }

    public synchronized void enqueue(Song song) {
        if (song != null) {
            customQueue.add(song);
        }
    }

    @Override
    public synchronized Song next() {
        if (customQueue.isEmpty()) return null;
        if (hasNext()) {
            currentIndex++;
            return customQueue.get(currentIndex);
        }
        return null;
    }

    @Override
    public synchronized boolean hasNext() {
        return currentIndex + 1 < customQueue.size();
    }

    @Override
    public synchronized boolean hasPrevious() {
        return currentIndex - 1 >= 0;
    }

    @Override
    public synchronized Song previous() {
        if (customQueue.isEmpty()) return null;
        if (hasPrevious()) {
            currentIndex--;
            return customQueue.get(currentIndex);
        }
        return null;
    }

    @Override
    public synchronized Song getCurrentSong() {
        if (customQueue.isEmpty() || currentIndex < 0 || currentIndex >= customQueue.size()) {
            return null;
        }
        return customQueue.get(currentIndex);
    }
}

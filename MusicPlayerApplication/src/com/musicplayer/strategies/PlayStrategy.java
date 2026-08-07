package com.musicplayer.strategies;

import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;

public abstract class PlayStrategy {
    protected Playlist playlist;

    public void setPlaylist(Playlist pl) {
        this.playlist = pl;
    }

    public abstract Song next();

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();

    public abstract Song previous();

    public abstract Song getCurrentSong();
}

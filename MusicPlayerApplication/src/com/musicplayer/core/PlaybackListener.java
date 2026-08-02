package com.musicplayer.core;

/**
 * Interface to receive notification when a song finishes playing.
 */
public interface PlaybackListener {
    /**
     * Triggered when the current song completes playback naturally.
     */
    void onSongFinished();
}

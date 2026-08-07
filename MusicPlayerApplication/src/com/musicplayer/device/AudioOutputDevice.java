package com.musicplayer.device;

import com.musicplayer.models.Song;

/**
 * Common target interface for all speaker types, implementing the Adapter pattern.
 */
public interface AudioOutputDevice {
    void playAudio(Song song);
}

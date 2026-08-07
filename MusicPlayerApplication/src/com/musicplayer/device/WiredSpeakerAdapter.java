package com.musicplayer.device;

import com.musicplayer.models.Song;
import com.musicplayer.external.WiredSpeakerAPI;

/**
 * Adapter class linking WiredSpeakerAPI to the AudioOutputDevice interface.
 */
public class WiredSpeakerAdapter implements AudioOutputDevice {
    private final WiredSpeakerAPI ws;

    public WiredSpeakerAdapter() {
        this.ws = new WiredSpeakerAPI();
    }

    @Override
    public void playAudio(Song song) {
        if (song != null) {
            ws.playSongViaWiredSpeaker(song.getTitle() + " - " + song.getArtist());
        }
    }
}

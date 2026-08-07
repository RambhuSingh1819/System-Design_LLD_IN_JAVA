package com.musicplayer.device;

import com.musicplayer.models.Song;
import com.musicplayer.external.HeadphoneSpeakerAPI;

/**
 * Adapter class linking HeadphoneSpeakerAPI to the AudioOutputDevice interface.
 */
public class HeadphoneSpeakerAdapter implements AudioOutputDevice {
    private final HeadphoneSpeakerAPI hs;

    public HeadphoneSpeakerAdapter() {
        this.hs = new HeadphoneSpeakerAPI();
    }

    @Override
    public void playAudio(Song song) {
        if (song != null) {
            hs.playSongViaHeadphone(song.getTitle() + " - " + song.getArtist());
        }
    }
}

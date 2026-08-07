package com.musicplayer.device;

import com.musicplayer.models.Song;
import com.musicplayer.external.BluetoothSpeakerAPI;

/**
 * Adapter class linking BluetoothSpeakerAPI to the AudioOutputDevice interface.
 */
public class BluetoothSpeakerAdapter implements AudioOutputDevice {
    private final BluetoothSpeakerAPI bs;

    public BluetoothSpeakerAdapter() {
        this.bs = new BluetoothSpeakerAPI();
    }

    @Override
    public void playAudio(Song song) {
        if (song != null) {
            bs.playSongViaBluetooth(song.getTitle() + " - " + song.getArtist());
        }
    }
}

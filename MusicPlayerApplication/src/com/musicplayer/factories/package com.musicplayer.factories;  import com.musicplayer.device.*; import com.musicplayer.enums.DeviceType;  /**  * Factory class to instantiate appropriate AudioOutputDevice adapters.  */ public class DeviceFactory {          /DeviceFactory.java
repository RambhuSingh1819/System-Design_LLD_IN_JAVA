package com.musicplayer.factories;

import com.musicplayer.device.*;
import com.musicplayer.enums.DeviceType;


public class DeviceFactory {
    

    public static AudioOutputDevice createDevice(DeviceType dt) {
        if (dt == null) {
            throw new IllegalArgumentException("DeviceType cannot be null");
        }
        
        switch (dt) {
            case BLUETOOTH:
                return new BluetoothSpeakerAdapter();
            case WIRED:
                return new WiredSpeakerAdapter();
            case HEADPHONE:
                return new HeadphoneSpeakerAdapter();
            default:
                throw new IllegalArgumentException("Unknown DeviceType: " + dt);
        }
    }
}

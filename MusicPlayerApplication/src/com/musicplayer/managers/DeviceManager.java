package com.musicplayer.managers;

import com.musicplayer.device.AudioOutputDevice;
import com.musicplayer.enums.DeviceType;
import com.musicplayer.factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance;
    private AudioOutputDevice currentDevice;

    private DeviceManager() {
        this.currentDevice = DeviceFactory.createDevice(DeviceType.WIRED);
    }
    public static synchronized DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }
  
    public synchronized void connect(DeviceType dt) {
        if (dt != null) {
            System.out.println("[DeviceManager] Connecting to output source: " + dt);
            this.currentDevice = DeviceFactory.createDevice(dt);
        }
    }

    public synchronized AudioOutputDevice getDevice() {
        return currentDevice;
    }
}

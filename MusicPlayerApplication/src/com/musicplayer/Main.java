package com.musicplayer;

import com.musicplayer.models.Song;
import com.musicplayer.enums.DeviceType;


public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Music Player Application LLD - Java Prototype  ");
        System.out.println("==================================================");

        // 1. Get Application Instance
        MusicPlayerApplication app = MusicPlayerApplication.getInstance();

        // 2. Register Songs
        System.out.println("\n--- [Step 1] Registering Songs in Catalog ---");
        Song s1 = app.createSong("Bohemian Rhapsody", "Queen", "/music/queen_br.mp3", 3);
        Song s2 = app.createSong("Hotel California", "Eagles", "/music/eagles_hc.mp3", 4);
        Song s3 = app.createSong("Imagine", "John Lennon", "/music/lennon_imagine.mp3", 2);
        Song s4 = app.createSong("Beat It", "Michael Jackson", "/music/mj_beatit.mp3", 3);

        // 3. Create Playlist
        System.out.println("\n--- [Step 2] Creating Playlist 'Rock Classics' ---");
        app.createPlaylist("Rock Classics");
        app.addSong("Bohemian Rhapsody", "Rock Classics");
        app.addSong("Hotel California", "Rock Classics");
        app.addSong("Imagine", "Rock Classics");

        // 4. Initialize Facade
        System.out.println("\n--- [Step 3] Loading Playlist into Facade ---");
        MusicPlayerFacade facade = MusicPlayerFacade.getInstance();
        facade.loadPlaylist("Rock Classics");

        // 5. Test Play All (Sequential Strategy)
        System.out.println("\n--- [Step 4] Starting Playback (Sequential Strategy) ---");
        facade.playAll();

        // Let it play for a bit
        sleepMs(150); // bohemian rhapsody is 3s (takes 300ms in simulation)

        // 6. Test Pause & Resume
        System.out.println("\n--- [Step 5] Pausing Playback ---");
        facade.pause();
        sleepMs(200); // stay paused for a bit
        System.out.println("\n--- [Step 6] Resuming Playback ---");
        facade.resume();

        // Let it play through the rest of Bohemian Rhapsody and start Hotel California
        sleepMs(450); // should advance to Hotel California (4s, takes 400ms)

        // 7. Test Device Switch (Adapter + Factory patterns)
        System.out.println("\n--- [Step 7] Switching Audio Output Device ---");
        facade.connectDevices(DeviceType.BLUETOOTH);
        // Let it continue playing Hotel California on Bluetooth
        sleepMs(200);

        facade.connectDevices(DeviceType.HEADPHONE);
        sleepMs(250);

        // 8. Test Skip Next / Prev
        System.out.println("\n--- [Step 8] Manual Skip Next ---");
        facade.playNext(); // Imagine (2s, takes 200ms)
        sleepMs(100);

        System.out.println("\n--- [Step 9] Manual Skip Previous ---");
        facade.playPrevious(); // Back to Hotel California
        sleepMs(100);

        // 9. Test Random Strategy
        System.out.println("\n--- [Step 10] Switching Strategy to RANDOM ---");
        facade.setStrategy("RANDOM");
        facade.playAll();
        sleepMs(500); // Let some random songs play

        // 10. Test Custom Strategy & Queue
        System.out.println("\n--- [Step 11] Custom Queue Strategy ---");
        facade.setStrategy("CUSTOM");
        // Enqueue songs
        facade.enqueue(s4); // Beat It
        facade.enqueue(s1); // Bohemian Rhapsody
        facade.playAll(); // Plays enqueued list
        
        sleepMs(800); // Let it finish playing custom enqueued songs

        // Exit Simulation
        System.out.println("\n--- [Step 12] Stopping Player Simulation ---");
        facade.pause(); // stop any active simulation thread
        
        System.out.println("\n==================================================");
        System.out.println("         Playback Simulation Finished             ");
        System.out.println("==================================================");
        System.exit(0);
    }

    private static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

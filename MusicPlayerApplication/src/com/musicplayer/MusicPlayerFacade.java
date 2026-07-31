package com.musicplayer;

import com.musicplayer.core.AudioEngine;
import com.musicplayer.core.PlaybackListener;
import com.musicplayer.models.Playlist;
import com.musicplayer.models.Song;
import com.musicplayer.enums.DeviceType;
import com.musicplayer.managers.DeviceManager;
import com.musicplayer.managers.PlaylistManager;
import com.musicplayer.managers.StrategyManager;
import com.musicplayer.strategies.PlayStrategy;
import com.musicplayer.strategies.CustomPlayStrategy;

public class MusicPlayerFacade implements PlaybackListener {
    private static MusicPlayerFacade instance;

    private final AudioEngine engine;
    private PlayStrategy strategy;
    private Playlist currentPl;

    private MusicPlayerFacade() {
        this.engine = AudioEngine.getInstance();
        this.engine.setListener(this);

        this.strategy = StrategyManager.getInstance().getStrategy("SEQUENTIAL");
    }

    public static synchronized MusicPlayerFacade getInstance() {
        if (instance == null) {
            instance = new MusicPlayerFacade();
        }
        return instance;
    }

    public void connectDevices(DeviceType dt) {
        DeviceManager.getInstance().connect(dt);
    }

    public void setStrategy(String type) {
        this.strategy = StrategyManager.getInstance().getStrategy(type);
        if (currentPl != null) {
            this.strategy.setPlaylist(currentPl);
        }
        System.out.println("[MusicPlayerFacade] Playback strategy changed to: " + type.toUpperCase());
    }

    public void loadPlaylist(String name) {
        Playlist pl = PlaylistManager.getInstance().getPlaylist(name);
        if (pl != null) {
            this.currentPl = pl;
            this.strategy.setPlaylist(pl);
            System.out.println("[MusicPlayerFacade] Loaded playlist: " + name);
        } else {
            System.out.println("[MusicPlayerFacade] Error: Playlist '" + name + "' not found.");
        }
    }

    public void playSong(Song song) {
        if (song != null) {
            System.out.println("[MusicPlayerFacade] Requesting to play song: " + song.getTitle());
            engine.play(DeviceManager.getInstance().getDevice(), song);
        }
    }

    public void pause() {
        System.out.println("[MusicPlayerFacade] Paused request.");
        engine.pause();
    }

    public void resume() {
        System.out.println("[MusicPlayerFacade] Resume request.");
        engine.resume();
    }

    public void playNext() {
        System.out.println("[MusicPlayerFacade] Next song requested.");
        if (strategy != null && strategy.hasNext()) {
            Song nextSong = strategy.next();
            if (nextSong != null) {
                engine.play(DeviceManager.getInstance().getDevice(), nextSong);
            }
        } else {
            System.out.println("[MusicPlayerFacade] No next song available.");
            engine.stop();
        }
    }

    public void playPrevious() {
        System.out.println("[MusicPlayerFacade] Previous song requested.");
        if (strategy != null && strategy.hasPrevious()) {
            Song prevSong = strategy.previous();
            if (prevSong != null) {
                engine.play(DeviceManager.getInstance().getDevice(), prevSong);
            }
        } else {
            System.out.println("[MusicPlayerFacade] No previous song available.");
        }
    }
    public void playAll() {
        System.out.println("[MusicPlayerFacade] Play All requested.");
        if (currentPl != null && strategy != null) {
            // Re-apply playlist to reset cursors
            strategy.setPlaylist(currentPl);
            if (strategy.hasNext()) {
                Song firstSong = strategy.next();
                if (firstSong != null) {
                    engine.play(DeviceManager.getInstance().getDevice(), firstSong);
                }
            } else {
                System.out.println("[MusicPlayerFacade] Loaded playlist is empty.");
            }
        } else {
            System.out.println("[MusicPlayerFacade] Error: No playlist is currently loaded.");
        }
    }
    public void enqueue(Song song) {
        if (song == null) return;
        System.out.println("[MusicPlayerFacade] Enqueuing: " + song.getTitle());
        PlayStrategy custom = StrategyManager.getInstance().getStrategy("CUSTOM");
        if (custom instanceof CustomPlayStrategy) {
            ((CustomPlayStrategy) custom).enqueue(song);
        }

        if (!(strategy instanceof CustomPlayStrategy)) {
            System.out.println("[MusicPlayerFacade] Note: Enqueued song, but current strategy is " 
                               + strategy.getClass().getSimpleName() 
                               + ". Switch to CUSTOM strategy to play from custom queue.");
        }
    }
    @Override
    public void onSongFinished() {
        System.out.println("[MusicPlayerFacade Callback] Song finished. Auto-playing next...");
        if (strategy != null && strategy.hasNext()) {
            Song nextSong = strategy.next();
            if (nextSong != null) {
                System.out.println("[MusicPlayerFacade Callback] Auto-advancing to: " + nextSong.getTitle());
                engine.play(DeviceManager.getInstance().getDevice(), nextSong);
            }
        } else {
            System.out.println("[MusicPlayerFacade Callback] Finished playlist queue. Stopping engine.");
            engine.stop();
        }
    }
    public Song getCurrentSong() {
        return engine.getCurrentSong();
    }

    public boolean isPlaying() {
        return engine.isPlaying();
    }
}

package com.musicplayer.core;

import com.musicplayer.models.Song;
import com.musicplayer.device.AudioOutputDevice;

/**
 * Singleton class managing the audio playback thread and simulation.
 */
public class AudioEngine {
    private static AudioEngine instance;

    private Song currentSong;
    private Thread playbackThread;
    private volatile boolean isPaused = false;
    private volatile boolean isStopped = true;
    private final Object pauseLock = new Object();
    private PlaybackListener listener;

    private AudioEngine() {
        // Private constructor for Singleton
    }


    public static synchronized AudioEngine getInstance() {
        if (instance == null) {
            instance = new AudioEngine();
        }
        return instance;
    }

    /**
     * Sets the playback listener for song completion callbacks.
     *
     * @param listener the PlaybackListener
     */
    public synchronized void setListener(PlaybackListener listener) {
        this.listener = listener;
    }

    /**
     * Plays the given song on the specified device.
     * If the same song is paused, it resumes it. Otherwise, it stops any active
     * playback and starts the new song on a background thread.
     *
     * @param device the AudioOutputDevice to use
     * @param song   the Song to play
     */
    public synchronized void play(AudioOutputDevice device, Song song) {
        if (song == null || device == null) {
            System.out.println("[AudioEngine] Error: Cannot play song. Song or device is null.");
            return;
        }

        // If the same song is playing and paused, resume it
        if (currentSong != null && currentSong.equals(song) && !isStopped && isPaused) {
            resume();
            return;
        }

        // Otherwise, stop any currently playing song
        stop();

        currentSong = song;
        isPaused = false;
        isStopped = false;

        playbackThread = new Thread(() -> {
            try {
                // Call adapter to output initialization message
                device.playAudio(song);
                
                int total = song.getDurationSecs();
                for (int elapsed = 1; elapsed <= total; elapsed++) {
                    // Check for pause state
                    if (isPaused) {
                        synchronized (pauseLock) {
                            while (isPaused && !isStopped) {
                                System.out.println("[AudioEngine Thread] Playback paused...");
                                pauseLock.wait();
                            }
                        }
                    }

                    // Check for stop state
                    if (isStopped) {
                        break;
                    }

                    // Simulate 1 second of playback in 100 milliseconds for fast testing/interactive demo
                    Thread.sleep(100);
                    System.out.printf("[AudioEngine Thread] '%s' playing: %d/%d secs%n", song.getTitle(), elapsed, total);
                }

                if (!isStopped) {
                    System.out.printf("[AudioEngine Thread] Completed playing: %s%n", song.getTitle());
                    // Asynchronously notify listener to prevent deadlocks in synchronous invocation
                    if (listener != null) {
                        new Thread(() -> listener.onSongFinished(), "Song-Finished-Callback-Thread").start();
                    }
                }

            } catch (InterruptedException e) {
                System.out.printf("[AudioEngine Thread] Playback interrupted for: %s%n", song.getTitle());
            }
        }, "AudioEngine-Playback-Thread");

        playbackThread.start();
    }

    /**
     * Pauses the current song playback.
     */
    public synchronized void pause() {
        if (!isStopped && !isPaused) {
            isPaused = true;
            System.out.println("[AudioEngine] Playback paused.");
        }
    }

    /**
     * Resumes the paused song playback.
     */
    public synchronized void resume() {
        if (!isStopped && isPaused) {
            isPaused = false;
            synchronized (pauseLock) {
                pauseLock.notifyAll();
            }
            System.out.println("[AudioEngine] Playback resumed.");
        }
    }

    /**
     * Stops the playback and cleans up the thread.
     */
    public synchronized void stop() {
        isStopped = true;
        isPaused = false;
        
        synchronized (pauseLock) {
            pauseLock.notifyAll(); // wake up thread if it's waiting on pause
        }

        if (playbackThread != null && playbackThread.isAlive()) {
            playbackThread.interrupt();
            try {
                playbackThread.join(300); // Wait up to 300ms for thread to exit cleanly
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        currentSong = null;
        playbackThread = null;
    }

    /**
     * Gets the currently playing song.
     *
     * @return the active Song, or null
     */
    public synchronized Song getCurrentSong() {
        return currentSong;
    }

    /**
     * Checks if the engine is currently playing.
     *
     * @return true if playing, false if paused or stopped
     */
    public synchronized boolean isPlaying() {
        return !isStopped && !isPaused;
    }
}

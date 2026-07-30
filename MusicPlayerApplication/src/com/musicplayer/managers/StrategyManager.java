package com.musicplayer.managers;

import com.musicplayer.strategies.*;

public class StrategyManager {
    private static StrategyManager instance;

    private final PlayStrategy sequentialStrategy;
    private final PlayStrategy randomStrategy;
    private final PlayStrategy customStrategy;

    private StrategyManager() {
        this.sequentialStrategy = new SequentialPlayStrategy();
        this.randomStrategy = new RandomPlayStrategy();
        this.customStrategy = new CustomPlayStrategy();
    }

    public static synchronized StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }

    public PlayStrategy getStrategy(String type) {
        if (type == null) {
            return sequentialStrategy;
        }

        String normType = type.toUpperCase().trim();
        switch (normType) {
            case "SEQUENTIAL":
                return sequentialStrategy;
            case "RANDOM":
                return randomStrategy;
            case "CUSTOM":
                return customStrategy;
            default:
                System.out.println("[StrategyManager] Warning: Unknown strategy '" + type + "'. Defaulting to Sequential.");
                return sequentialStrategy;
        }
    }
}

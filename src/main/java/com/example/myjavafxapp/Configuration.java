package com.example.myjavafxapp;

public class Configuration {
    private final int checkouts;
    private final int cooks;
    private final String strategy;
    private final int minTime;

    public Configuration(int checkouts, int cooks, String strategy, int minTime) {
        this.checkouts = checkouts;
        this.cooks = cooks;
        this.strategy = strategy;
        this.minTime = minTime;
    }

    public int getCheckouts() {
        return checkouts;
    }

    public int getCooks() {
        return cooks;
    }

    public String getStrategy() {
        return strategy;
    }

    public int getMinTime() {
        return minTime;
    }
}
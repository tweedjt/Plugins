package me.tweedjt.goodbyemobs.util;

import me.tweedjt.goodbyemobs.GoodbyeMobs;

public class GoodByeMobsConfig {

    private int getRadius = 25;

    public int getRadius() {

        return this.getRadius;
    }

    public GoodByeMobsConfig(GoodbyeMobs plugin) {
        try {
            if (plugin.getConfig().contains("Protection_Radius")) {
                if (plugin.getConfig().isInt("Protection_Radius")) {
                    this.getRadius = plugin.getConfig().getInt("Protection_Radius");
                } else {
                    getRadius = 25;
                }
            } else {
                getRadius = 25;
            }
        } catch (Exception ex) {
            getRadius = 25;
        }
    }
}


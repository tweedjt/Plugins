package me.tweedjt.goodbyemobs.util;

import me.tweedjt.goodbyemobs.GoodbyeMobs;

public class GoodByeMobsConfig {

    private int getRadius = 25;
    private String messagePrefix = "&6[&fAutoSmelt&6] &r";

    public int getRadius() {

        return this.getRadius;
    }

    public String getMessagePrefix() {
        return this.messagePrefix;
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

        // message_prefix
        try {
            if (plugin.getConfig().contains("message_prefix")) {
                if (plugin.getConfig().isString("message_prefix")) {
                    this.messagePrefix = plugin.getConfig().getString("message_prefix");
                } else {
                    messagePrefix = "&d[&fGoodByeMobs&d] &r";
                    // message_prefix is not a string value
                }
            } else {
                messagePrefix = "&d[&fGoodByeMobs&d] &r";
                // message_prefix does not exist in config file
            }
        } catch (Exception ex) {
            messagePrefix = "&d[&fGoodByeMobs&d] &r";
            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)

        }
    }
}


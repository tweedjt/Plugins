package me.tweedjt.goodbyemobs.util;

import me.tweedjt.goodbyemobs.GoodbyeMobs;

public class GoodByeMobsConfig {

    private boolean getKnockBack = false;
    private int Radius = 25;
    private int RunTime = 5;
    private String messagePrefix = "&6[&fAutoSmelt&6] &r";

    public boolean getKnockBack() {
        return this.getKnockBack;
    }

    public int getRadius() {
        return this.Radius;
    }

    public int getRunTime() {
        return this.RunTime;
    }

    public String getMessagePrefix() {
        return this.messagePrefix;
    }

    public GoodByeMobsConfig(GoodbyeMobs plugin) {
        try {
            if (plugin.getConfig().contains("Protection_Radius")) {
                if (plugin.getConfig().isInt("Protection_Radius")) {
                    this.Radius = plugin.getConfig().getInt("Protection_Radius");
                } else {
                    Radius = 25;
                }
            } else {
                Radius = 25;
            }
        } catch (Exception ex) {
            Radius = 25;
        }

        try {
            if (plugin.getConfig().contains("Run_Time")) {
                if (plugin.getConfig().isInt("Run_Time")) {
                    this.RunTime = plugin.getConfig().getInt("Run_Time");
                } else {
                    RunTime = 5;
                }
            } else {
                RunTime = 5;
            }
        } catch (Exception ex) {
            RunTime = 5;
        }

        //Knockback enabled?
        try {
            if (plugin.getConfig().contains("knockback_enabled")) {
                if (plugin.getConfig().isBoolean("knockback_enabled")) {
                    this.getKnockBack = plugin.getConfig().getBoolean("knockback_enabled");
                } else {
                    getKnockBack = false;
                }
            } else {
                getKnockBack = false;
            }
        } catch (Exception ex) {
            getKnockBack = false;
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


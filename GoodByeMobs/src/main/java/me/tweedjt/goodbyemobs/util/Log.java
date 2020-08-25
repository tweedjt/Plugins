package me.tweedjt.goodbyemobs.util;

import me.tweedjt.goodbyemobs.GoodbyeMobs;

public class Log {
    // Send a message to the console
    public static void debugToConsole(String message) {
        if (GoodbyeMobs.getInstance().getDebug()) {
            System.out.println(Misc.colorToString("&4[&fGoodByeMobs Debug&4]&r " + message));
        }
    }
    // Send a message to the console
    public static void logToConsole(String message) {
        System.out.println(Misc.colorToString("&3[&7GoodByeMobs Log&3]&r " + message));
    }
    // Send a message to the console without color
    public static void logToConsoleNoColor(String message) {
        System.out.println("[GoodByeMobs Log] " + message);
    }
}

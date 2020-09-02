package me.tweedjt.reputation.util;


import me.tweedjt.reputation.Reputation;

public class Log {
    // Send a message to the console
    public static void debugToConsole(String message) {
        if (Reputation.getInstance().getDebug()) {
            System.out.println(Misc.colorToString("&4[&fItemLore Debug&4]&r " + message));
        }
    }
    // Send a message to the console
    public static void logToConsole(String message) {
        System.out.println(Misc.colorToString("&3[&7ItemLore Log&3]&r " + message));
    }
    // Send a message to the console without color
    public static void logToConsoleNoColor(String message) {
        System.out.println("[ItemLore Log] " + message);
    }
}

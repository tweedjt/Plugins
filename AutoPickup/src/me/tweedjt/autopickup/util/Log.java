package me.tweedjt.autopickup.util;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.util.Misc;

public class Log {
    // Send a message to the console
    public static void debugToConsole(String message) {
    	if (AutoPickup.getInstance().getDebug()) {
    		System.out.println(Misc.colorToString("&4[&fAutoPickup Debug&4]&r " + message));
    	}
    }
    // Send a message to the console
    public static void logToConsole(String message) {
    	System.out.println(Misc.colorToString("&3[&7AutoPickup Log&3]&r " + message));  
    }
    // Send a message to the console without color
    public static void logToConsoleNoColor(String message) {
    	System.out.println("[AutoPickup Log] " + message);  
    }
}

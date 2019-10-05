package me.tweedjt.autosmelt.util;

import me.tweedjt.autosmelt.AutoSmelt;

public class Log {
    // Send a message to the console
    public static void debugToConsole(String message) {
    	if (AutoSmelt.getInstance().getDebug()) {
    		System.out.println(Misc.colorToString("&4[&fAutoSmelt Debug&4]&r " + message));
    	}
    }
    // Send a message to the console
    public static void logToConsole(String message) {
    	System.out.println(Misc.colorToString("&3[&7AutoSmelt Log&3]&r " + message));  
    }
    // Send a message to the console without color
    public static void logToConsoleNoColor(String message) {
    	System.out.println("[AutoSmelt Log] " + message);  
    }
}

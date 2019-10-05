package me.tweedjt.timedpermissions.util;

import me.tweedjt.timedpermissions.TimedPermissions;

	public class Log {
	    // Send a message to the console
	    public static void debugToConsole(String message) {
	    	if (TimedPermissions.getInstance().getDebug()) {
	    		System.out.println(Misc.colorToString("&4[&fTPerms Debug&4]&r " + message));
	    	}
	    }
	    // Send a message to the console
	    public static void logToConsole(String message) {
	    	System.out.println(Misc.colorToString("&3[&7TPerms Log&3]&r " + message));  
	    }
	    // Send a message to the console without color
	    public static void logToConsoleNoColor(String message) {
	    	System.out.println("[TPerms Log] " + message);  
	    }
	}

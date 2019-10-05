package me.tweedjt.timedpermissions.util;

import me.tweedjt.timedpermissions.TimedPermissions;

public class TimedPermissionsConfig {
		
	    private boolean autoPickup = false;
	    private boolean autoSmelt = false;
	    private boolean expDrop = false;
	    private String messagePrefix = "&6[&fTimedPermissions&6] &r";
	    
	    
	    public boolean getAutoPickup() {
		   return this.autoPickup;
	   }
	   public boolean getTimedPermissions() {
		   return this.autoSmelt;
	   }
	   public boolean getExpDrops() {
		   return this.expDrop;
	   }
	    public String getMessagePrefix() {
	        return this.messagePrefix;
	    }


	    public TimedPermissionsConfig(TimedPermissions plugin) {
	    	
	    	//TimedPermissions default
	        try {
	        	if (plugin.getConfig().contains("auto_smelt"))
	        	{
	        		if (plugin.getConfig().isBoolean("auto_smelt")) {
	        			this.autoSmelt = plugin.getConfig().getBoolean("auto_smelt");
	        		} else {
	        			autoSmelt = false;
	        		}
	        	}
	        }
	        	catch (Exception ex) {
	        		autoSmelt = true;
	        	}
	        
	        
	        //AutoPickup Default
	        try {
	        	if (plugin.getConfig().contains("auto_pickup"))
	        	{
	        		if (plugin.getConfig().isBoolean("auto_pickup")) {
	        			this.autoPickup = plugin.getConfig().getBoolean("auto_pickup");
	        		} else {
	        			autoPickup = false;
	        		}
	        	}
	        }
	        	catch (Exception ex) {
	        		autoPickup = true;
	        	}
	        
	        //EXP Orb Drops
	        try {
	        	if (plugin.getConfig().contains("exp_drops"))
	        	{
	        		if (plugin.getConfig().isBoolean("exp_drops")) {
	        			this.expDrop = plugin.getConfig().getBoolean("exp_drops");
	        		} else {
	        			expDrop = false;
	        		}
	        	}
	        }
	        	catch (Exception ex) {
	        		autoPickup = true;
	        	}

	        
	        // message_prefix
	        try {
	            if (plugin.getConfig().contains("message_prefix"))
	            {
	                if (plugin.getConfig().isString("message_prefix")) {
	                    this.messagePrefix = plugin.getConfig().getString("message_prefix");
	                } else {
	                    messagePrefix = "&d[&fTimedPermissions&d] &r";
	                    // message_prefix is not a string value
	                }
	            } else {
	                messagePrefix = "&d[&fTimedPermissions&d] &r";
	                // message_prefix does not exist in config file
	            }
	        } catch (Exception ex) {
	            messagePrefix = "&d[&fTimedPermissions&d] &r";
	            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)
	         
	        }
	    }
	}

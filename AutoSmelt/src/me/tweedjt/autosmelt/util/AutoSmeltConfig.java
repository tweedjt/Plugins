package me.tweedjt.autosmelt.util;

import me.tweedjt.autosmelt.AutoSmelt;

public class AutoSmeltConfig {
	
    private boolean autoPickup = false;
    private boolean autoSmelt = false;
    private boolean expDrop = false;
    private int expValue = 1;
    private String messagePrefix = "&6[&fAutoSmelt&6] &r";
    private String autoSmeltOffMessage = "AutoSmelt has been turned off";
    private String autoSmeltOnMessage = "AutoSmelt has been turned on";

   public boolean getAutoPickup() {
	   return this.autoPickup;
   }
   public boolean getAutoSmelt() {
	   return this.autoSmelt;
   }
   public boolean getExpDrops() {
	   return this.expDrop;
   }
   public int getExpValue() {
	   return this.expValue;
   }
    public String getMessagePrefix() {
        return this.messagePrefix;
    }
    public String getAutoSmeltOnMessage() {
        return this.autoSmeltOffMessage;
    }
    public String getAutoSmeltOffMessage() {
        return this.autoSmeltOnMessage;
    }



    public AutoSmeltConfig(AutoSmelt plugin) {
    	
    	//AutoSmelt default
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
                    messagePrefix = "&d[&fAutoSmelt&d] &r";
                    // message_prefix is not a string value
                }
            } else {
                messagePrefix = "&d[&fAutoSmelt&d] &r";
                // message_prefix does not exist in config file
            }
        } catch (Exception ex) {
            messagePrefix = "&d[&fAutoSmelt&d] &r";
            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)
         
        }
        
        
        try {
            if (plugin.getConfig().contains("autosmelt_off_message"))
            {
                if (plugin.getConfig().isString("autosmelt_off_message")) {
                    this.autoSmeltOffMessage = plugin.getConfig().getString("autosmelt_off_message");
                } else {
                	autoSmeltOffMessage = "AutoSmelt has been turned off";
                    // message_prefix is not a string value
                }
            } else {
            	autoSmeltOffMessage = "AutoSmelt has been turned off";
                // message_prefix does not exist in config file
            }
        } catch (Exception ex) {
        	autoSmeltOffMessage = "AutoSmelt has been turned off";
            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)
         
        }
        
        
        try {
            if (plugin.getConfig().contains("autosmelt_on_message"))
            {
                if (plugin.getConfig().isString("autosmelt_on_message")) {
                    this.autoSmeltOnMessage = plugin.getConfig().getString("autosmelt_on_message");
                } else {
                	autoSmeltOnMessage = "AutoSmelt has been turned on";
                    // message_prefix is not a string value
                }
            } else {
            	autoSmeltOnMessage = "AutoSmelt has been turned on";
                // message_prefix does not exist in config file
            }
        } catch (Exception ex) {
        	autoSmeltOnMessage = "AutoSmelt has been turned on";
            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)
         
        }
        try {
        	if (plugin.getConfig().contains("exp_value"))
        	{
        		if (plugin.getConfig().isInt("exp_value")) {
        			this.expValue = plugin.getConfig().getInt("exp_value");
        		} else {
        			expValue = 1;
        		}
        	} else {
        		expValue = 1;
        	}
        	} catch (Exception ex) {
        		expValue = 1;
        	}
    }
}
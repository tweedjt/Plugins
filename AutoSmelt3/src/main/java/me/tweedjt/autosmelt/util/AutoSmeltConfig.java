package me.tweedjt.autosmelt.util;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.tweedjt.autosmelt.AutoSmelt;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("ALL")
public class AutoSmeltConfig {

    private boolean autoPickup = false;
    private boolean autoSmelt = false;
    private boolean expDrop = false;
    private boolean fortuneDrop = false;
    private int ironExp = 1;
    private int goldExp = 1;
    private int netherScrapExp = 2;
    private String messagePrefix = "&6[&fAutoSmelt&6] &r";
    private String autoSmeltOffMessage = "AutoSmelt has been turned off";
    private String autoSmeltOnMessage = "AutoSmelt has been turned on";
    //NEW CODE
    private boolean dropAmount = false;
    private int maxDropAmount = 2;
    private int minDropAmount = 1;

    public boolean getAutoPickup() {
        return this.autoPickup;
    }

    public boolean getAutoSmelt() {
        return this.autoSmelt;
    }

    public boolean getExpDrops() {
        return this.expDrop;
    }

    public boolean getFortuneDrops() {
        return this.fortuneDrop;
    }

    public int getIronExp() {
        return this.ironExp;
    }

    public int getGoldExp() {
        return this.goldExp;
    }

    public int getNetherScrapExp() {
        return this.netherScrapExp;
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

    //NEW CODE
    public boolean getDropAmount() { return this.dropAmount; }

    public int getMaxDropAmount() { return this.maxDropAmount; }

    public int getMinDropAmount() { return this.minDropAmount; }


    @SuppressWarnings("ConstantConditions")
    public AutoSmeltConfig(AutoSmelt plugin) {

        FileConfiguration config = plugin.getConfig();

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


        //Random Drop Amount
        try {
            if (plugin.getConfig().contains("random_drop_amount"))
            {
                if (plugin.getConfig().isBoolean("random_drop_amount")) {
                    this.dropAmount = plugin.getConfig().getBoolean("random_drop_amount");
                } else {
                    dropAmount = false;
                }
            }
        }
        catch (Exception ex) {
            dropAmount = true;
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
            expDrop = true;
        }
        //EXP Orb Drops
        try {
            if (plugin.getConfig().contains("fortune_drops"))
            {
                if (plugin.getConfig().isBoolean("fortune_drops")) {
                    this.fortuneDrop = plugin.getConfig().getBoolean("fortune_drops");
                } else {
                    fortuneDrop = false;
                }
            }
        }
        catch (Exception ex) {
            fortuneDrop = true;
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
            //INTEGERS

        try {
            if (plugin.getConfig().contains("Max-drop-amount"))
            {
                if (plugin.getConfig().isInt("Max-drop-amount")) {
                    this.maxDropAmount = plugin.getConfig().getInt("Max-drop-amount");
                } else {
                    maxDropAmount = 2;
                }
            } else {
                maxDropAmount = 2;
            }
        } catch (Exception ex) {
            maxDropAmount = 2;
        }

        try {
            if (plugin.getConfig().contains("Min-drop-amount"))
            {
                if (plugin.getConfig().isInt("Min-drop-amount")) {
                    this.minDropAmount = plugin.getConfig().getInt("Min-drop-amount");
                } else {
                    minDropAmount = 1;
                }
            } else {
                minDropAmount = 1;
            }
        } catch (Exception ex) {
            minDropAmount = 1;
        }

        try {
            if (plugin.getConfig().contains("iron_exp"))
            {
                if (plugin.getConfig().isInt("iron_exp")) {
                    this.ironExp = plugin.getConfig().getInt("iron_exp");
                } else {
                    ironExp = 1;
                }
            } else {
                ironExp = 1;
            }
        } catch (Exception ex) {
            ironExp = 1;
        }

        try {
            if (plugin.getConfig().contains("gold_exp"))
            {
                if (plugin.getConfig().isInt("gold_exp")) {
                    this.goldExp = plugin.getConfig().getInt("gold_exp");
                } else {
                    goldExp = 1;
                }
            } else {
                goldExp = 1;
            }
        } catch (Exception ex) {
            goldExp = 1;
        }

        try {
            if (plugin.getConfig().contains("nether_scrap_exp"))
            {
                if (plugin.getConfig().isInt("nether_scrap_exp")) {
                    this.netherScrapExp = plugin.getConfig().getInt("nether_scrap_exp");
                } else {
                    netherScrapExp = 2;
                }
            } else {
                netherScrapExp = 2;
            }
        } catch (Exception ex) {
            netherScrapExp = 2;
        }
    }
}

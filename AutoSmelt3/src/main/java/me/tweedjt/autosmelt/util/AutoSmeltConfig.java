package me.tweedjt.autosmelt.util;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.tweedjt.autosmelt.AutoSmelt;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class AutoSmeltConfig {

    private AutoSmelt plugin;
    private YmlParser ymlParser;
    private boolean autoPickup = false;
    private boolean autoSmelt = false;
    private boolean expDrop = false;
    private boolean fortuneDrop = false;
    private int ironExp = 1;
    private int goldExp = 1;
    private int netherScrapExp = 2;
    private String messagePrefix = "&d[&fAutoSmelt&d] &r";
    private String autoSmeltOffMessage = "AutoSmelt has been turned &cOFF";
    private String autoSmeltOnMessage = "AutoSmelt has been turned &aON";
    //NEW CODE
    private boolean dropAmount = false;
    private int maxDropAmount = 2;
    private int minDropAmount = 1;
    private Set<Material> ironDrops = new HashSet<>();
    private Set<Material> deepSlateIronDrops = new HashSet<>();
    private Set<Material> goldDrops = new HashSet<>();
    private Set<Material> deepSlateGoldDrops = new HashSet<>();
    private Set<Material> copperDrops = new HashSet<>();
    private Set<Material> deepSlateCopperDrops = new HashSet<>();
    private Set<Material> netherScrapDrops = new HashSet<>();

    public AutoSmeltConfig(AutoSmelt plugin) {
        this.plugin = plugin;
        File configFile = Misc.getConfigYml(this.plugin);
        ymlParser = new YmlParser(plugin, configFile);
        load();
    }

    public void load() {

        //region Auto Smelt
        YmlParser.ConfigReturn crAutoSmelt = ymlParser.getBooleanValue(
                "auto_smelt",
                false,
                false
        );
        if (crAutoSmelt.success()) {
            this.autoSmelt = crAutoSmelt.getBoolean();
        }
        //endregion
        //region Auto Pickup
        YmlParser.ConfigReturn crAutoPickup = ymlParser.getBooleanValue(
                "auto_pickup",
                false,
                false
        );
        if (crAutoPickup.success()) {
            this.autoPickup = crAutoPickup.getBoolean();
        }
        //endregion
        //region Drop Experience Orbs
        YmlParser.ConfigReturn crExpDrops = ymlParser.getBooleanValue(
                "exp_drops",
                false,
                false
        );
        if (crExpDrops.success()) {
            this.expDrop = crExpDrops.getBoolean();
        }
        //endregion
        //region Fortune Drops
        YmlParser.ConfigReturn crFortuneDrops = ymlParser.getBooleanValue(
                "fortune_drops",
                false,
                false
        );
        if (crFortuneDrops.success()) {
            this.fortuneDrop = crFortuneDrops.getBoolean();
        }
        //endregion
        //region Iron Experience Amount
        YmlParser.ConfigReturn crIronExp = ymlParser.getIntValue(
                "iron_exp",
                0,
                false
        );
        if (crIronExp.success()) {
            this.ironExp = crIronExp.getInt();
        }
        //endregion
        //region Gold Experience Amount
        YmlParser.ConfigReturn crGoldExp = ymlParser.getIntValue(
                "gold_exp",
                0,
                false
        );
        if (crGoldExp.success()) {
            this.goldExp = crGoldExp.getInt();
        }
        //endregion
        //region Nether Scrap Experience Amount
        YmlParser.ConfigReturn crNetherScrapExp = ymlParser.getIntValue(
                "nether_scrap_exp",
                0,
                false
        );
        if (crNetherScrapExp.success()) {
            this.netherScrapExp = crNetherScrapExp.getInt();
        }
        //endregion
        //region Message Prefix
        YmlParser.ConfigReturn crMessagePrefix = ymlParser.getStringValue(
                "message_prefix",
                "&d[&fAutoSmelt&d] &r",
                false
        );
        if (crMessagePrefix.success()) {
            this.messagePrefix = crMessagePrefix.getString();
        }
        //endregion
        //region AutoSmelt ON Message
        YmlParser.ConfigReturn crOnMessage = ymlParser.getStringValue(
                "autosmelt_on_message",
                "AutoSmelt has been turned &aON",
                false
        );
        if (crOnMessage.success()) {
            this.autoSmeltOnMessage = crOnMessage.getString();
        }
        //endregion
        //region AutoSmelt OFF Message
        YmlParser.ConfigReturn crOffMessage = ymlParser.getStringValue(
                "autosmelt_off_message",
                "AutoSmelt has been turned &cOFF",
                false
        );
        if (crOffMessage.success()) {
            this.autoSmeltOffMessage = crOffMessage.getString();
        }
        //endregion
        //region Random Drop Amount
        YmlParser.ConfigReturn crRandomDropAmt = ymlParser.getBooleanValue(
                "random_drop_amount",
                false,
                false
        );
        if (crRandomDropAmt.success()) {
            this.dropAmount = crRandomDropAmt.getBoolean();
        }
        //endregion
        //region Max Drop Amount
        YmlParser.ConfigReturn crMaxDropAmt = ymlParser.getIntValue(
                "max_drop_amount",
                0,
                false
        );
        if (crMaxDropAmt.success()) {
            this.maxDropAmount = crMaxDropAmt.getInt();
        }
        //endregion
        //region Min Drop Amount
        YmlParser.ConfigReturn crMinDropAmount = ymlParser.getIntValue(
                "min_drop_amount",
                0,
                false
        );
        if (crMinDropAmount.success()) {
            this.minDropAmount = crMinDropAmount.getInt();
        }
        //endregion
        //region Block Drops
        // Iron Ore
        YmlParser.ConfigReturn crIronDrops = ymlParser.getStringList(
                "blocks.IRON_ORE",
                false
        );
        if (crIronExp.success()) {
            if (ironDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                ironDrops = new HashSet<>(); 
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String ironDrop : crIronDrops.getStringList()) {
                Material ironDropMaterial = Misc.materialFromName(ironDrop);
                if (!ironDropMaterial.equals(Material.AIR)) {
                    ironDrops.add(ironDropMaterial);
                }
            }
        }
        // Deep Slate Iron Ore
        YmlParser.ConfigReturn crDeepSlateIronDrops = ymlParser.getStringList(
                "blocks.DEEPSLATE_IRON_ORE",
                false
        );
        if (crDeepSlateIronDrops.success()) {
            if (deepSlateIronDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                deepSlateIronDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String deepSlateIronDrop : crDeepSlateIronDrops.getStringList()) {
                Material deepSlateIronDropMaterial = Misc.materialFromName(deepSlateIronDrop);
                if (!deepSlateIronDropMaterial.equals(Material.AIR)) {
                    deepSlateIronDrops.add(deepSlateIronDropMaterial);
                }
            }
        }
        // Gold Ore
        YmlParser.ConfigReturn crGoldDrops = ymlParser.getStringList(
                "blocks.GOLD_ORE",
                false
        );
        if (crGoldDrops.success()) {
            if (goldDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                goldDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String goldDrop : crGoldDrops.getStringList()) {
                Material goldDropMaterial = Misc.materialFromName(goldDrop);
                if (!goldDropMaterial.equals(Material.AIR)) {
                    goldDrops.add(goldDropMaterial);
                }
            }
        }
        // Deep Slate Gold Ore
        YmlParser.ConfigReturn crDeepSlateGoldDrops = ymlParser.getStringList(
                "blocks.DEEPSLATE_GOLD_ORE",
                false
        );
        if (crDeepSlateGoldDrops.success()) {
            if (deepSlateGoldDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                deepSlateGoldDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String deepSlateGoldDrop : crDeepSlateGoldDrops.getStringList()) {
                Material deepSlateGoldDropMaterial = Misc.materialFromName(deepSlateGoldDrop);
                if (!deepSlateGoldDropMaterial.equals(Material.AIR)) {
                    deepSlateGoldDrops.add(deepSlateGoldDropMaterial);
                }
            }
        }
        // Copper Ore
        YmlParser.ConfigReturn crCopperDrops = ymlParser.getStringList(
                "blocks.COPPER_ORE",
                false
        );
        if (crCopperDrops.success()) {
            if (copperDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                copperDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String copperDrop : crCopperDrops.getStringList()) {
                Material copperDropMaterial = Misc.materialFromName(copperDrop);
                if (!copperDropMaterial.equals(Material.AIR)) {
                    copperDrops.add(copperDropMaterial);
                }
            }
        }
        // Deep Slate Copper Ore
        YmlParser.ConfigReturn crDeepSlateCopperDrops = ymlParser.getStringList(
                "blocks.DEEPSLATE_COPPER_ORE",
                false
        );
        if (crDeepSlateCopperDrops.success()) {
            if (deepSlateCopperDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                deepSlateCopperDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String deepSlateCopperDrop : crDeepSlateCopperDrops.getStringList()) {
                Material deepSlateCopperDropMaterial = Misc.materialFromName(deepSlateCopperDrop);
                if (!deepSlateCopperDropMaterial.equals(Material.AIR)) {
                    deepSlateCopperDrops.add(deepSlateCopperDropMaterial);
                }
            }
        }
        // Ancient Debris
        YmlParser.ConfigReturn crAncientDebris = ymlParser.getStringList(
                "blocks.ANCIENT_DEBRIS",
                false
        );
        if (crAncientDebris.success()) {
            if (netherScrapDrops == null) {
                // This should never be null, but if it is, set it to a blank hashset
                netherScrapDrops = new HashSet<>();
            }
            // Loop through all the possible drops, convert the string to a material, if it is a valid
            // material (does not return AIR), add it to the hashset
            for (String ancientDebrisDrop : crAncientDebris.getStringList()) {
                Material ancientDebrisDropMaterial = Misc.materialFromName(ancientDebrisDrop);
                if (!ancientDebrisDropMaterial.equals(Material.AIR)) {
                    netherScrapDrops.add(ancientDebrisDropMaterial);
                }
            }
        }
        //endregion

    }

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

    public Set<Material> ironDrops() { return this.ironDrops; }
    public Set<Material> deepSlateIronDrops() { return this.deepSlateIronDrops; }
    public Set<Material> goldDrops() { return this.goldDrops; }
    public Set<Material> deepSlateGoldDrops() { return this.deepSlateGoldDrops; }
    public Set<Material> copperDrops() { return this.copperDrops; }
    public Set<Material> deepSlateCopperDrops() { return this.deepSlateCopperDrops; }
    public Set<Material> ancientDebrisDrops() { return this.ancientDebrisDrops(); }


    /*
    //ConstantConditions
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
     */
}

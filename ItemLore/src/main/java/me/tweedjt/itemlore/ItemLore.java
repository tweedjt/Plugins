package me.tweedjt.itemlore;

import me.tweedjt.itemlore.commands.LoreCommand;
import me.tweedjt.itemlore.commands.ReloadCommandListener;
import me.tweedjt.itemlore.util.ItemLoreConfig;
import me.tweedjt.itemlore.util.Log;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLore extends JavaPlugin {

    // Debug - set to true for debugging
    private final boolean debug = false;
    public boolean getDebug() {
        return debug;
    }

    // Main
    private static ItemLore instance;
    public static ItemLore getInstance() {
        return instance;
    }

    private ItemLoreConfig itemLoreConfig;
    public void loadItemLoreConfig() {

        this.itemLoreConfig = new ItemLoreConfig(this);
    }

    public ItemLoreConfig getItemLoreConfig() {
        return this.itemLoreConfig;
    }

    // On Enable/Disable
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loadItemLoreConfig();


        Log.logToConsole("Starting ItemLore");

        instance = this; // Setting the instance to this

        // Add command listeners
        try {
            getCommand("lore").setExecutor(new LoreCommand()); // lore
            getCommand("loreload").setExecutor(new ReloadCommandListener());
        } catch (Exception ex) {
            // If we have an error, output it
            Log.logToConsole("Unexpected Error - " + ex.getMessage());
        }
    }

    @Override
    public void onDisable() {

        Log.logToConsole("Disabling ItemLore");
    }

}
package me.tweedjt.itemlore;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemLore extends JavaPlugin {

    // Main
    private static me.tweedjt.itemlore.ItemLore instance;
    public static me.tweedjt.itemlore.ItemLore getInstance() {
        return instance;
    }

    // On Enable/Disable
    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();
        work.torp.loreset.alerts.Alert.Log("Main", "Starting Lore-Set");


        getCommand("lore").setExecutor(new me.tweedjt.itemlore.commands.Lore()); // Register my command
    }

    @Override
    public void onDisable() {

    }

}

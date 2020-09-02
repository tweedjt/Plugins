package me.tweedjt.reputation;

import me.tweedjt.reputation.commands.ReloadCommandListener;
import me.tweedjt.reputation.commands.RepCommandListener;
import me.tweedjt.reputation.util.Log;
import me.tweedjt.reputation.util.ReputationConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Reputation extends JavaPlugin implements Listener {

    // Debug - set to true for debugging
    private final boolean debug = false;
    public boolean getDebug() {
        return debug;
    }

    public static HashMap<UUID, Boolean> playerRep = new HashMap<UUID, Boolean>();

    private static Reputation instance;
    public static Reputation getInstance() {

        return instance;
    }

    private ReputationConfig reputationConfig;
    public void loadReputationConfig() {
        this.reputationConfig = new ReputationConfig(this);
    }

    public ReputationConfig getReputationConfig() {
        return this.reputationConfig;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loadReputationConfig();

        getServer().getPluginManager().registerEvents(this, this);

        Bukkit.getPluginManager().registerEvents(new RepCommandListener(), this);

        // Add command listeners
        try {
            getCommand("rep").setExecutor(new RepCommandListener()); // gbm
            getCommand("repreload").setExecutor(new ReloadCommandListener());
        } catch (Exception ex) {
            // If we have an error, output it
            Log.logToConsole("Unexpected Error - " + ex.getMessage());
        }
    }

    @Override
    public void onDisable() {
        Log.logToConsole("Disabling GoodByeMobs");
    }

}


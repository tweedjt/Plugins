package me.tweedjt.goodbyemobs;

import java.util.HashMap;
import java.util.UUID;

import me.tweedjt.goodbyemobs.bukkitlisteners.CreatureSpawn;
import me.tweedjt.goodbyemobs.bukkitlisteners.PlayerJoin;
import me.tweedjt.goodbyemobs.commands.GBMCommandListener;
import me.tweedjt.goodbyemobs.commands.ReloadCommandListener;
import me.tweedjt.goodbyemobs.util.GoodByeMobsConfig;
import me.tweedjt.goodbyemobs.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class GoodbyeMobs extends JavaPlugin implements Listener {

    // Debug - set to true for debugging
    private final boolean debug = false;
    public boolean getDebug() {
        return debug;
    }

    public static HashMap<UUID, Boolean> BeGone = new HashMap<UUID, Boolean>();

    private static GoodbyeMobs instance;
    public static GoodbyeMobs getInstance() {

        return instance;
    }


    private WorldGuardPlugin wg;
    private WorldEditPlugin we;
    private boolean hasWorldGuard = false;
    public boolean hookWorldGuard()
    {
        Plugin wgPlug = getServer().getPluginManager().getPlugin("WorldGuard");
        Plugin wePlug = getServer().getPluginManager().getPlugin("WorldEdit");

        if (wgPlug != null && wePlug != null)
        {
            wg = ((WorldGuardPlugin) wgPlug);
            we = ((WorldEditPlugin) wePlug);

            hasWorldGuard = true;
            return true;
        } else {
            return false;
        }
    }
    public boolean hasWorldGuard() {
        return hasWorldGuard;
    }


    public WorldGuardPlugin getWorldGuard() {
        return wg;
    }
    public WorldEditPlugin getWorldEdit() {
        return we;
    }

    private GoodByeMobsConfig goodByeMobsConfig;
    public void loadGoodByeMobsConfig() {
        this.goodByeMobsConfig = new GoodByeMobsConfig(this);
    }

    public GoodByeMobsConfig getGoodByeMobsConfig() {
        return this.goodByeMobsConfig;
    }

    @Override
    public void onEnable() {
    instance = this;
    saveDefaultConfig();
    loadGoodByeMobsConfig();

    getServer().getPluginManager().registerEvents(this, this);

    Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    Bukkit.getPluginManager().registerEvents(new CreatureSpawn(), this);
    Bukkit.getPluginManager().registerEvents(new GBMCommandListener(), this);

    try {
        BukkitTask task = new BukkitRunnable() {
            public void run() {
                //Log.logToConsole("Timer");
                Timed.Run();
            }
        }.runTaskTimer(this, 0, getGoodByeMobsConfig().getRunTime() * 20);
        Log.debugToConsole("Main - Timed task: " + (task.getTaskId()));
    } catch (Exception ex) {
        Log.debugToConsole("Main - Unexpected error: " + ex.getMessage());
    }

    if (hookWorldGuard()) {
        Log.logToConsole("WorldGuard functionality added");
    } else {
        Log.logToConsole("WorldGuard functionality NOT added");
    }

    Log.logToConsole("Starting GoodByeMobs");

    instance = this; // Setting the instance to this

    // Add command listeners
    try {
        getCommand("gbm").setExecutor(new GBMCommandListener()); // gbm
        getCommand("gbmreload").setExecutor(new ReloadCommandListener());
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

package me.tweedjt.goodbyemobs;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import me.tweedjt.goodbyemobs.util.Alert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GoodbyeMobs extends JavaPlugin implements Listener{

    public static HashMap<UUID, Boolean> BeGone = new HashMap<UUID, Boolean>();

    private static GoodbyeMobs instance;
    public static GoodbyeMobs getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Alert.Log("Plugin Status", "Started");
        getServer().getPluginManager().registerEvents(this, this);

        try {
            BukkitTask task = new BukkitRunnable() {
                public void run() {
                    Timed.Run();
                }
            }.runTaskTimer(this, 0, 5 * 20);
            Alert.Log("Main", "Timed task: " + Integer.toString(task.getTaskId()));
        } catch (Exception ex) {
            Alert.Log("Main", "Unexpected error: " + ex.getMessage());
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("gbm"))
        {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isOp() || player.hasPermission("goodbyemobs.use"))
                {
                    if (BeGone.containsKey(player.getUniqueId())) {
                        BeGone.remove(player.getUniqueId());
                        Alert.Sender(ChatColor.RED + "Disabled", sender, true);
                    } else {
                        BeGone.put(player.getUniqueId(), true);
                        Alert.Sender(ChatColor.GREEN + "Enabled", sender, true);
                    }
                } else {
                    Alert.Sender(ChatColor.RED + "You do not have permission to run this command", sender, true);
                }
            } else {
                Alert.Sender(ChatColor.RED + "This command can only be run by an online player", sender, true);
            }
        }
        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        if (evt.getPlayer().hasPermission("goodbyemobs.on_at_join"))
        {
            if (!BeGone.containsKey(evt.getPlayer().getUniqueId())) {
                BeGone.put(evt.getPlayer().getUniqueId(), true);
                Alert.Player(ChatColor.GREEN + "Enabled", evt.getPlayer(), true);
            }
        }
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent evt) {
        boolean isMonster = false;
        if (evt.getEntity() instanceof Monster) {
            isMonster = true;
        }
        if (evt.getEntityType() == EntityType.PHANTOM) {
            isMonster = true;
        }
        if (evt.getEntityType() == EntityType.BAT) {
            isMonster = true;
        }
        if (isMonster) {
            Location eloc = evt.getLocation();
            if (BeGone != null) {
                for (Entry<UUID, Boolean> BeGonePlayer : BeGone.entrySet()) {
                    Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                    if (player != null) {
                        if (evt.getLocation().getWorld().equals(player.getLocation().getWorld())) {
                            int edist = (int) Math.round(player.getLocation().distance(eloc));
                            if (edist < GoodbyeMobs.getInstance().getGoodByeMobsConfig().getRadius()) {
                                Alert.DebugLog("Main", "onCreatureSpawn", "Entity spawn cancelled: " + evt.getEntityType().name());
                                evt.setCancelled(true);
                            }
                        }
                    } else {
                        BeGone.remove(BeGonePlayer.getKey());
                        Alert.DebugLog("Main", "onCreatureSpawn", "Invalid player, removing from GoodByeMobs list");
                    }
                }
            }
        }
    }

}

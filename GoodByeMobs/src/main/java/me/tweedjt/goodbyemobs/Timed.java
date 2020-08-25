package me.tweedjt.goodbyemobs;

import java.util.Collection;
import java.util.UUID;
import java.util.Map.Entry;

import me.tweedjt.goodbyemobs.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class Timed {
    public static void Run() {
        if (GoodbyeMobs.BeGone != null) {
            for (Entry<UUID, Boolean> BeGonePlayer : GoodbyeMobs.BeGone.entrySet()) {
                Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                if (player != null) {
                    Collection<Entity> nearbyEntities = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25);
                    if (nearbyEntities != null)
                    {
                        for (Entity e : nearbyEntities) {
                            boolean isMonster = false;
                            if (e instanceof Monster) {
                                isMonster = true;
                            }
                            if (e.getType() == EntityType.PHANTOM) {
                                isMonster = true;
                            }
                            if (e.getType() == EntityType.BAT) {
                                isMonster = true;
                            }
                            if (isMonster) {
                                int edist = (int) Math.round(player.getLocation().distance(e.getLocation()));
                                if (edist < 25) {
                                    Log.debugToConsole("Timed - Run - Entity removed: " + e.getType().name());
                                    e.remove();
                                }
                            }
                        }
                    }
                } else {
                    GoodbyeMobs.BeGone.remove(BeGonePlayer.getKey());
                }
            }
        }
    }
}

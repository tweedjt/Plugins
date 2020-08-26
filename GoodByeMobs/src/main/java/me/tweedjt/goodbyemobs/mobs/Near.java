package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.Map.Entry;


public class Near {

    private static Collection<Entity> getNearby(Player player) {
        //getting the radius, then returning the collection.  if there is no collection, returning null
        int r = GoodbyeMobs.getInstance().getGoodByeMobsConfig().getRadius();
        if (player != null) {
            return player.getLocation().getWorld().getNearbyEntities(player.getLocation(), r, r, r);
        } else {
            return null;
        }
    }
    public static void process() {
        if (GoodbyeMobs.BeGone != null) {
            // Loop through the players
            for (Entry<UUID, Boolean> BeGonePlayer : GoodbyeMobs.BeGone.entrySet()) {
                // Get the player
                Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                if (player == null) {
                    // Player is null, let's remove them from the map
                    GoodbyeMobs.BeGone.remove(BeGonePlayer.getKey());
                    break;
                }
                // Get the nearby entities
                Collection<Entity> nearbyEntities = getNearby(player);
                if (nearbyEntities != null) {
                    // Loop through the entities
                    for (Entity entity : nearbyEntities) {
                        // Check if it is a monster (or at least an entity we want to remove)
                        boolean isMonster = false;
                        if (entity instanceof Monster) {
                            isMonster = true;
                        }
                        if (entity.getType() == EntityType.PHANTOM) {
                            isMonster = true;
                        }
                        if (entity.getType() == EntityType.BAT) {
                            isMonster = true;
                        }
                        // If it is a monster, lets remove it
                        if (isMonster) {
                            if (MobsMisc.isKnockback()) {
                                // Knock the mob back
                                MobsMisc.knockback(player, entity);
                            } else {
                                // Remove the mob
                                MobsMisc.remove(player, entity);
                            }
                        }
                    }
                }
            }
        }
    }
}

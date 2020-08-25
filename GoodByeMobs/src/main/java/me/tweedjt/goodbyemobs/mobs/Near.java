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

    public static void process() {
        if (MobsMisc.isKnockback()) {
          //  knockbackNearby();
        } else {
            removeNearby();
        }
    }

    private static Collection<Entity> getNearby(Player player) {

        //getting the radius, then returning the collection.  if there is no collection, returning null
        int r = GoodbyeMobs.getInstance().getGoodByeMobsConfig().getRadius();
        if (player != null) {
            return player.getLocation().getWorld().getNearbyEntities(player.getLocation(), r, r, r);
        } else {
            return null;
        }

    }
    public static void removeNearby() {

        if (GoodbyeMobs.BeGone != null) {
            // Loop through the players
            for (Entry<UUID, Boolean> BeGonePlayer : GoodbyeMobs.BeGone.entrySet()) {
                // Get the player
                Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                if (player == null) {
                    break;
                }
                // Get the nearby entities
                Collection<Entity> nearbyEntities = getNearby(player);
                if (nearbyEntities != null) {
                    // Loop through the entities
                    for (Entity e : nearbyEntities) {
                        // Check if it is a monster (or at least an entity we want to remove)
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
                        // If it is, lets remove it
                        if (isMonster) {
                            e.remove();
                        }
                    }
                }else{
                    GoodbyeMobs.BeGone.remove(BeGonePlayer.getKey());
            }
            }
        }
    }

    public static void knockbackMob(Player player, Entity entity) {
        // No idea what we're doing here for knockback
        MobsMisc.knockback(player, entity);
    }

}

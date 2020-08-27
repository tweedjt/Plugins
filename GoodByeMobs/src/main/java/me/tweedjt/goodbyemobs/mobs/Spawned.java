package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import me.tweedjt.goodbyemobs.mobs.MobsMisc;
import me.tweedjt.goodbyemobs.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class Spawned {

    public static void process(Entity entity) {
        boolean isMonster = false;
        if (entity instanceof Monster) {
            isMonster = true;
        }
        if (entity.getType().equals(EntityType.PHANTOM)) {
            isMonster = true;
        }
        if (entity.getType().equals(EntityType.BAT)) {
            isMonster = true;
        }
        if (!isMonster) {
            // If it isn't a monster, exit out
            return;
        }
        if (GoodbyeMobs.getInstance().BeGone != null) {
            for (Map.Entry<UUID, Boolean> BeGonePlayer : GoodbyeMobs.getInstance().BeGone.entrySet()) {
                Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                if (player != null) {
                    GoodbyeMobs.getInstance().BeGone.remove(BeGonePlayer.getKey());
                    return;
                }
                if (MobsMisc.isKnockback()) {
                   // Log.logToConsole("SPAWNED - Knockback is enabled");
                    // Knock the mob back
                    MobsMisc.knockback(player, entity);
                } else {
                   // Log.logToConsole("SPAWNED - Knockback is not enabled");
                    // Remove the mob
                    MobsMisc.remove(player, entity);
                }
            }
        }
    }
}

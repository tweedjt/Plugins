package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import me.tweedjt.goodbyemobs.mobs.MobsMisc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class Spawned {

    public static void knockbackMob(Player player, Entity entity) {
         MobsMisc.knockback(player, entity);
        // No idea what we're doing here for knockback
    }
    public static void removeMob(Player player, Entity entity) {
        entity.remove();
    }

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
                    knockbackMob(player, entity);
                } else {
                    removeMob(player, entity);
                }
            }
        }
    }
}

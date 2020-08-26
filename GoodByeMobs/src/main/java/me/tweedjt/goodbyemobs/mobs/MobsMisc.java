package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MobsMisc {

    public static boolean isKnockback() {
        return  GoodbyeMobs.getInstance().getGoodByeMobsConfig().getKnockBack();
    }

    public static void knockback(Player player, Entity entity) {
        // knockback code here
    }

    public static void remove(Player player, Entity entity) {
        // We don't strictly need player here, but handy if you want it for logging
        if (entity != null) {
            // Removes an entity
            // You can add logging stuff here
            // You could add in a simple particle effect here that'll show where
            // the mob was etc.
            entity.remove();
        }
    }
}

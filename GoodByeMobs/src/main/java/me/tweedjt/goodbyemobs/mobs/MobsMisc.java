package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MobsMisc {

    public static boolean isKnockback() {
        return  GoodbyeMobs.getInstance().getGoodByeMobsConfig().getKnockBack();
    }

    public static void knockback(Player player, Entity entity) {
        // knockback code here
        int r = GoodbyeMobs.getInstance().getGoodByeMobsConfig().getRadius();
        Vector v = new Vector(r+2, r+2, r+2);

        if (isKnockback()) {

            player.getNearbyEntities(r, r, r);
            entity.setVelocity(player.getLocation().getDirection().setY(0).normalize().multiply(v));
            }
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

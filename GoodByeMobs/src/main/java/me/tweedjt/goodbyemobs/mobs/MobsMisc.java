package me.tweedjt.goodbyemobs.mobs;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import me.tweedjt.goodbyemobs.util.Log;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MobsMisc {

    public static boolean isKnockback() {
      //  Log.logToConsole("MOBMISC - Knockback is enabled");
        return  GoodbyeMobs.getInstance().getGoodByeMobsConfig().getKnockBack();
    }

    public static void knockback(Player player, Entity entity) {
        // knockback code here

        if (isKnockback()) {

                if (entity == null || player == null) {
                    return;
                }
                // normalize - Converts this vector to a unit vector (a vector with length of 1).
                // multiple(double) - Performs scalar multiplication, multiplying all components with a scalar.
                Location pushToLocation = entity.getLocation().subtract(player.getLocation());
                if (pushToLocation != null) {
                    Vector pushVector = new Vector(
                            pushToLocation.toVector().normalize().multiply(1.7).getX(),
                            0.5,
                            pushToLocation.toVector().normalize().multiply(1.7).getZ()
                    );
                    entity.setVelocity(pushVector);
                }
            }
            //get nearby entities
            // get locations and respective vectors
            // subtract entity vector with player vector
            // normalize
            // multiply with a number
            // set vector to entity with entity.setVelocity()
            }


    public static void remove(Player player, Entity entity) {
        // We don't strictly need player here, but handy if you want it for logging
        if (entity != null) {

           // Log.logToConsole("MOBMISC - Knockback is not enabled");
            // Removes an entity
            // You can add logging stuff here
            // You could add in a simple particle effect here that'll show where
            // the mob was etc.
            entity.remove();
        }
    }
}

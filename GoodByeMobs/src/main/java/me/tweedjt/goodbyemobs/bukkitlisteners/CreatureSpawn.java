package me.tweedjt.goodbyemobs.bukkitlisteners;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import me.tweedjt.goodbyemobs.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.UUID;

public class CreatureSpawn implements Listener {

    @EventHandler
    public void onCreatureSpawn(org.bukkit.event.entity.CreatureSpawnEvent evt) {
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
            if (GoodbyeMobs.getInstance().BeGone != null) {
                for (Map.Entry<UUID, Boolean> BeGonePlayer : GoodbyeMobs.getInstance().BeGone.entrySet()) {
                    Player player = Bukkit.getPlayer(BeGonePlayer.getKey());
                    if (player != null) {
                        if (evt.getLocation().getWorld().equals(player.getLocation().getWorld())) {
                            int edist = (int) Math.round(player.getLocation().distance(eloc));
                            if (edist < GoodbyeMobs.getInstance().getGoodByeMobsConfig().getRadius()) {
                                Log.debugToConsole("Main - onCreatureSpawn - Entity spawn cancelled: " + evt.getEntityType().name());
                                evt.setCancelled(true);
                            }
                        }
                    } else {
                        GoodbyeMobs.getInstance().BeGone.remove(BeGonePlayer.getKey());
                        Log.debugToConsole("Main - onCreatureSpawn - Invalid player, removing from GoodByeMobs list");
                    }
                }
            }
        }
       // if (GoodbyeMobs.getInstance().getGoodByeMobsConfig().getKnockBack()){
       //     entity.setVelocity(new Vector(-10, 0, 0));
       // } else {
       //     Delete Code
       // }
    }

}

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
}

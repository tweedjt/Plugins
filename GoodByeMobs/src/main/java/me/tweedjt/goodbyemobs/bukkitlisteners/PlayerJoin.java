package me.tweedjt.goodbyemobs.bukkitlisteners;

import me.tweedjt.goodbyemobs.util.Message;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tweedjt.goodbyemobs.GoodbyeMobs;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent evt) {
        if (evt.getPlayer().hasPermission("goodbyemobs.on_at_join"))
        {
            if (!GoodbyeMobs.getInstance().BeGone.containsKey(evt.getPlayer().getUniqueId())) {
                GoodbyeMobs.getInstance().BeGone.put(evt.getPlayer().getUniqueId(), true);
                Message.toPlayer(ChatColor.GREEN + "Enabled", evt.getPlayer());
            }
        }
    }

}

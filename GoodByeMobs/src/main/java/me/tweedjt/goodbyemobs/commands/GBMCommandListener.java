package me.tweedjt.goodbyemobs.commands;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import me.tweedjt.goodbyemobs.util.Log;
import me.tweedjt.goodbyemobs.util.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GBMCommandListener implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("gbm"))
        {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isOp() || player.hasPermission("goodbyemobs.use"))
                {
                    if (GoodbyeMobs.getInstance().BeGone.containsKey(player.getUniqueId())) {
                        GoodbyeMobs.getInstance().BeGone.remove(player.getUniqueId());
                        Message.toPlayer(ChatColor.RED + "Disabled", player);
                    } else {
                        GoodbyeMobs.getInstance().BeGone.put(player.getUniqueId(), true);
                        Message.toPlayer(ChatColor.GREEN + "Enabled", player);
                    }
                } else {
                    Message.toPlayer(ChatColor.RED + "You do not have permission to run this command", player);
                }
            } else {
                Message.toSender(ChatColor.RED + "This command can only be run by an online player", sender);
            }
        }
        return true;
    }
}

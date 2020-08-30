package me.tweedjt.itemlore.util;

import me.tweedjt.itemlore.ItemLore;

public class ItemLoreConfig {


    private String messagePrefix = "&6[&fAutoSmelt&6] &r";


    public String getMessagePrefix() {
        return this.messagePrefix;
    }

    public ItemLoreConfig(ItemLore plugin) {


        // message_prefix
        try {
            if (plugin.getConfig().contains("message_prefix")) {
                if (plugin.getConfig().isString("message_prefix")) {
                    this.messagePrefix = plugin.getConfig().getString("message_prefix");
                } else {
                    messagePrefix = "&d[&fGoodByeMobs&d] &r";
                    // message_prefix is not a string value
                }
            } else {
                messagePrefix = "&d[&fGoodByeMobs&d] &r";
                // message_prefix does not exist in config file
            }
        } catch (Exception ex) {
            messagePrefix = "&d[&fGoodByeMobs&d] &r";
            // Unexpected error getting message_prefix. (Use ex.getMessage() to get error)

        }
    }
}
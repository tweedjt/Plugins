package me.tweedjt.itemlore.util;
import org.bukkit.ChatColor;

public class Misc {

    public static String colorToString(String input) {
        // &0 = black, &1 = dark blue, &2 = dark green, &3 = dark aqua, &4 = dark red, &5 = dark purple
        // &6 = gold, &7 = gray, &8 = dark gray, &9 = blue, &a = green, &b = aqua, &c = red
        // &d = light purple, &e = yellow, &f = white, &k = magic, &l = bold, &m = strikethrough
        // &n = underline, &o = italic, &r = reset
        String returnValue = ChatColor.translateAlternateColorCodes('&', input);
        return returnValue;
    }
}

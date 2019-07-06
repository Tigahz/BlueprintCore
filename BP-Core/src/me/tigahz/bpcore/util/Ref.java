package me.tigahz.bpcore.util;

import org.bukkit.ChatColor;

public class Ref {
	
	public static String format(String formattedMessage) {
		return ChatColor.translateAlternateColorCodes('&', formattedMessage);
	}
	
	public static String strip(String strippedMessage) {
		return ChatColor.stripColor(strippedMessage);
	}

}

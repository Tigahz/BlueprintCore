package me.tigahz.bpcore.util;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public interface CommandHandler extends TabExecutor {

	default void register(JavaPlugin plugin) {
		
		final PluginCommand cmd = plugin.getCommand(getName());
		
		if (cmd == null) {
			return;
		}
		
		cmd.setExecutor(this);
		cmd.setTabCompleter(this);
		
	}
	
	String getName();
	
}

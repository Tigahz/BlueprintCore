package me.tigahz.bpcore.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.handler.IDHandler;
import me.tigahz.bpcore.util.Ref;

public class JoinEvents implements Listener {
	
	@EventHandler
	public void onJoinEvents(PlayerJoinEvent e) {
		
		Player p = (Player) e.getPlayer();
		UUID uuid = p.getUniqueId();
		UserConfig uc = new UserConfig(uuid);
		
		uc.createUserFile(uuid);
		Bukkit.getConsoleSender().sendMessage(Ref.format("&9BP &8 - &aConfig " + uuid.toString() + ".yml created for user " + p.getName()));
		
		if (uc.getUserFile().getBoolean("id")) {
			IDHandler.hasID.add(p);
		}
		
	}
	
}

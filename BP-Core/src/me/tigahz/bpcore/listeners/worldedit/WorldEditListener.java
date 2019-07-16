package me.tigahz.bpcore.listeners.worldedit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldedit.WorldEditException;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.util.Ref;

public class WorldEditListener implements Listener {
	
	@EventHandler
	public void interact(PlayerInteractEvent e) throws WorldEditException {
		
		Player p = e.getPlayer();
		
		if (p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format("&b&lCar Wand"))) {
			LoadCarsEvent load = new LoadCarsEvent();
			load.loadCar(p);
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Successfully loaded and pasted car"));
		} else if (p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format("&b&lOak Tree Wand"))) {
			LoadTreeEvent load = new LoadTreeEvent();
			load.loadTree(p, "oak");
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Successfully loaded and pasted oak tree"));
		} else if (p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format("&b&lSpruce Tree Wand"))) {
			LoadTreeEvent load = new LoadTreeEvent();
			load.loadTree(p, "spruce");
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Successfully loaded and pasted spruce tree"));
		} else if (p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format("&b&lBirch Tree Wand"))) {
			LoadTreeEvent load = new LoadTreeEvent();
			load.loadTree(p, "birch");
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Successfully loaded and pasted birch tree"));
		} else if (p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format("&b&lJungle Tree Wand"))) {
			LoadTreeEvent load = new LoadTreeEvent();
			load.loadTree(p, "jungle");
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Successfully loaded and pasted jungle tree"));
		}
		
	}

}

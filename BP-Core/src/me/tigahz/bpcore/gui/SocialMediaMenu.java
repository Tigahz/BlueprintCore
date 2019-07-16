package me.tigahz.bpcore.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class SocialMediaMenu implements Listener, CommandHandler {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	String menuName = Ref.format("&9&lSocial Media Menu");
	ItemStack itemStack;
	
	public void createMenu(Player p) {
		
		Inventory i = Bukkit.createInventory(null, 36, menuName);
		
		ArrayList<String> pmcLore = new ArrayList<>();
		pmcLore.add(Ref.format("&bVisit our PlanetMinecraft"));
		pmcLore.add(Ref.format("&bfor awesome creations!"));
		Items.createLoredItem(i, itemStack, Material.MUSIC_DISC_STRAD, 10, "&a&lPlanet&b&lMinecraft", pmcLore);
		
		ArrayList<String> twitterLore = new ArrayList<>();
		twitterLore.add(Ref.format("&bVisit our twitter!"));
		Items.createLoredItem(i, itemStack, Material.MUSIC_DISC_WARD, 12, "&b&lTwitter", twitterLore);
		
		ArrayList<String> discordLore = new ArrayList<>();
		discordLore.add(Ref.format("&bGo to our discord to"));
		discordLore.add(Ref.format("&btalk to us!"));
		Items.createLoredItem(i, itemStack, Material.MUSIC_DISC_WAIT, 14, "&9&lDiscord", discordLore);
		
		ArrayList<String> ytLore = new ArrayList<>();
		ytLore.add(Ref.format("&bVisit our YouTube!"));
		Items.createLoredItem(i, itemStack, Material.MUSIC_DISC_11, 16, "&4&lYou&f&lTube", ytLore);
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 27, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 35, "&c&lExit Menu");
		
		p.openInventory(i);
		
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.SM_MENU))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				createMenu(p);
				p.sendMessage(Ref.format(messages("prefix") + messages("opened-menu")));
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.SM_MENU);
	}	
	
	@EventHandler
	public void onRPClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null) {
				e.setCancelled(true);
			} else if (!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else if (e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&a&lPlanet&b&lMinecraft")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("pmc-link")));
			} else if (Items.getClickedItem(e, "&b&lTwitter")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("twitter-link")));
			} else if (Items.getClickedItem(e, "&9&lDiscord")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("discord-link")));
			} else if (Items.getClickedItem(e, "&4&lYou&f&lTube")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("yt-link")));
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				MainMenu menu = new MainMenu();
				menu.createMenu(p);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else {
				e.setCancelled(true);
			}
			
		} else {
			return;
		}
		
	}

}

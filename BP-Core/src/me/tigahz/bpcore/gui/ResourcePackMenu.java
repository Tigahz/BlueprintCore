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

public class ResourcePackMenu implements Listener, CommandHandler {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	static String menuName = Ref.format("&9&lResource Pack Menu");
	static ItemStack itemStack;
	
	public static void createMenu(Player p) {
		
		Inventory i = Bukkit.createInventory(null, 36, menuName);
		
		ArrayList<String> standardLore = new ArrayList<>();
		standardLore.add(Ref.format("&bStandard Edition"));
		standardLore.add(Ref.format("&bFull resolution pack"));
		Items.createLoredItem(i, itemStack, Material.DANDELION_YELLOW, 11, "&9&lStandard Edition", standardLore);
		
		ArrayList<String> liteLore = new ArrayList<>();
		liteLore.add(Ref.format("&bLite Edition"));
		liteLore.add(Ref.format("&bLow resolution pack"));
		Items.createLoredItem(i, itemStack, Material.ORANGE_DYE, 15, "&9&lLite Edition", liteLore);
		
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

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.RP_MENU))) {
			
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
		return CommandType.getCommand(CommandType.RP_MENU);
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
			} else if (Items.getClickedItem(e, "&9&lStandard Edition")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("standard-pack-link")));
			} else if (Items.getClickedItem(e, "&9&lLite Edition")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage(Ref.format(messages("prefix") + messages("lite-pack-link")));
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				MainMenu.createMenu(p);
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

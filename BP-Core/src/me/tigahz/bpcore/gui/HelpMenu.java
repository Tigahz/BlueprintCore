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

public class HelpMenu implements CommandHandler, Listener {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	String menuName = Ref.format("&9&lHelp Menu");
	ItemStack itemStack;
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.HELP_MENU))) {
			
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
		return CommandType.getCommand(CommandType.HELP_MENU);
	}
	
	public void createMenu(final Player p) {
		
		Inventory i = Bukkit.createInventory(null, 45, menuName);
		
		ArrayList<String> applyLore = new ArrayList<>();
		applyLore.add(Ref.format("&bApply for builder"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 10, "&9&lApply", applyLore);
		
		ArrayList<String> prLore = new ArrayList<>();
		prLore.add(Ref.format("&bRequest a promotion"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 13, "&9&lPromotion Request", prLore);
		
		ArrayList<String> cbLore = new ArrayList<>();
		cbLore.add(Ref.format("&bRequest for a staff member"));
		cbLore.add(Ref.format("&bto review your build for"));
		cbLore.add(Ref.format("&bpromotion. &b&o(Plotworld Ranks only"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 16, "&9&lShow Build", cbLore);
		
		ArrayList<String> ranksLore = new ArrayList<>();
		ranksLore.add(Ref.format("&bView the server ranks"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 19, "&9&lRanks", ranksLore);
		
		ArrayList<String> staffLore = new ArrayList<>();
		staffLore.add(Ref.format("&bView the server staff"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 22, "&9&lStaff", staffLore);
		
		ArrayList<String> clLore = new ArrayList<>();
		clLore.add(Ref.format("&bHelp with commands"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 25, "&9&lCommand List", clLore);
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 36, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 44, "&c&lExit Menu");
		
		p.openInventory(i);
		
	}
	
	@EventHandler
	public void onMainMenu(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null) {
				e.setCancelled(true);
			} else if (!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else if (e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&9&lApply")) {
				e.setCancelled(true);
				p.closeInventory();
				p.performCommand("blueprintcore:apply");
			} else if (Items.getClickedItem(e, "&9&lPromotion Request")) {
				e.setCancelled(true);
				p.closeInventory();
				p.performCommand("blueprintcore:pr");
			} else if (Items.getClickedItem(e, "&9&lShow Build")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("showbuild");
			} else if (Items.getClickedItem(e, "&9&lRanks")) {
				e.setCancelled(true);
				p.sendMessage("ranks");
			} else if (Items.getClickedItem(e, "&9&lStaff")) {
				e.setCancelled(true);
				p.sendMessage("staff");
			} else if (Items.getClickedItem(e, "&9&lCommand List")) {
				e.setCancelled(true);
				p.sendMessage("cl");
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				MainMenu.createMenu(p);
				e.setCancelled(true);
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

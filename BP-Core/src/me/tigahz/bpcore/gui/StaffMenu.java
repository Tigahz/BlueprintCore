package me.tigahz.bpcore.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.Convert;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class StaffMenu implements CommandHandler, Listener {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	static String menuName = Ref.format("&9&lHelp Menu");
	static ItemStack itemStack;
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.STAFF))) {
			
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
		return CommandType.getCommand(CommandType.STAFF);
	}
	
	public static void createMenu(final Player p) {
		
		Inventory i = Bukkit.createInventory(null, 54, menuName);
		
		ArrayList<String> directorLore = new ArrayList<>();
		directorLore.add(Ref.format("&9&oThese individuals work"));
		directorLore.add(Ref.format("&9&oas the leaders of the server."));
		directorLore.add(Ref.format("&9&oThey work to help direct things"));
		directorLore.add(Ref.format("&9&oand develop the server."));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 0, "&6[&9&lDirector&r&6]", directorLore);
		
		for (String uuid : StaffConfig.getConfig().getConfigurationSection("director").getKeys(false)) {
			
			UUID u = UUID.fromString(uuid);
			ArrayList<String> ll = new ArrayList<>();
			int pos = Math.addExact(StaffConfig.getConfig().getInt("director." + u + ".position"), 1);
			String getName = Ref.format("&9&l" + Convert.getNameFromUUID(u));
			
			for (String lore : StaffConfig.getConfig().getStringList("director." + u + ".lore")) {
				ll.add(Ref.format("&9&o" + lore));
				Items.createSkull(i, itemStack, pos, u, getName, ll);
			}
			
		}
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 45, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 53, "&c&lExit Menu");
		
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

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

public class MainMenu implements CommandHandler, Listener {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	static String menuName = Ref.format("&9&lBlueprint Menu");
	static ItemStack itemStack;
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.MENU))) {
			
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
		return CommandType.getCommand(CommandType.MENU);
	}
	
	public static void createMenu(final Player p) {
		
		Inventory i = Bukkit.createInventory(null, 54, menuName);
		
		ArrayList<String> helpLore = new ArrayList<>();
		helpLore.add(Ref.format("&bGeneral Help Menu"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 10, "&9&lHelp", helpLore);
		
		ArrayList<String> rpLore = new ArrayList<>();
		rpLore.add(Ref.format("&bDownload our Resource Pack"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 12, "&9&lResource Pack", rpLore);
		
		ArrayList<String> smLore = new ArrayList<>();
		smLore.add(Ref.format("&bCheck out our Social Media"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 14, "&9&lSocial Media", smLore);
		
		ArrayList<String> projectsLore = new ArrayList<>();
		projectsLore.add(Ref.format("&bExplore our many"));
		projectsLore.add(Ref.format("&bServer Projects"));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 16, "&9&lProjects", projectsLore);
		
		ArrayList<String> webLore = new ArrayList<>();
		webLore.add(Ref.format("&bVisit our website"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 28, "&9&lOur Website", webLore);
		
		ArrayList<String> rulesLore = new ArrayList<>();
		rulesLore.add(Ref.format("&bRead the rules"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 29, "&9&lRules", rulesLore);
		
		ArrayList<String> spawnLore = new ArrayList<>();
		spawnLore.add(Ref.format("&bGo to spawn"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 30, "&9&lSpawn", spawnLore);
		
		ArrayList<String> ranksLore = new ArrayList<>();
		ranksLore.add(Ref.format("&bView our ranks"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 31, "&9&lRanks", ranksLore);
		
		ArrayList<String> applyLore = new ArrayList<>();
		applyLore.add(Ref.format("&bApply for builder"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 32, "&9&lApply", applyLore);
		
		ArrayList<String> prLore = new ArrayList<>();
		prLore.add(Ref.format("&bRequest for a promotion"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 33, "&9&lPromotion Request", prLore);
		
		ArrayList<String> btLore = new ArrayList<>();
		btLore.add(Ref.format("&bVarious tools and features"));
		btLore.add(Ref.format("&bthat can help with building"));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 34, "&9&lBuilding Tools", btLore);
		
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
			} else if (Items.getClickedItem(e, "&9&lHelp")) {
				e.setCancelled(true);
				p.sendMessage("help menu");
			} else if (Items.getClickedItem(e, "&9&lResource Pack")) {
				e.setCancelled(true);
				p.sendMessage("rp menu");
			} else if (Items.getClickedItem(e, "&9&lSocial Media")) {
				e.setCancelled(true);
				p.sendMessage("sm menu");
			} else if (Items.getClickedItem(e, "&9&lProjects")) {
				e.setCancelled(true);
				p.sendMessage("projects menu");
			} else if (Items.getClickedItem(e, "&9&lOur Website")) {
				e.setCancelled(true);
				p.sendMessage("website");
			} else if (Items.getClickedItem(e, "&9&lRules")) {
				e.setCancelled(true);
				p.sendMessage("rules");
			} else if (Items.getClickedItem(e, "&9&lSpawn")) {
				e.setCancelled(true);
				p.sendMessage("spawn");
			} else if (Items.getClickedItem(e, "&9&lRanks")) {
				e.setCancelled(true);
				p.sendMessage("ranks");
			} else if (Items.getClickedItem(e, "&9&lApply")) {
				e.setCancelled(true);
				p.sendMessage("apply");
			} else if (Items.getClickedItem(e, "&9&lPromotion Request")) {
				e.setCancelled(true);
				p.sendMessage("pr");
			} else if (Items.getClickedItem(e, "&9&lBuilding Tools")) {
				e.setCancelled(true);
				p.sendMessage("bt");
			} else {
				e.setCancelled(true);
			}
			
		} else {
			return;
		}
		
	}
	
}

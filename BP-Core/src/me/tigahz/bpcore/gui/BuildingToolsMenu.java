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
import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class BuildingToolsMenu implements CommandHandler, Listener {
	
	ItemStack itemStack;
	String menuName = Ref.format("&9&lBuilding Tools Menu");
	
	public void createMenu(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 36, menuName);
		
		UUID uuid = p.getUniqueId();
		UserConfig uc = new UserConfig(uuid);
		
		ArrayList<String> nightVisionLore = new ArrayList<>();
		nightVisionLore.add(Ref.format("&bToggle whether you can see in the dark"));
		nightVisionLore.add(Ref.format("&bAvailable to rank &f[&2Apprentice&f]"));
		nightVisionLore.add(Ref.format("&bor Plotworld &8[&e4&8]&b."));
		if (uc.getUserFile().getBoolean("nightvision")) {
			Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 10, "&9&lToggle NightVision", nightVisionLore);
		} else {
			Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 10, "&9&lToggle NightVision", nightVisionLore);
		}
		
		ArrayList<String> headsLore = new ArrayList<>();
		headsLore.add(Ref.format("&bAdd custom heads to your build"));
		headsLore.add(Ref.format("&bAvailable for &f[&2Apprentice&f]"));
		headsLore.add(Ref.format("&band Plotworld &8[&a7&8]&b."));
		Items.createLoredItem(inventory, itemStack, Material.PLAYER_HEAD, 12, "&9&lCustom Heads", headsLore);
		
		ArrayList<String> assetsLore = new ArrayList<>();
		assetsLore.add(Ref.format("&bVarious other things you"));
		assetsLore.add(Ref.format("&bmay want to add to your build"));
		Items.createLoredItem(inventory, itemStack, Material.OAK_FENCE_GATE, 14, "&9&lAssets", assetsLore);
		
		ArrayList<String> wandLore = new ArrayList<>();
		wandLore.add(Ref.format("&bVarious wands to add cars,"));
		wandLore.add(Ref.format("&btrucks, trees etc. to your build"));
		Items.createLoredItem(inventory, itemStack, Material.IRON_AXE, 16, "&9&lWands", wandLore);
		
		Items.createItem(inventory, itemStack, Material.SUGAR_CANE, 27, "&c&lGo Back");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 35, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	@EventHandler
	public void onBTClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				MainMenu menu = new MainMenu();
				menu.createMenu(p);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else if (Items.getClickedItem(e, "&9&lToggle NightVision")) {
				if (e.getCurrentItem().getType().equals(Material.CACTUS_GREEN)) {
					e.getCurrentItem().setType(Material.ROSE_RED);
					p.performCommand("blueprintcore:nv");
					e.setCancelled(true);
				} else if (e.getCurrentItem().getType().equals(Material.ROSE_RED)) {
					e.getCurrentItem().setType(Material.CACTUS_GREEN);
					p.performCommand("blueprintcore:nv");
					e.setCancelled(true);
				}
			} else if (Items.getClickedItem(e, "&9&lCustom Heads")) {
				e.setCancelled(true);
				p.closeInventory();
				p.performCommand("hdb");
			} else if (Items.getClickedItem(e, "&9&lAssets")) {
				e.setCancelled(true);
				p.closeInventory();
				p.performCommand("warp assets");
			} else if (Items.getClickedItem(e, "&9&lWands")) {
				e.setCancelled(true);
				p.sendMessage("wands");
			} else {
				e.setCancelled(true);
			}
		} else {
			return;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	String messages(String str) {
		return MessagesConfig.getConfig().getString(str);
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.BT))) {
			
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
		return CommandType.getCommand(CommandType.BT);
	}
}

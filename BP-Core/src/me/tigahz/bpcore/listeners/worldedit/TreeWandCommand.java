package me.tigahz.bpcore.listeners.worldedit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldedit.WorldEditException;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.gui.BuildingToolsMenu;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.PermissionsType;
import me.tigahz.bpcore.util.Ref;

public class TreeWandCommand implements CommandHandler, Listener {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.TREE_MENU))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				
				if (!p.hasPermission(PermissionsType.getPermission(PermissionsType.SCHEM))) {
					p.sendMessage(ErrorType.getError(ErrorType.NO_PERMISSIONS));
				} else {
					
					createMenu(p);
					p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
							MessagesConfig.getConfig().getString("opened-menu")));
					
				}
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.TREE_MENU);
	}
	
	String menuName = Ref.format("&9&lTree Wand Menu");
	ItemStack itemStack;
	
	public void createMenu(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 36, menuName);
		
		ArrayList<String> oakLore = new ArrayList<>();
		oakLore.add(Ref.format("&bWand to get Oak trees"));
		Items.createLoredItem(inventory, itemStack, Material.OAK_SAPLING, 10, "&b&lOak Tree Wand", oakLore);
		
		ArrayList<String> spruceLore = new ArrayList<>();
		spruceLore.add(Ref.format("&bWand to get Oak trees"));
		Items.createLoredItem(inventory, itemStack, Material.SPRUCE_SAPLING, 12, "&b&lSpruce Tree Wand", spruceLore);
		
		ArrayList<String> birchLore = new ArrayList<>();
		birchLore.add(Ref.format("&bWand to get Oak trees"));
		Items.createLoredItem(inventory, itemStack, Material.BIRCH_SAPLING, 14, "&b&lBirch Tree Wand", birchLore);
		
		ArrayList<String> jungleLore = new ArrayList<>();
		jungleLore.add(Ref.format("&bWand to get Oak trees"));
		Items.createLoredItem(inventory, itemStack, Material.JUNGLE_SAPLING, 16, "&b&lJungle Tree Wand", jungleLore);
		
		Items.createItem(inventory, itemStack, Material.SUGAR_CANE, 27, "&c&lGo Back");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 35, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	@EventHandler
	public void onTreeClick(InventoryClickEvent e) throws WorldEditException {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&b&lOak Tree Wand")) {
				
				e.setCancelled(true);
				p.closeInventory();
				
				ItemStack is = new ItemStack(Material.IRON_AXE);
				ItemMeta im = (ItemMeta) is.getItemMeta();
				im.setDisplayName(Ref.format("&b&lOak Tree Wand"));
				im.addEnchant(Enchantment.DURABILITY, 1, true);
				is.setItemMeta(im);
				p.getInventory().addItem(is);
				
			} else if (Items.getClickedItem(e, "&b&lSpruce Tree Wand")) {
				
				e.setCancelled(true);
				p.closeInventory();
				
				ItemStack is = new ItemStack(Material.IRON_AXE);
				ItemMeta im = (ItemMeta) is.getItemMeta();
				im.setDisplayName(Ref.format("&b&lSpruce Tree Wand"));
				im.addEnchant(Enchantment.DURABILITY, 1, true);
				is.setItemMeta(im);
				p.getInventory().addItem(is);
				
			} else if (Items.getClickedItem(e, "&b&lBirch Tree Wand")) {
				
				e.setCancelled(true);
				p.closeInventory();
				
				ItemStack is = new ItemStack(Material.IRON_AXE);
				ItemMeta im = (ItemMeta) is.getItemMeta();
				im.setDisplayName(Ref.format("&b&lBirch Tree Wand"));
				im.addEnchant(Enchantment.DURABILITY, 1, true);
				is.setItemMeta(im);
				p.getInventory().addItem(is);
				
			} else if (Items.getClickedItem(e, "&b&lJungle Tree Wand")) {
				
				e.setCancelled(true);
				p.closeInventory();
				
				ItemStack is = new ItemStack(Material.IRON_AXE);
				ItemMeta im = (ItemMeta) is.getItemMeta();
				im.setDisplayName(Ref.format("&b&lJungle Tree Wand"));
				im.addEnchant(Enchantment.DURABILITY, 1, true);
				is.setItemMeta(im);
				p.getInventory().addItem(is);
				
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				BuildingToolsMenu menu = new BuildingToolsMenu();
				menu.createMenu(p);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else {
				e.setCancelled(true);
			}
			
		}
		
	}

}

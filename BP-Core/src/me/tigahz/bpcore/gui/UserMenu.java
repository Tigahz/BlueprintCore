package me.tigahz.bpcore.gui;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.RequestsConfig;
import me.tigahz.bpcore.util.Convert;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class UserMenu implements Listener {
	
	ItemStack itemStack;
	
	public void createMenu(Player p, UUID uuid) {
		
		Inventory inventory = Bukkit.createInventory(null, 9, Ref.format("&b&l" + Convert.getNameFromUUID(uuid)));
		
		Items.createItem(inventory, itemStack, Material.CACTUS_GREEN, 0, "&a&lAccept Request");
		Items.createItem(inventory, itemStack, Material.CACTUS_GREEN, 1, "&a&lAccept Request");
		Items.createItem(inventory, itemStack, Material.CACTUS_GREEN, 2, "&a&lAccept Request");
		Items.createItem(inventory, itemStack, Material.CACTUS_GREEN, 3, "&a&lAccept Request");
		
		ArrayList<String> itemLore = new ArrayList<>();
		itemLore.add(Ref.format("&8&m--------------------"));
		itemLore.add("");
		itemLore.add(Ref.format("&b&l♦ World: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".world")));
		itemLore.add(Ref.format("&b&l♦ X: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".x")));
		itemLore.add(Ref.format("&b&l♦ Y: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".y")));
		itemLore.add(Ref.format("&b&l♦ Z: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".z")));
		itemLore.add("");
		itemLore.add(Ref.format("&8&m--------------------"));
		
		Items.createLoredItem(inventory, itemStack, Material.SIGN, 4, "&b&l" + Convert.getNameFromUUID(uuid), itemLore);
		
		Items.createItem(inventory, itemStack, Material.ROSE_RED, 5, "&c&lDeny Request");
		Items.createItem(inventory, itemStack, Material.ROSE_RED, 6, "&c&lDeny Request");
		Items.createItem(inventory, itemStack, Material.ROSE_RED, 7, "&c&lDeny Request");
		Items.createItem(inventory, itemStack, Material.ROSE_RED, 8, "&c&lDeny Request");
		
		p.openInventory(inventory);
		
	}
	
	@EventHandler
	public void onUserClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		@SuppressWarnings("deprecation")
		OfflinePlayer op = Bukkit.getOfflinePlayer(Ref.strip(e.getInventory().getName()));
		UUID uuid = op.getUniqueId();
		
		if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
			e.setCancelled(true);
		} else if (Items.getClickedItem(e, "&a&lAccept Request")) {
			
			e.setCancelled(true);
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bppoints add " + op.getName() + " 1");
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Accepted " + Ref.strip(op.getName()) + "'s request."));
			RequestsConfig.getConfig().set("requests." + uuid, null);
			RequestsConfig.saveConfig();
			
			if (op.getPlayer().isOnline()) {
				op.getPlayer().sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Congratulations, your show build request was accepted!"));
				op.getPlayer().performCommand("points");
			}
			
			p.closeInventory();
			
		} else if (Items.getClickedItem(e, "&c&lDeny Request")) {
			
			e.setCancelled(true);
			
			RequestsConfig.getConfig().set("requests." + uuid, null);
			RequestsConfig.saveConfig();
			
			p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Denied " + Ref.strip(op.getName()) + "'s request."));
			
			if (op.getPlayer().isOnline()) {
				op.getPlayer().sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Sorry, your show build request was denied! Try again soon!"));
				op.getPlayer().performCommand("points");
			}
			
			p.closeInventory();
			
		}
		
	}

}

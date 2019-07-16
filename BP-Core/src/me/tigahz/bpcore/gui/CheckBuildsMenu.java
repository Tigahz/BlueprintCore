package me.tigahz.bpcore.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.RequestsConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.Convert;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.PermissionsType;
import me.tigahz.bpcore.util.Ref;

public class CheckBuildsMenu implements CommandHandler, Listener {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.CHECKBUILD))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				
				if (!p.hasPermission(PermissionsType.getPermission(PermissionsType.ADMIN))) {
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
		return CommandType.getCommand(CommandType.CHECKBUILD);
	}
	
	String menuName = Ref.format("&9&lShow Build Requests Menu");
	ItemStack itemStack;
	
	public void createMenu(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 54, menuName);
		
		for (String uuid : RequestsConfig.getConfig().getConfigurationSection("requests").getKeys(false)) {
			
			String ign = Convert.getNameFromUUID(uuid);
			
			ArrayList<String> lore = new ArrayList<>();
			lore.add(Ref.format("&8&m--------------------"));
			lore.add("");
			lore.add(Ref.format("&b&l♦ World: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".world")));
			lore.add(Ref.format("&b&l♦ X: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".x")));
			lore.add(Ref.format("&b&l♦ Y: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".y")));
			lore.add(Ref.format("&b&l♦ Z: &b" + RequestsConfig.getConfig().getString("requests." + uuid + ".z")));
			lore.add("");
			lore.add(Ref.format("&8&m--------------------"));
			lore.add(Ref.format("&b&lClick to teleport there"));
			lore.add(Ref.format("&b&lRight Click to accept/remove"));
			lore.add(Ref.format("&8&m--------------------"));
			
			Items.createLoredItem(inventory, itemStack, Material.SIGN, 
					inventory.firstEmpty(), "&b&l"+ign, lore);
			
		}
		
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 36, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 37, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 38, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 39, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 40, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 41, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 42, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 43, "");
		Items.createItem(inventory, itemStack, Material.CYAN_STAINED_GLASS_PANE, 44, "");
		
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 45, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 46, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 47, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 48, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 49, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 50, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 51, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 52, "&c&lExit Menu");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 53, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onCBClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else if (e.getCurrentItem().getType().equals(Material.SIGN)) {
				
				String ign = Ref.strip(e.getCurrentItem().getItemMeta().getDisplayName());
				
				if (Bukkit.getServer().getOnlinePlayers().contains(ign)) {
					
					Player op = Bukkit.getPlayer(ign);
					UUID uuid = op.getUniqueId();
					
					if (e.getClick().isLeftClick()) {
						
						e.setCancelled(true);
						
						World world = Bukkit.getWorld(RequestsConfig.getConfig().getString("requests." + uuid + ".world"));
						int posx = RequestsConfig.getConfig().getInt("requests." + uuid + ".x");
						int posy = RequestsConfig.getConfig().getInt("requests." + uuid + ".y");
						int posz = RequestsConfig.getConfig().getInt("requests." + uuid + ".z");
						
						Location loc = new Location(world, posx, posy, posz, p.getLocation().getYaw(), p.getLocation().getPitch());
						p.teleport(loc);
						p.closeInventory();
								
					} else if (e.getClick().isRightClick()) {
						
						e.setCancelled(true);
						UserMenu menu = new UserMenu();
						menu.createMenu(p, uuid);
						
					}
					
				} else if (!(Bukkit.getServer().getOnlinePlayers().contains(ign))) {
					
					@SuppressWarnings("deprecation")
					OfflinePlayer op = Bukkit.getOfflinePlayer(ign);
					UUID uuid = op.getUniqueId();
					
					if (e.getClick().isLeftClick()) {
						
						e.setCancelled(true);
						
						World world = Bukkit.getWorld(RequestsConfig.getConfig().getString("requests." + uuid + ".world"));
						int posx = RequestsConfig.getConfig().getInt("requests." + uuid + ".x");
						int posy = RequestsConfig.getConfig().getInt("requests." + uuid + ".y");
						int posz = RequestsConfig.getConfig().getInt("requests." + uuid + ".z");
						
						Location loc = new Location(world, posx, posy, posz, p.getLocation().getYaw(), p.getLocation().getPitch());
						p.teleport(loc);
						p.closeInventory();
								
					} else if (e.getClick().isRightClick()) {
						
						e.setCancelled(true);
						UserMenu menu = new UserMenu();
						menu.createMenu(p, uuid);
						
					}
					
				} else {
					e.setCancelled(true);
				}
				
			}
			
		}
		
	}

}

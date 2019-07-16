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

public class RanksMenu implements CommandHandler, Listener {

	ItemStack itemStack;
	
	String menuName = Ref.format("&9&lBlueprint Ranks Menu");
	String creativeName = Ref.format("&9&lCreative Ranks Menu");
    String plotworldName = Ref.format("&9&lPlotworld Ranks Menu");
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.RANKS))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				
				p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + MessagesConfig.getConfig().getString("opened-menu")));
				createMenu(p);
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.RANKS);
	}
	
	public void createMenu(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 36, menuName);
		
		ArrayList<String> creative = new ArrayList<>();
		creative.add(Ref.format("&b♦ The classic Blueprint team."));
		creative.add(Ref.format("&b♦ Participates in the many server"));
		creative.add(Ref.format("&b  projects and builds"));
		Items.createLoredItem(inventory, itemStack, Material.WOODEN_AXE, 11, "&8[&9Creative Ranks&8]", creative);
		
		ArrayList<String> plotworld = new ArrayList<>();
		plotworld.add(Ref.format("&b♦ Plotworld for literally anybody to"));
		plotworld.add(Ref.format("&b  build in, no matter your skill level!"));
		Items.createLoredItem(inventory, itemStack, Material.GRASS_BLOCK, 15, "&8[&bPlotworld Ranks&8]", plotworld);
		
		Items.createItem(inventory, itemStack, Material.SUGAR_CANE, 27, "&c&lGo Back");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 35, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	public void creativeRanks(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 45, creativeName);
		
		ArrayList<String> citizen = new ArrayList<>();
		citizen.add(Ref.format("&9♦ Basic essential commands"));
		citizen.add(Ref.format("&9♦ Cannot build in creatve worlds"));
		Items.createLoredItem(inventory, itemStack, Material.BONE_MEAL, 10, "&f[&7Citizen&f]", citizen);
		
		ArrayList<String> builder = new ArrayList<>();
		builder.add(Ref.format("&9♦ First creative builder rank"));
		builder.add(Ref.format("&9♦ Given WorldEdit and can now"));
		builder.add(Ref.format("&9  build in creative worlds"));
		Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 12, "&f[&aBuilder&f]", builder);
		
		ArrayList<String> apprentice = new ArrayList<>();
		apprentice.add(Ref.format("&9♦ Second creative builder rank"));
		apprentice.add(Ref.format("&9♦ Access to &b/nick&9, &b/hdb&9,"));
		apprentice.add(Ref.format("&9  goBrush and goPaint"));
		Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 14, "&f[&2Apprentice&f]", apprentice);
		
		ArrayList<String> journeyman = new ArrayList<>();
		journeyman.add(Ref.format("&9♦ Third creative builder rank"));
		journeyman.add(Ref.format("&9♦ Acess to &b/hat &9and &b/head"));
		Items.createLoredItem(inventory, itemStack, Material.PINK_DYE, 16, "&f[&dJourneyman&f]", journeyman);
		
		ArrayList<String> architect = new ArrayList<>();
		architect.add(Ref.format("&9♦ Fourth creative builder rank"));
		architect.add(Ref.format("&9♦ Able to upload WorldEdit schematics"));
		Items.createLoredItem(inventory, itemStack, Material.PURPLE_DYE, 19, "&f[&5Architect&f]", architect);
		
		ArrayList<String> master = new ArrayList<>();
		master.add(Ref.format("&9♦ Fifth creative builder rank"));
		master.add(Ref.format("&9♦ Access to coloured nicknames"));
		Items.createLoredItem(inventory, itemStack, Material.DANDELION_YELLOW, 21, "&f[&eMaster&f]", master);
		
		ArrayList<String> grandmaster = new ArrayList<>();
		grandmaster.add(Ref.format("&9♦ Sixth creative builder rank"));
		grandmaster.add(Ref.format("&9♦ Access to coloured chat"));
		Items.createLoredItem(inventory, itemStack, Material.ORANGE_DYE, 23, "&f[&6Grandmaster&f]", grandmaster);
		
		ArrayList<String> infinite = new ArrayList<>();
		infinite.add(Ref.format("&9♦ Seventh creative builder rank"));
		infinite.add(Ref.format("&9♦ Able to request personal worlds"));
		Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 25, "&f[&cInfinite&f]", infinite);
		
		Items.createItem(inventory, itemStack, Material.SUGAR_CANE, 36, "&c&lGo Back");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 44, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	public void plotworldRanks(Player p) {
		
		Inventory inventory = Bukkit.createInventory(null, 54, plotworldName);
		
		ArrayList<String> r1 = new ArrayList<>();
		r1.add(Ref.format("&9♦ Can claim one plot"));
		r1.add(Ref.format("&9♦ Access to 32x32 plotworld"));
		Items.createLoredItem(inventory, itemStack, Material.BONE_MEAL, 10, "&8[&71&8]", r1);
		
		ArrayList<String> r2 = new ArrayList<>();
		r2.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.BONE_MEAL, 11, "&8[&72&8]", r2);
		
		ArrayList<String> r3 = new ArrayList<>();
		r3.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.BONE_MEAL, 12, "&8[&73&8]", r3);
		
		ArrayList<String> r4 = new ArrayList<>();
		r4.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.DANDELION_YELLOW, 13, "&8[&e4&8]", r4);
	
		ArrayList<String> r5 = new ArrayList<>();
		r5.add(Ref.format("&9♦ Additional plot"));
		r5.add(Ref.format("&9♦ Access to 64x64 plotworld"));
		Items.createLoredItem(inventory, itemStack, Material.DANDELION_YELLOW, 14, "&8[&e5&8]", r5);
		
		ArrayList<String> r6 = new ArrayList<>();
		r6.add(Ref.format("&9♦ Additional plot"));
		r6.add(Ref.format("&9♦ Access to &b/nick"));
		Items.createLoredItem(inventory, itemStack, Material.DANDELION_YELLOW, 15, "&8[&e6&8]", r6);
		
		ArrayList<String> r7 = new ArrayList<>();
		r7.add(Ref.format("&9♦ Additional plot"));
		r7.add(Ref.format("&9♦ Access to &b/hdb"));
		Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 16, "&8[&a7&8]", r7);
		
		ArrayList<String> r8 = new ArrayList<>();
		r8.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 19, "&8[&a8&8]", r8);
		
		ArrayList<String> r9 = new ArrayList<>();
		r9.add(Ref.format("&9♦ Additional plot"));
		r9.add(Ref.format("&9♦ Access to 128x128 plotworld"));
		r9.add(Ref.format("&9♦ Access to WorldEdit in plots"));
		Items.createLoredItem(inventory, itemStack, Material.CACTUS_GREEN, 20, "&8[&a9&8]", r9);
		
		ArrayList<String> r10 = new ArrayList<>();
		r10.add(Ref.format("&9♦ Additional plot"));
		r10.add(Ref.format("&9♦ Access to goBrush & goPaint"));
		Items.createLoredItem(inventory, itemStack, Material.PURPLE_DYE, 21, "&8[&510&8]", r10);
		
		ArrayList<String> r11 = new ArrayList<>();
		r11.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.PURPLE_DYE, 22, "&8[&511&8]", r11);
		
		ArrayList<String> r12 = new ArrayList<>();
		r12.add(Ref.format("&9♦ Additional plot"));
		r12.add(Ref.format("&9♦ Able to upload WorldEdit schematics"));
		Items.createLoredItem(inventory, itemStack, Material.PURPLE_DYE, 23, "&8[&512&8]", r12);
		
		ArrayList<String> r13 = new ArrayList<>();
		r13.add(Ref.format("&9♦ Additional plot"));
		r13.add(Ref.format("&9♦ Access to 256x256 plotworld"));
		r13.add(Ref.format("&9♦ Access to &b/banner&9, &b/color&9 & &b/secretblocks"));
		Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 24, "&8[&c13&8]", r13);
		
		ArrayList<String> r14 = new ArrayList<>();
		r14.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 25, "&8[&c14&8]", r14);
		
		ArrayList<String> r15 = new ArrayList<>();
		r15.add(Ref.format("&9♦ Additional plot"));
		r15.add(Ref.format("&9♦ Access to coloured nicknames"));
		Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 28, "&8[&415&8]", r15);
		
		ArrayList<String> r16 = new ArrayList<>();
		r16.add(Ref.format("&9♦ Additional plot"));
		Items.createLoredItem(inventory, itemStack, Material.ROSE_RED, 29, "&8[&416&8]", r16);
		
		ArrayList<String> mb = new ArrayList<>();
		mb.add(Ref.format("&9♦ Unlimited plots"));
		mb.add(Ref.format("&9♦ Access to coloured chat"));
		Items.createLoredItem(inventory, itemStack, Material.ORANGE_DYE, 30, "&8[&6Master Builder&8]", mb);
		
		Items.createItem(inventory, itemStack, Material.SUGAR_CANE, 45, "&c&lGo Back");
		Items.createItem(inventory, itemStack, Material.BONE_MEAL, 53, "&c&lExit Menu");
		
		p.openInventory(inventory);
		
	}
	
	@EventHandler
	public void onRanksClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&8[&9Creative Ranks&8]")) {
				e.setCancelled(true);
				creativeRanks(p);
			} else if (Items.getClickedItem(e, "&8[&bPlotworld Ranks&8]")) {
				e.setCancelled(true);
				plotworldRanks(p);
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
			
		} else if (e.getInventory().getName().equalsIgnoreCase(creativeName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				createMenu(p);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else {
				e.setCancelled(true);
			}
			
		} else if (e.getInventory().getName().equalsIgnoreCase(plotworldName)) {
			
			if (e.getClickedInventory() == null || !e.getCurrentItem().hasItemMeta() || e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				e.setCancelled(true);
				createMenu(p);
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

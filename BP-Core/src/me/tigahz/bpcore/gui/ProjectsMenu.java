package me.tigahz.bpcore.gui;

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
import me.tigahz.bpcore.config.ProjectsConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class ProjectsMenu implements CommandHandler, Listener {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	Inventory i;
	ItemStack itemStack;
	String menuName = Ref.format("&9&lProjects Menu");
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.PROJECTS_MENU))) {
			
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

	public void createMenu(Player p) {
		
		i = Bukkit.createInventory(null, 45, menuName);

		if (ProjectsConfig.getConfig().getBoolean("cities.CityOne.used") == true) {
			Items.createItem(i, itemStack, Material.CACTUS_GREEN, 10, Ref.format("&a&l" + ProjectsConfig.getConfig().getString("cities.CityOne.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 10, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityTwo.used") == true) {
			Items.createItem(i, itemStack, Material.LIGHT_BLUE_DYE, 11, Ref.format("&b&l" + ProjectsConfig.getConfig().getString("cities.CityTwo.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 11, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityThree.used") == true) {
			Items.createItem(i, itemStack, Material.LAPIS_LAZULI, 12, Ref.format("&9&l" + ProjectsConfig.getConfig().getString("cities.CityThree.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 12, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityFour.used") == true) {
			Items.createItem(i, itemStack, Material.PURPLE_DYE, 13, Ref.format("&5&l" + ProjectsConfig.getConfig().getString("cities.CityFour.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 13, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityFive.used") == true) {
			Items.createItem(i, itemStack, Material.PINK_DYE, 14, Ref.format("&d&l" + ProjectsConfig.getConfig().getString("cities.CityFive.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 14, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CitySix.used") == true) {
			Items.createItem(i, itemStack, Material.ROSE_RED, 15, Ref.format("&c&l" + ProjectsConfig.getConfig().getString("cities.CitySix.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 15, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CitySeven.used") == true) {
			Items.createItem(i, itemStack, Material.ORANGE_DYE, 16, Ref.format("&6&l" + ProjectsConfig.getConfig().getString("cities.CitySeven.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 16, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityEight.used") == true) {
			Items.createItem(i, itemStack, Material.ROSE_RED, 19, Ref.format("&c&l" + ProjectsConfig.getConfig().getString("cities.CityEight.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 19, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityNine.used") == true) {
			Items.createItem(i, itemStack, Material.ORANGE_DYE, 20, Ref.format("&6&l" + ProjectsConfig.getConfig().getString("cities.CityNine.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 20, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityTen.used") == true) {
			Items.createItem(i, itemStack, Material.DANDELION_YELLOW, 21, Ref.format("&e&l" + ProjectsConfig.getConfig().getString("cities.CityTen.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 21, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityEleven.used") == true) {
			Items.createItem(i, itemStack, Material.CACTUS_GREEN, 22, Ref.format("&a&l" + ProjectsConfig.getConfig().getString("cities.CityEleven.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 22, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityTwelve.used") == true) {
			Items.createItem(i, itemStack, Material.LIGHT_BLUE_DYE, 23, Ref.format("&b&l" + ProjectsConfig.getConfig().getString("cities.CityTwelve.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 23, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityThirteen.used") == true) {
			Items.createItem(i, itemStack, Material.LAPIS_LAZULI, 24, Ref.format("&9&l" + ProjectsConfig.getConfig().getString("cities.CityThirteen.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 24, Ref.format("&c&lProject TBD"));
		}
		
		if (ProjectsConfig.getConfig().getBoolean("cities.CityFourteen.used") == true) {
			Items.createItem(i, itemStack, Material.PURPLE_DYE, 25, Ref.format("&5&l" + ProjectsConfig.getConfig().getString("cities.CityFourteen.name")));
		} else {
			Items.createItem(i, itemStack, Material.BARRIER, 25, Ref.format("&c&lProject TBD"));
		}
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 36, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 44, "&c&lExit Menu");
		
		p.openInventory(i);
		
	}
	
	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.PROJECTS_MENU);
	}

	@EventHandler
	public void onProjectsClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null) {
				e.setCancelled(true);
			} else if (!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else if (e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&a&l" + ProjectsConfig.getConfig().getString("cities.CityOne.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityOne", Material.CACTUS_GREEN, "a");
			} else if (Items.getClickedItem(e, "&b&l" + ProjectsConfig.getConfig().getString("cities.CityTwo.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityTwo", Material.LIGHT_BLUE_DYE, "b");
			} else if (Items.getClickedItem(e, "&9&l" + ProjectsConfig.getConfig().getString("cities.CityThree.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityThree", Material.LAPIS_LAZULI, "9");
			} else if (Items.getClickedItem(e, "&5&l" + ProjectsConfig.getConfig().getString("cities.CityFour.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityFour", Material.PURPLE_DYE, "5");
			} else if (Items.getClickedItem(e, "&d&l" + ProjectsConfig.getConfig().getString("cities.CityFive.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityFive", Material.PINK_DYE, "d");
			} else if (Items.getClickedItem(e, "&c&l" + ProjectsConfig.getConfig().getString("cities.CitySix.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CitySix", Material.ROSE_RED, "c");
			} else if (Items.getClickedItem(e, "&6&l" + ProjectsConfig.getConfig().getString("cities.CitySeven.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CitySeven", Material.ORANGE_DYE, "6");
			} else if (Items.getClickedItem(e, "&c&l" + ProjectsConfig.getConfig().getString("cities.CityEight.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityEight", Material.ROSE_RED, "c");
			} else if (Items.getClickedItem(e, "&6&l" + ProjectsConfig.getConfig().getString("cities.CityNine.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityNine", Material.ORANGE_DYE, "6");
			} else if (Items.getClickedItem(e, "&e&l" + ProjectsConfig.getConfig().getString("cities.CityTen.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityTen", Material.DANDELION_YELLOW, "e");
			} else if (Items.getClickedItem(e, "&a&l" + ProjectsConfig.getConfig().getString("cities.CityEleven.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityEleven", Material.CACTUS_GREEN, "a");
			} else if (Items.getClickedItem(e, "&b&l" + ProjectsConfig.getConfig().getString("cities.CityTwelve.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityTwelve", Material.LIGHT_BLUE_DYE, "b");
			} else if (Items.getClickedItem(e, "&9&l" + ProjectsConfig.getConfig().getString("cities.CityThirteen.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityThirteen", Material.LAPIS_LAZULI, "9");
			} else if (Items.getClickedItem(e, "&5&l" + ProjectsConfig.getConfig().getString("cities.CityFourteen.name"))) {
				e.setCancelled(true);
				CityMenu city = new CityMenu();
				city.createMenu(p, "CityFourteen", Material.PURPLE_DYE, "5");
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

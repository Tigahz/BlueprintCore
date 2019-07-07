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
import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.rank.Developer;
import me.tigahz.bpcore.rank.Director;
import me.tigahz.bpcore.rank.Moderator;
import me.tigahz.bpcore.rank.ProjectManager;
import me.tigahz.bpcore.rank.ServerManager;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class StaffMenu implements CommandHandler, Listener {
	
	static String menuName = Ref.format("&9&lStaff Menu");
	
	ItemStack itemStack;
	public Inventory i = Bukkit.createInventory(null, 54, menuName);
	
	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}

	
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
	
	public void createMenu(final Player p) {
		
		i = Bukkit.createInventory(null, 54, menuName);
		
		ArrayList<String> directorLore = new ArrayList<>();
		directorLore.add(Ref.format("&9&oThese individuals work"));
		directorLore.add(Ref.format("&9&oas the leaders of the server."));
		directorLore.add(Ref.format("&9&oThey work to help direct things"));
		directorLore.add(Ref.format("&9&oand develop the server."));
		Items.createLoredItem(i, itemStack, Material.LAPIS_LAZULI, 0, "&6[&9&lDirector&r&6]", directorLore);
		
		@SuppressWarnings("unchecked")
		final List<Director> directors = (List<Director>) StaffConfig.getConfig().getList("directors");
		directors.forEach(it -> it.insertInto(i));
		
		ArrayList<String> moderatorLore = new ArrayList<>();
		moderatorLore.add(Ref.format("&c&oModerators try and manage"));
		moderatorLore.add(Ref.format("&c&oissues you may have, and will"));
		moderatorLore.add(Ref.format("&c&ohelp you if you need it."));
		Items.createLoredItem(i, itemStack, Material.ROSE_RED, 9, "&6[&c&lModerator&r&6]", moderatorLore);
		
		@SuppressWarnings("unchecked")
		final List<Moderator> moderators = (List<Moderator>) StaffConfig.getConfig().getList("moderators");
		moderators.forEach(it -> it.insertInto(i));
		
		ArrayList<String> smLore = new ArrayList<>();
		smLore.add(Ref.format("&b&oThey are here to be the eyes and"));
		smLore.add(Ref.format("&b&oears for the higher staff, but"));
		smLore.add(Ref.format("&b&ocan do some things themselves."));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 18, "&6[&b&lServer Manager&r&6]", smLore);

		@SuppressWarnings("unchecked")
		final List<ServerManager> sm = (List<ServerManager>) StaffConfig.getConfig().getList("server-managers");
		sm.forEach(it -> it.insertInto(i));
		
		ArrayList<String> pmLore = new ArrayList<>();
		pmLore.add(Ref.format("&b&oThese people lead a project"));
		pmLore.add(Ref.format("&b&oor help with the direction of"));
		pmLore.add(Ref.format("&b&oa large scale build."));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 27, "&6[&b&lProject Manager&r&6]", pmLore);

		@SuppressWarnings("unchecked")
		final List<ProjectManager> pm = (List<ProjectManager>) StaffConfig.getConfig().getList("project-managers");
		pm.forEach(it -> it.insertInto(i));
		
		ArrayList<String> devLore = new ArrayList<>();
		devLore.add(Ref.format("&b&oThese people develop the"));
		devLore.add(Ref.format("&b&oserver and add spicy plugins,"));
		devLore.add(Ref.format("&b&olike this, to the server :)"));
		Items.createLoredItem(i, itemStack, Material.BONE_MEAL, 36, "&6[&fDeveloper&6]", devLore);
		
		@SuppressWarnings("unchecked")
		final List<Developer> developer = (List<Developer>) StaffConfig.getConfig().getList("developers");
		developer.forEach(it -> it.insertInto(i));
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 45, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 53, "&c&lExit Menu");
		
		p.openInventory(i);
		
	}
	
	@EventHandler
	public void onStaffMenu(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null) {
				e.setCancelled(true);
			} else if (!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else if (e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				MainMenu menu = new MainMenu();
				menu.createMenu(p);
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

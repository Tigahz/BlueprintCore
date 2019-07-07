package me.tigahz.bpcore.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.rank.Director;
import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class StaffMenu {
	
	static String menuName = Ref.format("&9&lStaff Menu");
	
	ItemStack itemStack;
	public Inventory i = Bukkit.createInventory(null, 54, menuName);
	
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
		
		/*ArrayList<String> moderatorLore = new ArrayList<>();
		moderatorLore.add(Ref.format("&c&oModerators try and manage"));
		moderatorLore.add(Ref.format("&c&oissues you may have, and will"));
		moderatorLore.add(Ref.format("&c&ohelp you if you need it."));
		Items.createLoredItem(i, itemStack, Material.ROSE_RED, 9, "&6[&c&lModerator&r&6]", moderatorLore);
		Convert.createStaff("moderator", "c", 10, i, itemStack);
		
		ArrayList<String> smLore = new ArrayList<>();
		smLore.add(Ref.format("&b&oThey are here to be the eyes and"));
		smLore.add(Ref.format("&b&oears for the higher staff, but"));
		smLore.add(Ref.format("&b&ocan do some things themselves."));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 18, "&6[&b&lServer Manager&r&6]", smLore);
		Convert.createStaff("server-manager", "b", 19, i, itemStack);
		
		ArrayList<String> pmLore = new ArrayList<>();
		pmLore.add(Ref.format("&b&oThese people lead a project"));
		pmLore.add(Ref.format("&b&oor help with the direction of"));
		pmLore.add(Ref.format("&b&oa large scale build."));
		Items.createLoredItem(i, itemStack, Material.LIGHT_BLUE_DYE, 27, "&6[&b&lProject Manager&r&6]", pmLore);
		Convert.createStaff("project-manager", "b", 28, i, itemStack);*/
		
		Items.createItem(i, itemStack, Material.SUGAR_CANE, 45, "&c&lGo Back");
		Items.createItem(i, itemStack, Material.BONE_MEAL, 53, "&c&lExit Menu");
		
		p.openInventory(i);
		
	}
	
}

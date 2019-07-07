package me.tigahz.bpcore.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tigahz.bpcore.config.ProjectsConfig;
import me.tigahz.bpcore.util.Items;
//import me.tigahz.bpcore.util.Ref;

public class CityMenu implements Listener {

	private String city = null;
	
	public CityMenu(String city) {
		this.city = city;
	}
	
	Inventory i;
	ItemStack itemStack;
	//String menuName = Ref.format(ProjectsConfig.getConfig().getString("cities." + city.toString() + ".name"));
	String menuName = "A";
	
	public void createMenu(Player p, Material material) {
		
		i = Bukkit.createInventory(null, 36, menuName);
		
		for (String getWarp : ProjectsConfig.getConfig().getConfigurationSection("cities." + city + ".warps").getKeys(false)) {
			
			@SuppressWarnings("unused")
			String warp = ProjectsConfig.getConfig().getString(getWarp + ".warpName");
			int position = ProjectsConfig.getConfig().getInt(getWarp + ".position");
			Items.createItem(i, itemStack, material, position, "&a&l" + getWarp);
			
		}
		
		p.openInventory(i);
		
	}
	
}

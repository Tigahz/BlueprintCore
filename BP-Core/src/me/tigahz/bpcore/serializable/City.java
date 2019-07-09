package me.tigahz.bpcore.serializable;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import me.tigahz.bpcore.util.Ref;

public final class City implements ConfigurationSerializable {
	
	private final String name;
	private final int position;
	private final String warpName;
	
	public City(Map<String, Object> map) {
		
		this.name = (String) map.get("name");
		this.position = (int) map.get("position");
		this.warpName = (String) map.get("warpName");
		
	}
	
	public Map<String, Object> serialize() {
		
		final Map<String, Object> map = new HashMap<>();
		
		map.put("name", name);
		map.put("position", position);
		map.put("warpName", warpName);
		
		return map;
		
	}

	@SuppressWarnings("deprecation")
	public ItemStack asItemStack(Material material, String colour) {
		
		final ItemStack item = new Wool(material).toItemStack(1);
		final ItemMeta meta = item.getItemMeta();
		final String itemName = Ref.format("&" + colour + "&l" + name);
		
		meta.setDisplayName(itemName);
		item.setItemMeta(meta);
		return item;
		
	}
	
	public void insertInto(Inventory i, Material material, String colour) {
		final ItemStack item = this.asItemStack(material, colour);
		i.setItem(position-1, item);
	}
	
	public String warpName() {
		return Ref.format(warpName);
	}
	
	public String getName(String colour) {
		return Ref.format("&" + colour + "&l" + name);
	}

}

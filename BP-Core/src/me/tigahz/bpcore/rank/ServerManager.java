package me.tigahz.bpcore.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.tigahz.bpcore.util.Ref;

public final class ServerManager implements ConfigurationSerializable {
	
	private final OfflinePlayer op;
	private final int position;
	private final List<String> lore;
	
	@SuppressWarnings("unchecked")
	public ServerManager(Map<String, Object> map) {
		
		final UUID uuid = UUID.fromString((String) map.get("uuid"));
		this.op = Bukkit.getOfflinePlayer(uuid);
		this.position = (int) map.get("position");
		this.lore = (List<String>) map.getOrDefault("lore", new ArrayList<>());
		
	}
	
	public Map<String, Object> serialize() {
		
		final Map<String, Object> map = new HashMap<>();
		
		map.put("uuid", op.getUniqueId().toString());
		map.put("position", position);
		map.put("lore", lore);
		
		return map;
		
	}

	@SuppressWarnings("deprecation")
	public ItemStack asItemStack() {
		
		final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		final SkullMeta meta = (SkullMeta) item.getItemMeta();
		final String username = op.getName();

		if (username == null) {
			meta.setOwner(username);
			meta.setDisplayName(Ref.format("&b&lPlayer hasn't joined"));
		} else {
			meta.setOwner(username);
			meta.setDisplayName(Ref.format("&b&l" + username));
		}
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
		
	}
	
	public void insertInto(Inventory i) {
		final ItemStack item = this.asItemStack();
		i.setItem(position+19, item);
	}

}

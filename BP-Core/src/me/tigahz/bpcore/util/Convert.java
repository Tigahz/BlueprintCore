package me.tigahz.bpcore.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import me.tigahz.bpcore.config.StaffConfig;


public class Convert {
	
	public static String getNameFromUUID(String string) {
		
		String url = "https://api.mojang.com/user/profiles/" + string.toString().replace("-", "") + "/names";
		
		try {
			
			@SuppressWarnings("deprecation")
			String nameJSON = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJSON);
			String playerSlot = nameValue.get(nameValue.size()-1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
			
		} catch (IOException | ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return "error";
		
	}
	
	public static String getNameFromUUID(UUID uuid) {
		
		String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names";
		
		try {
			
			@SuppressWarnings("deprecation")
			String nameJSON = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJSON);
			String playerSlot = nameValue.get(nameValue.size()-1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
			
		} catch (IOException | ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return "error";
		
	}
	
	public static String getCardinalDirection(Player p) {
		
		double r = (p.getLocation().getYaw() - 180) % 360;
		if (r < 0) {
			r += 360;
		}
		
		if (0 <= r && r < 45) {
			return "2";
		} else if (45 <= r && r < 135) {
			return "1";
		} else if (135 <= r && r < 225) {
			return "0";
		} else if (225 <= r && r < 315) {
			return "3";
		} else if (315 <= r && r < 360) {
			return "2";
		} else {
			return null;
		}
		
	}
	
	public static void createStaff(String rank, String colour, int addition, Inventory i, ItemStack itemStack) {
		
		for (String uuid : StaffConfig.getConfig().getConfigurationSection(rank).getKeys(false)) {
			
			UUID u = UUID.fromString(uuid);
			ArrayList<String> ll = new ArrayList<>();
			int pos = Math.addExact(StaffConfig.getConfig().getInt(rank + "." + u + ".position"), addition);
			String getName = Ref.format("&" + colour + "&l" + Convert.getNameFromUUID(u));
			
			for (String lore : StaffConfig.getConfig().getStringList(rank + "." + u + ".lore")) {
				ll.add(Ref.format("&" + colour + "&o" + lore));
				Items.createSkull(i, itemStack, pos, u, getName, ll);
			}
			
		}
		
	}

}

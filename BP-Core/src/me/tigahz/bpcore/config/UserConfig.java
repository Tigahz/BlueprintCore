package me.tigahz.bpcore.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tigahz.bpcore.Main;


public class UserConfig {

	File f;
	FileConfiguration c;
	UUID uuid;
	
	public UserConfig(UUID uuid) {
		this.uuid = uuid;
		f = new File(Main.getInstance().getDataFolder(), "user" + File.separator + uuid + ".yml");
		c = YamlConfiguration.loadConfiguration(f);
	}

	public void createUserFile(UUID uuid) {
		
		f = new File(Main.getInstance().getDataFolder(), "user" + File.separator + uuid + ".yml");
		
		if (!f.exists()) {
			
			exist(uuid);
			
		}
		
	}
	
	public FileConfiguration getUserFile() {
		return c;
	}
	
	public void saveUserFile() {
		
		try {
			getUserFile().save(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void reloadUserFile() {
		
		f = new File(Main.getInstance().getDataFolder(), "user" + File.separator + uuid + ".yml");
		
		if (!f.exists()) {
			
			f.getParentFile().mkdirs();
			Main.getInstance().saveResource("user" + File.separator + uuid + ".yml", false);
			
		}
		
		try {
			c = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	void exist(UUID uuid) {
		
		if (!f.exists()) {
			
			try {
				
				YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
				
				c.set("uuid", uuid.toString());
				c.set("points", 0);
				c.set("nightvision", false);
				c.set("id", true);
				c.set("swear", true);
				c.set("requests", null);
				
				c.save(f);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}

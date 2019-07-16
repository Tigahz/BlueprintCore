package me.tigahz.bpcore.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tigahz.bpcore.Main;

public class RequestsConfig {
	
	private static File f;
	private static FileConfiguration c;
	
	public static FileConfiguration getConfig() {
		return RequestsConfig.c;
	}
	
	public static void createConfig() {
		
		f = new File(Main.getInstance().getDataFolder(), "configs/request.yml");
		
		if (!f.exists()) {
			
			f.getParentFile().mkdirs();
			Main.getInstance().saveResource("configs/request.yml", false);
			
		}
		
		c = new YamlConfiguration();
		
		try {
			
			c.load(f);
			
		}  catch (IOException | InvalidConfigurationException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void loadFile() {
		
		final File f = new File(Main.getInstance().getDataFolder(), "configs/request.yml");
		
		if (!(f.exists())) {
			
			f.getParentFile().mkdirs();
			Main.getInstance().saveResource("configs/request.yml", false);
			
		}
		
		try {

			c = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
			
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveConfig() {
		
		try {
			c.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void eraseFile() {
		
		f.delete();
		
	}

}

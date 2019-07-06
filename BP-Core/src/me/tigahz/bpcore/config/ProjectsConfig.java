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

public class ProjectsConfig {
	
	static File file;
	static FileConfiguration fileConfig;

	public static void createConfig() {
		
		final File file = new File(Main.getInstance().getDataFolder(), "configs/projects.yml");
		
		if (!file.exists()) {
			
			file.getParentFile().mkdirs();
			Main.getInstance().saveResource("configs/projects.yml", false);
			
		}
		
		fileConfig = new YamlConfiguration();
		
		try {
			fileConfig.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	public static FileConfiguration getConfig() {
		return fileConfig;
	}
	
	public static void reloadConfig() {
		
		final File file = new File(Main.getInstance().getDataFolder(), "configs/projects.yml");
		
		if (!file.exists()) {
			
			file.getParentFile().mkdirs();
			Main.getInstance().saveResource("configs/projects.yml", false);
			
		}
		
		try {
			fileConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void saveConfig() {
		
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

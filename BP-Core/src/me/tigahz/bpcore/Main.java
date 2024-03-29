package me.tigahz.bpcore;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.tigahz.bpcore.commands.ApplyCommand;
import me.tigahz.bpcore.commands.BlueprintCommand;
import me.tigahz.bpcore.commands.BlueprintPointsCommand;
import me.tigahz.bpcore.commands.NightVisionCommand;
import me.tigahz.bpcore.commands.PointsCommand;
import me.tigahz.bpcore.commands.PromotionRequestCommand;
import me.tigahz.bpcore.commands.RandomStringCommnad;
import me.tigahz.bpcore.commands.ShowBuildCommand;
import me.tigahz.bpcore.commands.WebsiteCommand;
import me.tigahz.bpcore.config.DefaultConfig;
import me.tigahz.bpcore.config.FAQConfig;
import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.ProjectsConfig;
import me.tigahz.bpcore.config.RequestsConfig;
import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.gui.BuildingToolsMenu;
import me.tigahz.bpcore.gui.CheckBuildsMenu;
import me.tigahz.bpcore.gui.CityMenu;
import me.tigahz.bpcore.gui.HelpMenu;
import me.tigahz.bpcore.gui.MainMenu;
import me.tigahz.bpcore.gui.ProjectsMenu;
import me.tigahz.bpcore.gui.RanksMenu;
import me.tigahz.bpcore.gui.ResourcePackMenu;
import me.tigahz.bpcore.gui.SocialMediaMenu;
import me.tigahz.bpcore.gui.StaffMenu;
import me.tigahz.bpcore.gui.UserMenu;
import me.tigahz.bpcore.handler.IDHandler;
import me.tigahz.bpcore.handler.SwearHandler;
import me.tigahz.bpcore.listeners.JoinEvents;
import me.tigahz.bpcore.listeners.worldedit.LoadCarsEvent;
import me.tigahz.bpcore.listeners.worldedit.TreeWandCommand;
import me.tigahz.bpcore.listeners.worldedit.WorldEditListener;
import me.tigahz.bpcore.serializable.City;
import me.tigahz.bpcore.serializable.Developer;
import me.tigahz.bpcore.serializable.Director;
import me.tigahz.bpcore.serializable.Moderator;
import me.tigahz.bpcore.serializable.ProjectManager;
import me.tigahz.bpcore.serializable.ServerManager;
import me.tigahz.bpcore.util.Ref;

public class Main extends JavaPlugin {
	
	static Plugin instance;
	public static boolean chat = true;
	
	ConsoleCommandSender ccs = Bukkit.getConsoleSender();

	@Override
	public void onEnable() {
		
		ConfigurationSerialization.registerClass(Director.class);
		ConfigurationSerialization.registerClass(Moderator.class);
		ConfigurationSerialization.registerClass(ServerManager.class);
		ConfigurationSerialization.registerClass(ProjectManager.class);
		ConfigurationSerialization.registerClass(Developer.class);
		ConfigurationSerialization.registerClass(City.class);
		
		ccs.sendMessage(Ref.format("&8---------------------------"));
		ccs.sendMessage(Ref.format("&9Blueprint Core - &bEnabled!"));
		ccs.sendMessage(Ref.format("&8---------------------------"));
		
		instance = this;
		
		registerCommands();
		registerListeners();
		registerConfigs();
		
		IDHandler.idRunnable();

	}

	@Override
	public void onDisable() {
		
		ccs.sendMessage(Ref.format("&8---------------------------"));
		ccs.sendMessage(Ref.format("&9Blueprint Core - &bDisabled!"));
		ccs.sendMessage(Ref.format("&8---------------------------"));
		
		instance = null;
		
	}
	
	void registerConfigs() {
		
		DefaultConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig config.yml loaded"));
		
		FAQConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/faq.yml loaded"));
		
		MessagesConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig messages.yml loaded"));
		
		ProjectsConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/projects.yml loaded"));
		
		RequestsConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/request.yml loaded"));
		
		StaffConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/staff.yml loaded"));
		
	}

	void registerListeners() {
		
		org.bukkit.plugin.PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new IDHandler(), this);
		pm.registerEvents(new JoinEvents(), this);
		pm.registerEvents(new SwearHandler(), this);
		pm.registerEvents(new MainMenu(), this);
		pm.registerEvents(new WorldEditListener(), this);
		pm.registerEvents(new ResourcePackMenu(), this);
		pm.registerEvents(new SocialMediaMenu(), this);
		pm.registerEvents(new HelpMenu(), this);
		pm.registerEvents(new StaffMenu(), this);
		pm.registerEvents(new ProjectsMenu(), this);
		pm.registerEvents(new CityMenu(), this);
		pm.registerEvents(new RanksMenu(), this);
		pm.registerEvents(new BuildingToolsMenu(), this);
		pm.registerEvents(new TreeWandCommand(), this);
		pm.registerEvents(new CheckBuildsMenu(), this);
		pm.registerEvents(new UserMenu(), this);
		
	}

	void registerCommands() {
		
		Stream.of(new IDHandler()).forEach(it -> it.register(this));
		Stream.of(new ApplyCommand()).forEach(it -> it.register(this));
		Stream.of(new NightVisionCommand()).forEach(it -> it.register(this));
		Stream.of(new BlueprintCommand()).forEach(it -> it.register(this));
		Stream.of(new SwearHandler()).forEach(it -> it.register(this));
		Stream.of(new MainMenu()).forEach(it -> it.register(this));
		Stream.of(new LoadCarsEvent()).forEach(it -> it.register(this));
		Stream.of(new PointsCommand()).forEach(it -> it.register(this));
		Stream.of(new ResourcePackMenu()).forEach(it -> it.register(this));
		Stream.of(new SocialMediaMenu()).forEach(it -> it.register(this));
		Stream.of(new PromotionRequestCommand()).forEach(it -> it.register(this));
		Stream.of(new HelpMenu()).forEach(it -> it.register(this));
		Stream.of(new StaffMenu()).forEach(it -> it.register(this));
		Stream.of(new ProjectsMenu()).forEach(it -> it.register(this));
		Stream.of(new RanksMenu()).forEach(it -> it.register(this));
		Stream.of(new WebsiteCommand()).forEach(it -> it.register(this));
		Stream.of(new BuildingToolsMenu()).forEach(it -> it.register(this));
		Stream.of(new TreeWandCommand()).forEach(it -> it.register(this));
		Stream.of(new ShowBuildCommand()).forEach(it -> it.register(this));
		Stream.of(new RandomStringCommnad()).forEach(it -> it.register(this));
		Stream.of(new CheckBuildsMenu()).forEach(it -> it.register(this));
		Stream.of(new BlueprintPointsCommand()).forEach(it -> it.register(this));
		
	}
	
	public static Plugin getInstance() {
		return instance;
	}
	
}

package me.tigahz.bpcore;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.tigahz.bpcore.commands.ApplyCommand;
import me.tigahz.bpcore.commands.BlueprintCommand;
import me.tigahz.bpcore.commands.NightVisionCommand;
import me.tigahz.bpcore.commands.PointsCommand;
import me.tigahz.bpcore.commands.PromotionRequestCommand;
import me.tigahz.bpcore.config.DefaultConfig;
import me.tigahz.bpcore.config.FAQConfig;
import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.ProjectsConfig;
import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.gui.HelpMenu;
import me.tigahz.bpcore.gui.MainMenu;
import me.tigahz.bpcore.gui.ResourcePackMenu;
import me.tigahz.bpcore.gui.SocialMediaMenu;
import me.tigahz.bpcore.gui.StaffMenu;
import me.tigahz.bpcore.handler.IDHandler;
import me.tigahz.bpcore.handler.SwearHandler;
import me.tigahz.bpcore.listeners.JoinEvents;
import me.tigahz.bpcore.listeners.LoadCarsEvent;
import me.tigahz.bpcore.util.Ref;

public class Main extends JavaPlugin {
	
	static Plugin instance;
	
	ConsoleCommandSender ccs = Bukkit.getConsoleSender();

	@Override
	public void onEnable() {
		
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
	
	private void registerConfigs() {
		
		DefaultConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig config.yml loaded"));
		
		FAQConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/faq.yml loaded"));
		
		MessagesConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig messages.yml loaded"));
		
		ProjectsConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/projects.yml loaded"));
		
		StaffConfig.createConfig();
		ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/staff.yml loaded"));
		
		
	}

	private void registerListeners() {
		
		org.bukkit.plugin.PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new IDHandler(), this);
		pm.registerEvents(new JoinEvents(), this);
		pm.registerEvents(new SwearHandler(), this);
		pm.registerEvents(new MainMenu(), this);
		pm.registerEvents(new LoadCarsEvent(), this);
		pm.registerEvents(new ResourcePackMenu(), this);
		pm.registerEvents(new SocialMediaMenu(), this);
		pm.registerEvents(new HelpMenu(), this);
		
	}

	private void registerCommands() {
		
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
		
	}
	
	public static Plugin getInstance() {
		return instance;
	}
	
}

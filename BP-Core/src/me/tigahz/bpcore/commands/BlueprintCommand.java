package me.tigahz.bpcore.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import me.tigahz.bpcore.config.DefaultConfig;
import me.tigahz.bpcore.config.FAQConfig;
import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.ProjectsConfig;
import me.tigahz.bpcore.config.StaffConfig;
import me.tigahz.bpcore.gui.MainMenu;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.PermissionsType;
import me.tigahz.bpcore.util.Ref;

public class BlueprintCommand implements CommandHandler {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String l, String[] args) {
		
		final List<String> completions = new ArrayList<>();
		
		Set<String> COMMANDS = new HashSet<>();
		COMMANDS.add("reload");
		COMMANDS.add("menu");
		COMMANDS.add("staff");
		COMMANDS.add("ranks");
		COMMANDS.add("toggleswear");
		
		StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
		Collections.sort(completions);
		
		return completions;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.BLUEPRINT))) {
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("reload")) {
					
					if (cs.hasPermission(PermissionsType.getPermission(PermissionsType.ADMIN))) {
						
						ConsoleCommandSender ccs = Bukkit.getConsoleSender();
						
						cs.sendMessage(Ref.format(messages("prefix") + "&aReloaded configs"));
						
						DefaultConfig.reloadConfig();
						ccs.sendMessage(Ref.format("&9BP &8 - &aConfig config.yml reloaded"));
						
						FAQConfig.reloadConfig();
						ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/faq.yml reloaded"));
						
						MessagesConfig.reloadConfig();
						ccs.sendMessage(Ref.format("&9BP &8 - &aConfig messages.yml reloaded"));
						
						ProjectsConfig.reloadConfig();
						ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/projects.yml reloaded"));
						
						StaffConfig.reloadConfig();
						ccs.sendMessage(Ref.format("&9BP &8 - &aConfig configs/staff.yml reloaded"));

						
					} else {
						
						cs.sendMessage(ErrorType.getError(ErrorType.NO_PERMISSIONS));
						
					}
					
				} else if (args[0].equalsIgnoreCase("menu")) {
					
					if (!(cs instanceof Player)) {
						cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
					} else {
						
						Player p = (Player) cs;
						
						p.sendMessage(Ref.format(messages("prefix") + messages("opened-menu")));
						MainMenu menu = new MainMenu();
						menu.createMenu(p);
						
					}
					
				} else if (args[0].equalsIgnoreCase("staff")) {
					
					if (!(cs instanceof Player)) {
						cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
					} else {
						
						Player p = (Player) cs;
						
						p.sendMessage(Ref.format(messages("prefix") + messages("opened-menu")));
						//open menu
						
					}
					
				} else if (args[0].equalsIgnoreCase("ranks")) {
					
					if (!(cs instanceof Player)) {
						cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
					} else {
						
						Player p = (Player) cs;
						
						p.sendMessage(Ref.format(messages("prefix") + messages("opened-menu")));
						//open menu
						
					}
					
				} else if (args[0].equalsIgnoreCase("toggleswear")) {
					
					//add methods
					cs.sendMessage("toggleswear");
					
				} else {
					
					cs.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
					
				}
				
			} else {
				
				cs.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.BLUEPRINT);
	}
	
	

}

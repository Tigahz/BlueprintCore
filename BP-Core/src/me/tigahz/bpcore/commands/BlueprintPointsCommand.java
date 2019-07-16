package me.tigahz.bpcore.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Ref;

public class BlueprintPointsCommand implements CommandHandler {

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String l, String[] args) {

		final List<String> completions = new ArrayList<>();
		
		Set<String> commandsOne = new HashSet<>();
		commandsOne.add("[add/take] [player] [amount]");
		
		StringUtil.copyPartialMatches(args[0], commandsOne, completions);
		Collections.sort(completions);
		
		return completions;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.BPPOINTS))) {
			
			if (!(args.length == 3)) {
				cs.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
			} else if (args.length == 3) {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if ((p == null) || (!p.isOnline())) {

					@SuppressWarnings("deprecation")
					OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
					UserConfig uc = new UserConfig(op.getUniqueId());
					
					if (args[0].equalsIgnoreCase("add")) {
						
						int amountToAdd = Integer.parseInt(args[2]);
						int oldBalance = uc.getUserFile().getInt("points");
						int newBalance = Math.addExact(oldBalance, amountToAdd);
						uc.getUserFile().set("points", newBalance);
						uc.saveUserFile();
						
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								"Successfully addded " + amountToAdd + " to " + op.getName() + "'s "
										+ "points balance!"));
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								op.getName() + "'s new points balance: " + newBalance));
						
					} else if (args[0].equalsIgnoreCase("take")) {
						
						int amountToRemove = Integer.parseInt(args[2]);
						int oldBalance = uc.getUserFile().getInt("points");
						int newBalance = Math.subtractExact(oldBalance, amountToRemove);
						uc.getUserFile().set("points", newBalance);
						uc.saveUserFile();
						
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								"Successfully taken " + amountToRemove + " from " + op.getName() + "'s "
										+ "points balance!"));
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								op.getName() + "'s new points balance: " + newBalance));
						
					} else {
						
						cs.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
						
					}
					
				} else {
					
					Player op = Bukkit.getPlayer(args[1]);
					UserConfig uc = new UserConfig(op.getUniqueId());
					
					if (args[0].equalsIgnoreCase("add")) {
						
						int amountToAdd = Integer.parseInt(args[2]);
						int oldBalance = uc.getUserFile().getInt("points");
						int newBalance = Math.addExact(oldBalance, amountToAdd);
						uc.getUserFile().set("points", newBalance);
						uc.saveUserFile();
						
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								"Successfully addded " + amountToAdd + " to " + op.getName() + "'s "
										+ "points balance!"));
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								op.getName() + "'s new points balance: " + newBalance));
						
						op.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								amountToAdd + " points have been added to your point balance!"));
						
					} else if (args[0].equalsIgnoreCase("take")) {
						
						int amountToRemove = Integer.parseInt(args[2]);
						int oldBalance = uc.getUserFile().getInt("points");
						int newBalance = Math.subtractExact(oldBalance, amountToRemove);
						uc.getUserFile().set("points", newBalance);
						uc.saveUserFile();
						
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								"Successfully taken " + amountToRemove + " from " + op.getName() + "'s "
										+ "points balance!"));
						cs.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								op.getName() + "'s new points balance: " + newBalance));
						
						op.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") +
								amountToRemove + " points have been removed from your point balance!"));
						
					} else {
						
						cs.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.BPPOINTS);
	}
	
}

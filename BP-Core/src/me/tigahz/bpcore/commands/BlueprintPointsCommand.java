package me.tigahz.bpcore.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;

public class BlueprintPointsCommand implements CommandHandler {

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String l, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.BPPOINTS))) {
			
			if (args.length == 0) {
				
				
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.BPPOINTS);
	}
	
	

}

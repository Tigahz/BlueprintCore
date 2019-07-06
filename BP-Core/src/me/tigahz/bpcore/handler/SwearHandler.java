package me.tigahz.bpcore.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.StringUtil;

import me.tigahz.bpcore.config.DefaultConfig;
import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Ref;

public class SwearHandler implements CommandHandler, Listener {

	String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String l, String[] args) {
		
		final List<String> completions = new ArrayList<>();
		
		Set<String> COMMANDS = new HashSet<>();
		COMMANDS.add("toggle");
		COMMANDS.add("list");
		
		StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
		Collections.sort(completions);
		
		return completions;
		
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.SWEAR))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				UserConfig uc = new UserConfig(p.getUniqueId());
				
				if (args.length == 0) {
					
					if (uc.getUserFile().getBoolean("swear")) {
						
						uc.getUserFile().set("swear", false);
						uc.saveUserFile();
						p.sendMessage(Ref.format(messages("prefix") + "Swearing is now censored"));
						
					} else {
						
						uc.getUserFile().set("swear", true);
						uc.saveUserFile();
						p.sendMessage(Ref.format(messages("prefix") + "Swearing is no longer censored"));
						
					}
					
				} else if (args.length == 1) {
					
					if(args[0].equalsIgnoreCase("toggle")) {
						
						if (uc.getUserFile().getBoolean("swear")) {
							
							uc.getUserFile().set("swear", false);
							uc.saveUserFile();
							p.sendMessage(Ref.format(messages("prefix") + "Swearing is now censored"));
							
						} else {
							
							uc.getUserFile().set("swear", true);
							uc.saveUserFile();
							p.sendMessage(Ref.format(messages("prefix") + "Swearing is no longer censored"));
							
						}
						
					} else if (args[0].equalsIgnoreCase("list")) {
						
						p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "List of swear words:"));
						for (String word : DefaultConfig.getConfig().getStringList("banned-words")) {
							p.sendMessage(Ref.format("&8- &b" + word));
						}
						
					} else {
						
						p.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
						
					}
					
				} else {
					
					p.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
					
				}
				
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.SWEAR);
	}
	
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent e) {
		
		UserConfig uc = new UserConfig(e.getPlayer().getUniqueId());
		
		if (e.isCancelled()) {
			return;
		}
		
		String message = e.getMessage();
		String[] words = message.split(" ");
		String[] a;
		int z = (a = words).length;
		
		for (int i = 0; i < z; i++) {
			
			String word = a[i];
			
			if(isSwear(word)) {
				message = message.replace(word, replaceSwear(word));
			}
			
		}
		
		String formatMessage = e.getFormat().replace("%1$s", e.getPlayer().getDisplayName());
		Bukkit.getConsoleSender().sendMessage(formatMessage.replace("%2$s", e.getMessage()));
		for (Player p : e.getRecipients()) {
			
			if (uc.getUserFile().getBoolean("swear")) {
				p.sendMessage(formatMessage.replace("%2$s", e.getMessage()));
			} else {
				p.sendMessage(formatMessage.replace("%2$s", message));
			}
			
		}
		e.setCancelled(true);
		
	}
	
	private boolean isSwear(String word) {
		for (String w : DefaultConfig.getConfig().getStringList("banned-words")) {
			if (word.contains(w)) {
				return true;
			}
		}
		return false;
	}
	
	private String replaceSwear(String word) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < word.length(); i++) {
			sb.append("*");
		}
		return sb.toString();
	}
	

}

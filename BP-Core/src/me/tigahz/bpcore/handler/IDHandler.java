package me.tigahz.bpcore.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.tigahz.bpcore.Main;
import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Ref;

public class IDHandler implements CommandHandler, Listener {
	
	public static List<Player> hasID = new ArrayList<>();
	
	static void sendBar(Player p, Block b) {
		
		String m = Ref.format(MessagesConfig.getConfig().getString("id-finder").replace("%block%", b.getBlockData().getAsString()));
		
		ActionBarAPI.sendActionBar(p, m);
		
	}
	
	static Block getTarget(Player p, int range) {
		
		BlockIterator bi = new BlockIterator(p, range);
		Block b = bi.next();
		
		while (bi.hasNext()) {
			
			b = bi.next();
			
			if (b.getType() != Material.AIR) {
				break;
			}
			
		}
		
		return b;
		
	}
	
	public static void idRunnable() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (Player p : hasID) {
					sendBar(p, getTarget(p, 6));
				}
				
			}
			
		}.runTaskTimerAsynchronously(Main.getInstance(), 20L, 1L);
		
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.ID_TOGGLE))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				
				UserConfig uc = new UserConfig(p.getUniqueId());
				
				if (uc.getUserFile().getBoolean("id")) {
					
					hasID.remove(p);
					uc.getUserFile().set("id", false);
					uc.saveUserFile();
					p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "IDs toggled &cOFF"));
					
				} else {
					
					hasID.add(p);
					uc.getUserFile().set("id", true);
					uc.saveUserFile();
					p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "IDs toggled &aON"));
					
				}
				
			}
			
			
			
		}
		
		return false;
	}
	
	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.ID_TOGGLE);
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String l, String[] args) {
		return null;
	}

}

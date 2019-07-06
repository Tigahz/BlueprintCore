package me.tigahz.bpcore.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.UserConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.Ref;

public class NightVisionCommand implements CommandHandler {
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.NIGHT_VISION))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
			
				Player p = (Player) cs;
				UUID uuid = p.getUniqueId();
				UserConfig uc = new UserConfig(uuid);
				
				if (uc.getUserFile().getBoolean("nightvision")) {
					
					uc.getUserFile().set("nightvision", false);
					uc.saveUserFile();
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					p.sendMessage(Ref.format(messages("prefix") + "NightVision toggled &cOFF"));
					
				} else {
					
					uc.getUserFile().set("nightvision", true);
					uc.saveUserFile();
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 5, false, false, true));
					p.sendMessage(Ref.format(messages("prefix") + "NightVision toggled &aON"));
					
				}
				
			}
			
		}
		
		return false;
	}

	private String messages(String string) {
		return MessagesConfig.getConfig().getString(string);
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.NIGHT_VISION);
	}

	
	
}

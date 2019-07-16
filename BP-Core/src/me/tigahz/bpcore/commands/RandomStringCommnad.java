package me.tigahz.bpcore.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tigahz.bpcore.config.RequestsConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.PermissionsType;

public class RandomStringCommnad implements CommandHandler {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {

		if (c.getName().equalsIgnoreCase("asdj2k")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) s;
				
				if (!p.hasPermission(PermissionsType.getPermission(PermissionsType.ADMIN))) {
					p.sendMessage(ErrorType.getError(ErrorType.NO_PERMISSIONS));
				} else {
					
					if (!(a.length == 1)) {
						p.sendMessage(ErrorType.getError(ErrorType.INCORRECT_ARGS));
					} else {
						
						@SuppressWarnings("deprecation")
						OfflinePlayer target = Bukkit.getOfflinePlayer(a[0]);
						UUID uuid = target.getUniqueId();
						
						World world = Bukkit.getWorld(RequestsConfig.getConfig().getString("requests." + uuid + ".world"));
						int posx = RequestsConfig.getConfig().getInt("requests." + uuid + ".x");
						int posy = RequestsConfig.getConfig().getInt("requests." + uuid + ".y");
						int posz = RequestsConfig.getConfig().getInt("requests." + uuid + ".z");
						
						float yaw = p.getLocation().getYaw();
						float pitch = p.getLocation().getPitch();
						
						Location loc = new Location(world, posx, posy, posz, yaw, pitch);
						p.teleport(loc);
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return "asdj2k";
	}
	
}

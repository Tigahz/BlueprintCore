package me.tigahz.bpcore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tigahz.bpcore.config.MessagesConfig;
import me.tigahz.bpcore.config.RequestsConfig;
import me.tigahz.bpcore.util.CommandHandler;
import me.tigahz.bpcore.util.CommandType;
import me.tigahz.bpcore.util.ErrorType;
import me.tigahz.bpcore.util.PermissionsType;
import me.tigahz.bpcore.util.Ref;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ShowBuildCommand implements CommandHandler {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {

		if (cmd.getName().equalsIgnoreCase(CommandType.getCommand(CommandType.SHOWBUILD))) {
			
			if (!(cs instanceof Player)) {
				cs.sendMessage(ErrorType.getError(ErrorType.NON_PLAYER_COMMAND_SENDER));
			} else {
				
				Player p = (Player) cs;
				
				String world = ("requests." + p.getUniqueId() + ".world");
				String posX = ("requests." + p.getUniqueId() + ".x");
				String posY = ("requests." + p.getUniqueId() + ".y");
				String posZ = ("requests." + p.getUniqueId() + ".z");

				RequestsConfig.getConfig().set(world, p.getLocation().getWorld().getName());
				RequestsConfig.getConfig().set(posX, p.getLocation().getX());
				RequestsConfig.getConfig().set(posY, p.getLocation().getY());
				RequestsConfig.getConfig().set(posZ, p.getLocation().getZ());
				RequestsConfig.saveConfig();
				
				p.sendMessage(Ref.format(MessagesConfig.getConfig().getString("prefix") + "Request sent! Please wait for staff to approve/deny it."));
				
				for (Player staff : Bukkit.getOnlinePlayers()) {
					
					if (staff.hasPermission(PermissionsType.getPermission(PermissionsType.ADMIN))) {
						
						TextComponent firstLine = new TextComponent("--------------------");
						firstLine.setColor(ChatColor.DARK_GRAY);
						firstLine.setStrikethrough(true);
						firstLine.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						firstLine.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						TextComponent blankone = new TextComponent("                     ");
						blankone.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						blankone.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						TextComponent title = new TextComponent("Build Show Request:");
						title.setColor(ChatColor.BLUE);
						title.setBold(true);
						title.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						title.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						TextComponent user = new TextComponent("♦ User: " + p.getName());
						user.setColor(ChatColor.AQUA);
						user.setBold(true);
						user.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						user.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						TextComponent worldmsg = new TextComponent("♦ World: " + p.getLocation().getWorld().getName());
						worldmsg.setColor(ChatColor.AQUA);
						worldmsg.setBold(true);
						worldmsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						worldmsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						TextComponent secondLine = new TextComponent("--------------------");
						secondLine.setColor(ChatColor.DARK_GRAY);
						secondLine.setStrikethrough(true);
						secondLine.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/asdj2k " + p.getName()));
						secondLine.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Ref.bungeeFormat("&bTeleport to the build!")).create()));
						
						staff.spigot().sendMessage(firstLine);
						staff.spigot().sendMessage(blankone);
						staff.spigot().sendMessage(title);
						staff.spigot().sendMessage(blankone);
						staff.spigot().sendMessage(user);
						staff.spigot().sendMessage(blankone);
						staff.spigot().sendMessage(worldmsg);
						staff.spigot().sendMessage(blankone);
						staff.spigot().sendMessage(secondLine);
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}

	@Override
	public String getName() {
		return CommandType.getCommand(CommandType.SHOWBUILD);
	}

}

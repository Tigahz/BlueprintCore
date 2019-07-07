package me.tigahz.bpcore.util;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import me.tigahz.bpcore.config.StaffConfig;

public class RanksLoader {
	
	UUID uuid;
	
	public RanksLoader(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Set<String> getDirector = StaffConfig.getConfig().getConfigurationSection("director").getKeys(false);
	public int directorPosition = Math.addExact(StaffConfig.getConfig().getInt("director." + uuid + ".position"), 1);
	public List<String> directorLore = StaffConfig.getConfig().getStringList("director." + uuid + ".lore");
	public String getDirectorName = Ref.format("&9&l" + Convert.getNameFromUUID(uuid));
	
	public Set<String> getModerator = StaffConfig.getConfig().getConfigurationSection("moderator").getKeys(false);
	public int moderatorPosition = Math.addExact(StaffConfig.getConfig().getInt("moderator." + uuid + ".position"), 10);
	public List<String> moderatorLore = StaffConfig.getConfig().getStringList("moderator." + uuid + ".lore");
	public String getModeratorName = Ref.format("&c&l" + Convert.getNameFromUUID(uuid));
	
	public Set<String> getServerManager = StaffConfig.getConfig().getConfigurationSection("server-manager").getKeys(false);
	public int serverManagerPosition = Math.addExact(StaffConfig.getConfig().getInt("server-manager." + uuid + ".position"), 10);
	public List<String> serverManagerLore = StaffConfig.getConfig().getStringList("server-manager." + uuid + ".lore");
	public String getServerManagerName = Ref.format("&b&l" + Convert.getNameFromUUID(uuid));

	public Set<String> getProjectManager = StaffConfig.getConfig().getConfigurationSection("project-manager").getKeys(false);
	public int projectManagerPosition = Math.addExact(StaffConfig.getConfig().getInt("project-manager." + uuid + ".position"), 10);
	public List<String> projectManagerLore = StaffConfig.getConfig().getStringList("project-manager." + uuid + ".lore");
	public String getProjectManagerName = Ref.format("&b&l" + Convert.getNameFromUUID(uuid));
	
}

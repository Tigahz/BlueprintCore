package me.tigahz.bpcore.util;

public enum PermissionsType {
	
	RELOAD;
	
	public static String getPermission(PermissionsType type) {
		
		String msg = "";
		
		if (type == RELOAD) {
			msg = "blueprint.reload";
		}
		
		return msg;
		
	}

}

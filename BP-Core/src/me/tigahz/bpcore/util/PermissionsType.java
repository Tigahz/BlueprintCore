package me.tigahz.bpcore.util;

public enum PermissionsType {
	
	ADMIN;
	
	public static String getPermission(PermissionsType type) {
		
		String msg = "";
		
		if (type == ADMIN) {
			msg = "blueprint.admin";
		}
		
		return msg;
		
	}

}

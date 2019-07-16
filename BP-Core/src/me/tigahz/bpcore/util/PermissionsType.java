package me.tigahz.bpcore.util;

public enum PermissionsType {
	
	ADMIN, SCHEM;
	
	public static String getPermission(PermissionsType type) {
		
		String msg = "";
		
		if (type == ADMIN) {
			msg = "blueprint.admin";
		} else if (type == SCHEM) {
			msg = "blueprint.schem";
		}
		
		return msg;
		
	}

}

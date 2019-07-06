package me.tigahz.bpcore.util;

public enum CommandType {

	ID_TOGGLE, APPLY, NIGHT_VISION, BLUEPRINT, SWEAR, MENU, POINTS, BPPOINTS;
	
	public static String getCommand(CommandType cmd) {
		
		String msg = "";
		
		if (cmd == ID_TOGGLE) {
			msg = "id";
		} else if (cmd == APPLY) {
			msg = "apply";
		} else if (cmd == NIGHT_VISION) {
			msg = "nv";
		} else if (cmd == BLUEPRINT) {
			msg = "blueprint";
		} else if (cmd == SWEAR) {
			msg = "swear";
		} else if (cmd == MENU) {
			msg = "menu";
		} else if (cmd == POINTS) {
			msg = "points";
		} else if (cmd == BPPOINTS) {
			msg = "bppoints";
		}
		
		return msg;
		
	}
	
}

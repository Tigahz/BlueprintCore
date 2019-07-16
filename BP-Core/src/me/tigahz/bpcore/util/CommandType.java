package me.tigahz.bpcore.util;

public enum CommandType {

	ID_TOGGLE, APPLY, NIGHT_VISION, BLUEPRINT, SWEAR, MENU, POINTS, BPPOINTS, RP_MENU, SM_MENU, HELP_MENU, PR, STAFF, PROJECTS_MENU, RANKS,
	WEBSITE, BT, CHATFREEZE, PLAYERFREEZE, TREE_MENU, SHOWBUILD, CHECKBUILD;
	
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
		} else if (cmd == RP_MENU) {
			msg = "rp";
		} else if (cmd == SM_MENU) {
			msg = "sm";
		} else if (cmd == HELP_MENU) {
			msg = "help";
		} else if (cmd == PR) {
			msg = "pr";
		} else if (cmd == STAFF) {
			msg = "staff";
		} else if (cmd == PROJECTS_MENU) {
			msg = "projects";
		} else if (cmd == RANKS) {
			msg = "ranks";
		} else if (cmd == WEBSITE) {
			msg = "website";
		} else if (cmd == BT) {
			msg = "buildingtools";
		} else if (cmd == CHATFREEZE) {
			msg = "chatfreeze";
		} else if (cmd == PLAYERFREEZE) {
			msg = "playerfreeze";
		} else if (cmd == TREE_MENU) {
			msg = "gettree";
		} else if (cmd == SHOWBUILD) {
			msg = "showbuild";
		} else if (cmd == CHECKBUILD) {
			msg = "checkbuild";
		}
		
		
		return msg;
		
	}
	
}

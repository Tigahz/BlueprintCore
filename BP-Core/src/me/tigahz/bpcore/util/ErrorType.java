package me.tigahz.bpcore.util;

public enum ErrorType {
	
	NON_PLAYER_COMMAND_SENDER, NO_PERMISSIONS, INCORRECT_ARGS, UNABLE_TO_LOAD, PLAYER_NOT_ONLINE, FREEZE_BYPASS;
	
	public static String getError(ErrorType errors) {
		
		String msg = "";
		String prefix = Ref.format("&4&lError! &c");
		
		if (errors == NON_PLAYER_COMMAND_SENDER) {
			msg = prefix + "Only players can execute this command!";
		} else if (errors == NO_PERMISSIONS) {
			msg = prefix + "You don't have permission to execute this command!";
		} else if (errors == INCORRECT_ARGS) {
			msg = prefix + "Incorrect usage of command!";
		} else if (errors == UNABLE_TO_LOAD) {
			msg = prefix + "Unable to load schematic!";
		} else if (errors == PLAYER_NOT_ONLINE) {
			msg = prefix + "Player is not online!";
		} else if (errors == FREEZE_BYPASS) {
			msg = prefix + "You cannot freeze that player!";
		}
		
		return msg;
		
	}

}

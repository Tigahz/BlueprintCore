package me.tigahz.bpcore.util;

import java.util.UUID;

import me.tigahz.bpcore.config.UserConfig;

public class Points {
	
	public static void addPoints(UUID uuid, int amount) { //Doesn't send message
		
		UserConfig uc = new UserConfig(uuid);
		
		int userBalance = uc.getUserFile().getInt("points");
		int pointsAddition = Math.addExact(userBalance, amount);
		
		uc.getUserFile().set("points", pointsAddition);
		uc.saveUserFile();
		
	}
	
	public static void takePoints(UUID uuid, int amount) { //Doesn't send message
		
		UserConfig uc = new UserConfig(uuid);
		
		int userBalance = uc.getUserFile().getInt("points");
		int pointsReduction = Math.subtractExact(userBalance, amount);
		
		uc.getUserFile().set("points", pointsReduction);
		uc.saveUserFile();
		
	}
	
	public static void setPoints(UUID uuid, int amount) {
		
		UserConfig uc = new UserConfig(uuid);
		
		int newBalance = amount;
		
		uc.getUserFile().set("points", newBalance);
		uc.saveUserFile();
		
	}

}

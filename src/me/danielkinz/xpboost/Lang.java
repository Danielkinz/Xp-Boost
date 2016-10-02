package me.danielkinz.xpboost;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * A class that contains all of the messages and lang
 * 
 * @author Danielkinz
 */
public class Lang {

	private static StringList checkBoost = new StringList();
	private static StringList timeFormat = new StringList();
	private static String xpBoostInactive = "";
	private static String xpBoostSyntax = "";
	private static String start = "";
	private static String end = "";
	private static String inProgress = "";
	private static String clear = "";
	private static String clearError = "";
	private static String permissionError = "";

	/**
	 * Loads the messages from the config
	 * 
	 * @param conf
	 */
	public static void init(FileConfiguration conf) {
		checkBoost = new StringList(conf.getStringList("CheckBoost"));
		timeFormat = new StringList(conf.getStringList("Time Formats"));
		xpBoostInactive = conf.getString("No Active Boost");
		xpBoostSyntax = conf.getString("XpBoostSyntax");
		start = conf.getString("Start");
		end = conf.getString("End");
		inProgress = conf.getString("InProgress");
		clear = conf.getString("Clear");
		clearError = conf.getString("Error Clearing");
		permissionError = conf.getString("Missing Permissions");
	}

	public static StringList getCheckBoost() {
		return new StringList(checkBoost);
	}

	public static StringList getTimeFormat() {
		return new StringList(timeFormat);
	}

	public static String getBoostInactive() {
		return new String(xpBoostInactive);
	}

	public static String getBoostSyntax() {
		return new String(xpBoostSyntax);
	}

	public static String getStart() {
		return new String(start);
	}

	public static String getEnd() {
		return new String(end);
	}

	public static String getInProgress() {
		return new String(inProgress);
	}

	public static String getClear() {
		return new String(clear);
	}

	public static String getClearError() {
		return new String(clearError);
	}

	public static String getPermissionError() {
		return new String(permissionError);
	}

}

package me.danielkinz.xpboost;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * A class with usefull functions
 * 
 * @author Danielkinz
 *
 */
public final class Tools {

	/**
	 * Checks if the string represents an integer
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNumber(final String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Checks if the represents represents a double
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isDouble(final String number) {
		try {
			Double.parseDouble(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Checks if the string represents a short
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isShort(final String number) {
		try {
			Short.parseShort(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Formats the colors in a string
	 * 
	 * @param text
	 * @return
	 */
	public static String format(String text) {
		if (text == null) {
			return null;
		}
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	/**
	 * Removes all the colors from a string
	 * 
	 * @param text
	 * @param replacement
	 * @return
	 */
	public static String formatStrip(String text) {
		return ChatColor.stripColor(format(text));
	}

	/**
	 * Formats the colors in all of the strings in the list
	 * 
	 * @param list
	 * @return
	 */
	public static <U extends Collection<String>> StringList formatList(U list) {
		StringList formatted = new StringList(list);
		for (int i = 0; i < list.size(); i++) {
			formatted.set(i, format(formatted.get(i)));
		}
		return formatted;
	}

	/**
	 * Concates all of the strings into 1 long string connected by the glue
	 * 
	 * @param list
	 * @param connector
	 * @return
	 */
	public static String concate(List<String> list, String glue) {
		String str = "";
		int counter = 0;

		for (counter = 0; counter < list.size(); counter++) {
			str += list.get(counter);
			if (counter != list.size() - 1) {
				str += glue;
			}
		}

		return str.substring(0, str.length() - glue.length());
	}

	/**
	 * Sends multiple messages to the player
	 * 
	 * @param player
	 * @param messages
	 */
	public static void sendMessages(CommandSender player, List<String> messages) {
		for (String msg : messages) {
			player.sendMessage(Tools.format(msg));
		}
	}

	/**
	 * Sends multiple messages to the player
	 * 
	 * @param player
	 * @param messages
	 */
	public static void broadcastMessages(List<String> messages) {
		for (String msg : messages) {
			Bukkit.broadcastMessage(Tools.format(msg));
		}
	}

	public static String toTitle(String s) {
		return WordUtils.capitalize(s.toLowerCase().replace("_", " "));
	}

}

package me.danielkinz.xpboost;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * The /checkboost command handler
 * 
 * @author Danielkinz
 */
public class CheckBoostCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		StringList msg = Lang.getCheckBoost();
		TimeFormat left = new TimeFormat(XpBoost.getExpire() - System.currentTimeMillis());

		// If the multiplier expired
		if (System.currentTimeMillis() > XpBoost.getExpire()) {
			sender.sendMessage(Lang.getBoostInactive());
		} else {
			// Inserts the multiplier
			msg.replace("{multiplier}", String.valueOf(XpBoost.getMultiplier()));

			// Determines the correct message
			if (left.getDays() > 0) {
				msg.replace("{time}", Lang.getTimeFormat().get(0));
			} else if (left.getHours() > 0) {
				msg.replace("{time}", Lang.getTimeFormat().get(1));
			} else if (left.getMinutes() > 0) {
				msg.replace("{time}", Lang.getTimeFormat().get(2));
			} else if (left.getSeconds() > 0) {
				msg.replace("{time}", Lang.getTimeFormat().get(3));
			} else {
				msg.replace("{time}", Lang.getTimeFormat().get(4));
			}

			// Formats the duration into the message
			msg.replace("{days}", String.valueOf(left.getDays()));
			msg.replace("{hours}", String.valueOf(left.getHours()));
			msg.replace("{minutes}", String.valueOf(left.getMinutes()));
			msg.replace("{seconds}", String.valueOf(left.getSeconds()));
		}

		return false;
	}

}

package me.danielkinz.xpboost;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class AdminCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {

		String s = "";
		TimeFormat format = null;
		String msg = Tools.format(Lang.getStart());

		if (!sender.hasPermission("xpboost.admin")) {
			sender.sendMessage(Tools.format(Lang.getPermissionError()));
			return true;
		}

		if (args.length < 2 && !(args.length == 1 && args[0].equalsIgnoreCase("clear"))) {
			sender.sendMessage(Tools.format(Lang.getBoostSyntax()));
			return true;
		}

		if (args[0].equalsIgnoreCase("clear")) {
			if (XpBoost.getExpire() < System.currentTimeMillis()) {
				sender.sendMessage(Tools.format(Lang.getClearError()));
				return true;
			}

			XpBoost.setBoost(1, 0);
			Bukkit.broadcastMessage(Tools.format(Lang.getClear()));

			if (XpBoost.run != null) {
				XpBoost.run.cancel();
				XpBoost.run = null;
			}

			return true;
		}

		if (!Tools.isDouble(args[0])) {
			sender.sendMessage(Tools.format(Lang.getBoostSyntax()));
			return true;
		}

		for (int i = 1; i < args.length; i++) {
			s += args[i] + " ";
		}

		try {
			format = TimeFormat.parse(s);
		} catch (Exception e) {
			sender.sendMessage(Tools.format(Lang.getBoostSyntax()));
			return true;
		}

		if (XpBoost.getExpire() >= System.currentTimeMillis()) {
			sender.sendMessage(Tools.format(Lang.getInProgress()));
			return true;
		}

		XpBoost.setBoost(Double.parseDouble(args[0]), format.getTimeStamp());

		msg = msg.replace("{multiplier}", String.valueOf(XpBoost.getMultiplier()));

		// Determines the correct message
		if (format.getDays() > 0) {
			msg = msg.replace("{time}", Lang.getTimeFormat().get(0));
		} else if (format.getHours() > 0) {
			msg = msg.replace("{time}", Lang.getTimeFormat().get(1));
		} else if (format.getMinutes() > 0) {
			msg = msg.replace("{time}", Lang.getTimeFormat().get(2));
		} else if (format.getSeconds() > 0) {
			msg = msg.replace("{time}", Lang.getTimeFormat().get(3));
		} else {
			msg = msg.replace("{time}", Lang.getTimeFormat().get(4));
		}

		// Formats the duration into the message
		msg = msg.replace("{days}", String.valueOf(format.getDays()));
		msg = msg.replace("{hours}", String.valueOf(format.getHours()));
		msg = msg.replace("{minutes}", String.valueOf(format.getMinutes()));
		msg = msg.replace("{seconds}", String.valueOf(format.getSeconds()));

		Bukkit.broadcastMessage(msg);

		if (XpBoost.run != null) {
			XpBoost.run.cancel();
		}

		XpBoost.run = new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(Tools.format(Lang.getEnd()));
				XpBoost.run = null;
			}
		};
		XpBoost.run.runTaskLater(XpBoost.plugin, format.getTimeStamp() / 50);

		return true;
	}

}

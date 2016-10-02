package me.danielkinz.xpboost;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Main plugin class
 * 
 * @author Danielkinz
 */
public class XpBoost extends JavaPlugin {

	private static double multiplier = 0;
	private static long expire = 0L;
	public static BukkitRunnable run = null;
	public static XpBoost plugin = null;

	@Override
	public void onEnable() {
		plugin = this;
		registerConfig();
		registerListeners();
		registerSchedulers();

	}
	
	@Override
	public void onDisable() {
		if (run != null) {
			run.cancel();
		}
	}

	/**
	 * Reads the config and copies the defaults
	 */
	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		multiplier = getConfig().getDouble("Current Boost.Multiplier");
		expire = getConfig().getLong("Current Boost.Expire");
		Lang.init(getConfig());
	}

	/**
	 * Registers event listeners and commands
	 */
	private void registerListeners() {
		getCommand("checkboost").setExecutor(new CheckBoostCommand());
		getCommand("xpboost").setExecutor(new AdminCommand());
		Bukkit.getPluginManager().registerEvents(new ExpListener(), this);
	}

	/**
	 * Registers listeners
	 */
	private void registerSchedulers() {
		if (expire > System.currentTimeMillis()) {
			run = new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage(Tools.format(Lang.getEnd()));
					XpBoost.run = null;
				}
			};
			run.runTaskLater(this, (expire - System.currentTimeMillis()) / 50);
		}
	}

	public static double getMultiplier() {
		return multiplier;
	}

	public static long getExpire() {
		return expire;
	}

	public static void setBoost(double multiplier, long duration) {
		XpBoost.multiplier = multiplier;
		expire = System.currentTimeMillis() + duration;
		plugin.getConfig().set("Current Boost.Multiplier", multiplier);
		plugin.getConfig().set("Current Boost.Expire", expire);
		plugin.saveConfig();
	}

}

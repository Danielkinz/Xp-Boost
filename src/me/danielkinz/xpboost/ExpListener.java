package me.danielkinz.xpboost;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

/**
 * A class that contains the listeners the plugin needs
 * 
 * @author Danielkinz
 */
public class ExpListener implements Listener {

	/**
	 * Multiplies the exp
	 * 
	 * @param event
	 */
	@EventHandler
	public void onExpChange(PlayerExpChangeEvent event) {
		// If the multiplier is still valid
		if (XpBoost.getExpire() >= System.currentTimeMillis()) {
			event.setAmount((int) (event.getAmount() * XpBoost.getMultiplier()));
		}
	}

}

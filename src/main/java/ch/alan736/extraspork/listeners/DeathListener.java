package ch.alan736.extraspork.listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ch.alan736.extraspork.ExtraSpork;
import io.seanbarker.trackerdeaths.event.PlayerDeathEvent;
import io.sporkpgm.player.SporkPlayer;

public class DeathListener implements Listener {

	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
	
	if (!SporkPlayer.getPlayer(event.getPlayer()).isObserver()) {
		
		String Player = event.getPlayer().getName();	
		int Deaths = ExtraSpork.Extraspork.getConfig().getInt("scoreboard."+ Player + ".deaths");

		Deaths++;	
		ExtraSpork.Extraspork.getConfig().set("scoreboard."+ Player + ".deaths", Deaths);
		ExtraSpork.Extraspork.saveConfig();
	}
	}
}

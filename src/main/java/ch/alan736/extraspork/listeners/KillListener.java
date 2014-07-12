package ch.alan736.extraspork.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ch.alan736.extraspork.ExtraSpork;
import io.seanbarker.trackerdeaths.event.PlayerKillPlayerEvent;

public class KillListener implements Listener {

	@EventHandler
	public void onKill(PlayerKillPlayerEvent event) {
	
		String killer = event.getCredited().getName();	
		int Kills = ExtraSpork.Extraspork.getConfig().getInt("scoreboard."+ killer + ".kills");
		
		Kills++;		
		ExtraSpork.Extraspork.getConfig().set("scoreboard."+ killer + ".kills", Kills);
		ExtraSpork.Extraspork.saveConfig();
	}
	
}

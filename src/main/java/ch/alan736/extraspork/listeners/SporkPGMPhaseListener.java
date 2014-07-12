package ch.alan736.extraspork.listeners;

import io.sporkpgm.match.event.MatchPhaseChangeEvent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ch.alan736.extraspork.ExtraSpork;

public class SporkPGMPhaseListener implements Listener {

	@EventHandler
	public void onChangePhase(MatchPhaseChangeEvent event) {
				
		if (event.getTo().toString().contains("Starting")) {
			
			FileConfiguration Config = ExtraSpork.Extraspork.getConfig();
			
			for(String key : Config.getKeys(true)){

				if (key.contains(".kills")) {
					Config.set(key, 0);
					
				}
				else if (key.contains(".deaths")) {
					Config.set(key, 0);
				}
				
				ExtraSpork.Extraspork.saveConfig();
			}
			
		}
		
	}
	
}

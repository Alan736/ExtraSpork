package ch.alan736.extraspork.listeners;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.alan736.extraspork.ExtraSpork;

public class ConnectionListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent event) {
		String Player = event.getPlayer().getName();
		FileConfiguration Config = ExtraSpork.Extraspork.getConfig();
		
		if (Config.getString("scoreboard." + Player + ".kills") == null) {
			Config.set("scoreboard." +  Player, "");
			Config.set("scoreboard." + Player + ".kills", 0);
			Config.set("scoreboard." + Player + ".deaths", 0);
			
			ExtraSpork.Extraspork.saveConfig();

	}

	
}
}

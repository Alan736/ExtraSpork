package ch.alan736.extraspork;



import ch.alan736.extraspork.commands.*;
import ch.alan736.extraspork.listeners.ConnectionListener;



import ch.alan736.extraspork.listeners.DeathListener;
import ch.alan736.extraspork.listeners.KillListener;
import ch.alan736.extraspork.listeners.SporkPGMPhaseListener;
import io.sporkpgm.player.SporkPlayer;







import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public class ExtraSpork extends JavaPlugin {

	public static ExtraSpork Extraspork;
	
	protected CommandsManager<CommandSender> commands;
	
	@Override
	public void onEnable() {
		Extraspork = this;
		getConfig().set("scoreboard", null);
		
		registerCommands();
		registerListeners();
		
	}
	
	@Override
	public void onDisable() {
		for(String key : getConfig().getKeys(false)){
			getConfig().set(key,null);
		}
		saveConfig();
	}
	
	private void registerCommands() {
		this.commands = new CommandsManager<CommandSender>() {
			public boolean hasPermission(CommandSender sender, String perm) {
				return sender.hasPermission(perm);
			}
		};
		CommandsManagerRegistration cmr = new CommandsManagerRegistration(this, this.commands);

		cmr.register(ScoreboardCommands.class);
		

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			this.commands.execute(cmd.getName(), args, sender, sender);
		} catch(CommandPermissionsException e) {
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
		} catch(MissingNestedCommandException e) {
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch(CommandUsageException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch(WrappedCommandException e) {
			if(e.getCause() instanceof NumberFormatException) {
				sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
			} else {
				sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
				e.printStackTrace();
			}
		} catch(CommandException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
		}
		return true;
	}
	
	public void InitKillCounter() {
		
		for(SporkPlayer Player : SporkPlayer.getPlayers()) {
			Player.isObserver();
		}
		this.getConfig().addDefault("PlayerName", 0);
	}
	
	private void registerListeners() {
		registerListener(new ConnectionListener());
		registerListener(new KillListener());
		registerListener(new DeathListener());
		registerListener(new SporkPGMPhaseListener());
	}
	
	public static void registerListener(Listener listener) {
		get().getServer().getPluginManager().registerEvents(listener, get());
	}

	public static ExtraSpork get() {
		return Extraspork;
	}
	
}

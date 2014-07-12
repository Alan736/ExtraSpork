package ch.alan736.extraspork.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.alan736.extraspork.ExtraSpork;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;

public class ScoreboardCommands {

	
	@Command(aliases = {"stats"}, desc = "Shows your kills, deaths and your KD during a match", max = 0)
	public static void stats(CommandContext cmd, CommandSender sender) throws CommandException {
		
		if(!(sender instanceof Player)) {
			throw new CommandException("Only players can request");
		}
		Player Player = (Player) sender;
		String Separator = ChatColor.DARK_PURPLE + "" + ChatColor.STRIKETHROUGH + "-----------";
		String Header = Separator  + ChatColor.AQUA + " Stats for " + Player.getName() + " " + Separator;

		
		int Kills  = ExtraSpork.Extraspork.getConfig().getInt("scoreboard."+ Player.getName() + ".kills");
		int Deaths = ExtraSpork.Extraspork.getConfig().getInt("scoreboard."+ Player.getName() + ".deaths");
		double KD = Kills/(Deaths*1.f);
		if (Double.isNaN(KD)) {
			KD = 0;
		}
		else if (Deaths == 0) {
			KD = Kills;
		}
		double KDScreen = Math.floor(KD * 100) /100;

		
		sender.sendMessage(Header);	
		sender.sendMessage(ChatColor.WHITE + "Kills : " + ChatColor.GREEN + Kills);
		sender.sendMessage(ChatColor.WHITE + "Deaths : " + ChatColor.DARK_RED + Deaths);
		sender.sendMessage(ChatColor.WHITE + "KD : "  + ChatColor.GOLD + KDScreen); 
		
	}
}

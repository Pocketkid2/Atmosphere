package com.github.pocketkid2.atmosphere;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtmosphereCommand implements CommandExecutor {

	private AtmospherePlugin plugin;

	public AtmosphereCommand(AtmospherePlugin p) {
		plugin = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return true;
		}
		Player player = (Player) sender;

		if (args.length < 1) {
			player.sendMessage(ChatColor.RED + "Not enough arguments!");
			return false;
		}

		if (args.length > 1) {
			player.sendMessage(ChatColor.RED + "Too many arguments!");
			return false;
		}

		if (args[0].equalsIgnoreCase("test")) {
			plugin.toggleTest(player);
			player.sendMessage(ChatColor.AQUA + "Atmosphere testing has been turned " + (plugin.isTesting(player) ? ChatColor.GREEN + "on" : ChatColor.RED + "off"));
			return true;
		}
		player.sendMessage(ChatColor.RED + "Unknown argument " + ChatColor.GRAY + args[0]);
		return false;
	}

}

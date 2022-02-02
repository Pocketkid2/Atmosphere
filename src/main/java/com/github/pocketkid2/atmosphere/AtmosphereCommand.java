package com.github.pocketkid2.atmosphere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

		if (args[0].equalsIgnoreCase("test")) {
			plugin.toggleTest(player);
			player.sendMessage(ChatColor.AQUA + "Atmosphere testing has been turned " + (plugin.isTesting(player) ? ChatColor.GREEN + "on" : ChatColor.RED + "off"));
			return true;
		}
		if (args[0].equalsIgnoreCase("list")) {
			List<Material> list = new ArrayList<Material>(Arrays.asList(Material.values()));
			for (Material m : list) {
				player.sendMessage(ChatColor.AQUA + m.toString());
			}
			return true;
		} else {
			player.sendMessage(ChatColor.RED + "Unknown argument " + ChatColor.GRAY + args[0]);
			return false;
		}
	}

}

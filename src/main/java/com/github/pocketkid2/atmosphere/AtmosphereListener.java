package com.github.pocketkid2.atmosphere;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AtmosphereListener implements Listener {

	private AtmospherePlugin plugin;

	public AtmosphereListener(AtmospherePlugin p) {
		plugin = p;
	}

	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && plugin.isTesting(event.getPlayer())) {
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();

			player.sendMessage(ChatColor.AQUA + "You clicked a " + ChatColor.BLUE + block.getType().toString());

			player.sendMessage(ChatColor.AQUA + "This block is " + (Atmosphere.isAirTight(block) ? ChatColor.GREEN + "airtight" : ChatColor.RED + "not airtight"));

			player.sendMessage(ChatColor.AQUA + "This block is " + (block.getType().isSolid() ? ChatColor.GREEN + "solid" : ChatColor.RED + "not solid") + ChatColor.AQUA + " and "
					+ (block.getType().isOccluding() ? ChatColor.GREEN + "occluding" : ChatColor.RED + "not occluding"));
		}
	}
}

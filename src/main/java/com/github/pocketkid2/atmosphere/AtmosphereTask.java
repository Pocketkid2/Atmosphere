package com.github.pocketkid2.atmosphere;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AtmosphereTask extends BukkitRunnable {

	private AtmospherePlugin plugin;

	public AtmosphereTask(AtmospherePlugin p) {
		plugin = p;
	}

	public void run() {
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			if (p.getGameMode() == GameMode.SURVIVAL && plugin.getValues().get(p.getWorld()) != null && plugin.getValues().get(p.getWorld()) < 0.5
					&& isExposedToOpenAir(p)) {
				double damage = 1.0 / plugin.getValues().get(p.getWorld());
				plugin.getLogger().info("Player " + p.getName() + " is exposed to open air in world " + p.getWorld().getName()
						+ " with atmospheric density " + plugin.getValues().get(p.getWorld()) + " and will take " + damage + " damage");
				p.damage(damage);
				p.sendMessage("You are exposed to the atmosphere!");
			}
		}
	}

	public boolean isExposedToOpenAir(Player player) {
		if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() == Material.JACK_O_LANTERN)
			return false;
		Location target = player.getLocation();
		target.setY(player.getWorld().getMaxHeight());
		return isConnectedTo(target, player.getLocation(), new ArrayList<Location>());
	}

	public boolean isConnectedTo(Location target, Location current, List<Location> checked) {
		for (Location loc : checked) {
			if (loc.getBlockX() == current.getBlockX() && loc.getBlockY() == current.getBlockY() && loc.getBlockZ() == current.getBlockZ())
				return false;
		}
		checked.add(current);

		if (target.getBlockX() == current.getBlockX() && target.getBlockY() == current.getBlockY() && target.getBlockZ() == current.getBlockZ())
			return true;

		if ((current.add(0, 1, 0).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;
		if ((current.add(1, 0, 0).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;
		if ((current.add(-1, 0, 0).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;
		if ((current.add(0, 0, 1).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;
		if ((current.add(0, 0, -1).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;
		if ((current.add(0, -1, 0).getBlock().isEmpty() || current.add(1, 0, 0).getBlock().isPassable())
				&& isConnectedTo(target, current.add(1, 0, 0), checked))
			return true;

		return false;
	}

}

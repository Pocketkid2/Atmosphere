package com.github.pocketkid2.atmosphere;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AtmospherePlugin extends JavaPlugin {

	private Map<Player, Boolean> test;

	@Override
	public void onEnable() {
		test = new HashMap<Player, Boolean>();

		getServer().getPluginManager().registerEvents(new AtmosphereListener(this), this);

		getCommand("atmosphere").setExecutor(new AtmosphereCommand(this));

		getLogger().info("Enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}

	public Map<World, Double> getValues() {
		return null;
	}

	public void toggleTest(Player player) {
		if (test.containsKey(player)) {
			test.put(player, !test.get(player).booleanValue());
		} else {
			test.put(player, true);
		}
	}

	public boolean isTesting(Player player) {
		if (test.containsKey(player))
			return test.get(player);
		return false;
	}
}

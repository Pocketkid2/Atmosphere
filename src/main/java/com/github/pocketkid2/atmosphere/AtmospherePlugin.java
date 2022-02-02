package com.github.pocketkid2.atmosphere;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AtmospherePlugin extends JavaPlugin {

	private Map<World, Double> values;
	private Map<Player, Boolean> test;

	@Override
	public void onEnable() {
		test = new HashMap<Player, Boolean>();

		saveDefaultConfig();

		values = new HashMap<World, Double>();
		ConfigurationSection cs = getConfig().getConfigurationSection("worlds");
		for (String key : cs.getKeys(false)) {
			double d = cs.getDouble(key, 1.0);
			World w = Bukkit.getWorld(key);
			if (w == null) {
				getLogger().warning("Couldn't find world " + key);
				continue;
			}
			values.put(w, d);
			getLogger().info("Loaded world " + w.getName() + " with value " + d);
		}

		getServer().getPluginManager().registerEvents(new AtmosphereListener(this), this);

		getCommand("atmosphere").setExecutor(new AtmosphereCommand(this));

		getLogger().info("Enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}

	public Map<World, Double> getValues() {
		return values;
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

package com.github.pocketkid2.atmosphere;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class AtmospherePlugin extends JavaPlugin {

	private Map<World, Double> values;

	@Override
	public void onEnable() {
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
		getLogger().info("Enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}

	public Map<World, Double> getValues() {
		return values;
	}
}

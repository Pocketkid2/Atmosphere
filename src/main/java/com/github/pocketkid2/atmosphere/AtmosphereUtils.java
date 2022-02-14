package com.github.pocketkid2.atmosphere;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class AtmosphereUtils {

	public static boolean isAirTight(Material mat) {
		if (mat.isSolid() && mat.isOccluding())
			return true;
		return false;
	}

	public static boolean isAirTight(Block blk) {
		return isAirTight(blk.getType());
	}

}

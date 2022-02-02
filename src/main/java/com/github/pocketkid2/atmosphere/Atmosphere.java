package com.github.pocketkid2.atmosphere;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * This class is sort of like a Utils class, will handle some of the general
 * calculations and things.
 *
 * @author adam
 *
 */
public class Atmosphere {

	public static boolean isAirTight(Material mat) {
		if (mat.isSolid() && mat.isOccluding())
			return true;
		return false;
	}

	public static boolean isAirTight(Block blk) {
		return isAirTight(blk.getType());
	}

}

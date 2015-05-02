package com.estimote.examples.LukasynoBeacons;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

public class BeaconUtils {
	public static enum Beacolor {
		AQUA, COBALT, MINT, DEFAULT
	};

	private static float range;
	private static Beacolor color;

	public static float getRange() {
		return range;
	}

	public static void setRange(float r) {
		range = r;
	}

	private static boolean isInRange(Beacon b, float range) {
		return Utils.computeAccuracy(b) < range;
	}

	public static Beacolor getColor() {
		return color;
	}

	public static void setColor(Beacolor color) {
		BeaconUtils.color = color;
	}

	public boolean hasEqualMac(Beacon b, String mac) {
		if (mac != null)
			return b.getMacAddress().equals(mac);
		return false;
	}
	// public static void eat(Beacon beacon2, float range) {
	// beacon = beacon2;
	// color = detectColor(beacon);
	// if (isInRange(beacon, range)) {
	//
	// }
	// }
}

package com.estimote.examples.BeaconsBackground;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.estimote.examples.LukasynoBeacons.Beactionable;
import com.estimote.examples.LukasynoBeacons.SolidBeacon;
import com.estimote.sdk.Beacon;

public class MyBeacons implements Beactionable {
	private static MyBeacons instance = new MyBeacons();
	private static Boolean[] statePattern;
	private static Boolean[] currentPattern;

	private static SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
	private static List<SolidBeacon> beaconList = new ArrayList<SolidBeacon>();

	public static MyBeacons getInstance() {
		return instance;
	}

	private MyBeacons() {
		this(new String[] { "0" });
	}

	private MyBeacons(String[] macList) {
		statePattern = new Boolean[macList.length];
		currentPattern = new Boolean[macList.length];
		int i = 0;
		for (String mac : macList) {
			beaconList.add(new SolidBeacon(mac));
			statePattern[i] = false;
			currentPattern[i] = false;
			++i;
		}
	}

	public static void registerBeacon(String mac) {
		beaconList.add(new SolidBeacon(mac));
		statePattern = expand(statePattern, false);
		currentPattern = expand(currentPattern, false);
	}

	private static Boolean[] expand(Boolean[] array, Boolean object) {
		Boolean tempArray[] = new Boolean[array.length + 1];
		int i = 0;
		for (Boolean boo : array) {
			tempArray[i] = boo;
			++i;
		}
		tempArray[array.length] = object;
		return tempArray;
	}

	public void search(Beacon beacon) {
		int i = 0;
		for (SolidBeacon bee : beaconList) {
			if (bee.equals(beacon)) {
				bee.setBeacon(beacon);
				bee.setVisible(true);
				Date currentDate = Calendar.getInstance().getTime();
				bee.setNotification(bee.getName() + " already entered. ["
						+ simple.format(currentDate) + "]");
				currentPattern[i] = true;
			} else {
				bee.setVisible(false);
				currentPattern[i] = false;
			}
			++i;
		}
		if (!Arrays.equals(currentPattern, statePattern))
			new PostDataAsyncTask(beaconList).execute();

	}

	@Override
	public void onEnterArea() {

	}

	@Override
	public void onLeaveArea() {

	}

}

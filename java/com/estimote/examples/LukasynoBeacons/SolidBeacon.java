package com.estimote.examples.LukasynoBeacons;

import com.estimote.sdk.Beacon;
import com.estimote.examples.LukasynoBeacons.BeaconUtils.Beacolor;

public class SolidBeacon {
	private String name = null;
	private String mac = null;
	private Beacolor color = null;
	private Beacon beacon = null;
	private String notification = "";
	private boolean visible = false;

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public SolidBeacon() {
	}

	public SolidBeacon(String mac) {
		this.mac = mac;
	}

	public SolidBeacon(String name, String mac, Beacolor color, Beacon beacon) {
		super();
		this.name = name;
		this.mac = mac;
		this.color = color;
		this.beacon = beacon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Beacolor getColor() {
		return color;
	}

	public void setColor(Beacolor color) {
		this.color = color;
	}

	public Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}

	public boolean equals(SolidBeacon beacon) {
		return beacon.getMac().equals(mac);
	}

	public boolean equals(Beacon beacon) {
		return beacon.getMacAddress().equals(mac);
	}

}

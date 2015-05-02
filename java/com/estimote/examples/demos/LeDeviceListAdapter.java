package com.estimote.examples.demos;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.examples.BeaconsBackground.MyBeacons;
import com.estimote.examples.LukasynoBeacons.BeaconUtils;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

/**
 * Displays basic information about beacon.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class LeDeviceListAdapter extends BaseAdapter {
	private ListBeaconsActivity instance = null; // activity changing layout
	private ArrayList<Beacon> beacons;
	private LayoutInflater inflater;
	private static final int Color_YELLOW = 0x30ffff00;
	private static final int Color_BLUE = 0x300000ff;
	private static final int Color_CYAN = 0x3000ffff;
	private static final int Color_GREEN = 0x3000ff00;

	
	public LeDeviceListAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
		this.beacons = new ArrayList<Beacon>();
		instance = DemosApplication.getBeaconsList();
	}

	public void replaceWith(Collection<Beacon> newBeacons) {
		this.beacons.clear();
		this.beacons.addAll(newBeacons);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return beacons.size();
	}

	@Override
	public Beacon getItem(int position) {
		return beacons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		view = inflateIfRequired(view, position, parent);
		bind(getItem(position), view);
		return view;
	}

	private void bind(Beacon beacon, View view) {
		ViewHolder holder = (ViewHolder) view.getTag();
		if (instance != null) {
			MyBeacons.getInstance().search(beacon);
		}

		holder.majorTextView.setText("Major: " + beacon.getMajor());
		holder.minorTextView.setText("Minor: " + beacon.getMinor());
		holder.measuredPowerTextView.setText("MPower: "
				+ beacon.getMeasuredPower());
		holder.rssiTextView.setText("RSSI: " + beacon.getRssi());
		previousBeacon = beacon;
	}

	private View inflateIfRequired(View view, int position, ViewGroup parent) {
		if (view == null) {
			view = inflater.inflate(R.layout.device_item, null);
			view.setTag(new ViewHolder(view));
		}
		return view;
	}

	static class ViewHolder {
		final TextView macTextView;
		final TextView majorTextView;
		final TextView minorTextView;
		final TextView measuredPowerTextView;
		final TextView rssiTextView;

		ViewHolder(View view) {
			macTextView = (TextView) view.findViewWithTag("mac");
			majorTextView = (TextView) view.findViewWithTag("major");
			minorTextView = (TextView) view.findViewWithTag("minor");
			measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
			rssiTextView = (TextView) view.findViewWithTag("rssi");
		}
	}
}

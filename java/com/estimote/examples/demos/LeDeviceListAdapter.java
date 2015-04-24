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
		// holder.macTextView.setText(String.format("MAC: %s (%.2fm)",
		// beacon.getMacAddress(), Utils.computeAccuracy(beacon)));
		if (instance != null) {
			if (beacon.getMacAddress().equals(MyBeacons.cyan_MAC)) {
				holder.macTextView.setText("Widzê b³êkitnego, jest "
						+ String.format("%.2fm st¹d.",
								Utils.computeAccuracy(beacon)));

				if (Utils.computeAccuracy(beacon) < 4.0) {
					instance.changeColor(Color.CYAN);
					DemosApplication.setCurrentBeaconName(MyBeacons.cyan_name);
					if (MyBeacons.flag4Cyan) {
						MyBeacons.flag4Cyan = false;
						new PostDataAsyncTask().execute();
					}
				} else if(Utils.computeAccuracy(beacon) > 4.0){
					MyBeacons.flag4Cyan = true;
					instance.changeColor(Color.YELLOW);
				}
			} else if (beacon.getMacAddress().equals(MyBeacons.blue_MAC)) {
				holder.macTextView.setText("Widzê kobaltowego, jest "
						+ String.format("%.2fm st¹d.",
								Utils.computeAccuracy(beacon)));

				if (Utils.computeAccuracy(beacon) < 4.0) {
					instance.changeColor(Color.BLUE);
					DemosApplication.setCurrentBeaconName(MyBeacons.blue_name);
					if (MyBeacons.flag4Blue) {
						MyBeacons.flag4Blue = false;
						new PostDataAsyncTask().execute();
					}
					
					// if(MyBeacons.attmepts4Blue == MyBeacons.maxAttmepts)
					// new PostDataAsyncTask().execute();
					// else if(MyBeacons.attmepts4Blue==0)
					// MyBeacons.attmepts4Blue = MyBeacons.maxAttmepts;
					// else
					// MyBeacons.attmepts4Blue--;

					// if (!startedStreaming) {
					// startedStreaming = true;
					// Intent inent = new Intent(
					// "net.majorkernelpanic.spydroid.ui.ANOTHER_ACTIVITY");
					// DemosApplication.getCurrentActivity().startActivity(inent);
					// }

				} else if(Utils.computeAccuracy(beacon) > 4.0){
					MyBeacons.flag4Blue = false;
					instance.changeColor(Color.YELLOW);
				}
			} else if (beacon.getMacAddress().equals(MyBeacons.green_MAC)) {
				holder.macTextView.setText("Widzê zielonego, jest "
						+ String.format("%.2fm st¹d.",
								Utils.computeAccuracy(beacon)));

				if (Utils.computeAccuracy(beacon) < 4.0) {
					instance.changeColor(Color.GREEN);
					DemosApplication.setCurrentBeaconName(MyBeacons.green_name);
					if (MyBeacons.flag4Green) {
						MyBeacons.flag4Green = false;
						new PostDataAsyncTask().execute();
					}

					// if(MyBeacons.attmepts4Green == MyBeacons.maxAttmepts)
					// new PostDataAsyncTask().execute();
					// else if(MyBeacons.attmepts4Green==0)
					// MyBeacons.attmepts4Green = MyBeacons.maxAttmepts;
					// else
					// MyBeacons.attmepts4Green--;
					//
				}else if(Utils.computeAccuracy(beacon) > 4.0){
					MyBeacons.flag4Green = true;
					instance.changeColor(Color.YELLOW);
				}
			} else {
				holder.macTextView.setText("Inny Beacon");
			}

		}

		holder.majorTextView.setText("Major: " + beacon.getMajor());
		holder.minorTextView.setText("Minor: " + beacon.getMinor());
		holder.measuredPowerTextView.setText("MPower: "
				+ beacon.getMeasuredPower());
		holder.rssiTextView.setText("RSSI: " + beacon.getRssi());
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

package com.estimote.examples.demos;

import android.app.Application;

import com.estimote.sdk.EstimoteSDK;

/**
 * Main {@link Application} object for Demos. It configures EstimoteSDK.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class DemosApplication extends Application {
	private static ListBeaconsActivity list_instance = null;
	public static ListBeaconsActivity getBeaconsList(){
		return list_instance;
	}
	public static void setBeaconsList(ListBeaconsActivity act){
		list_instance = act;
	}
  @Override
  public void onCreate() {
    super.onCreate();

    // Initializes Estimote SDK with your App ID and App Token from Estimote Cloud.
    // You can find your App ID and App Token in the
    // Apps section of the Estimote Cloud (http://cloud.estimote.com).
    EstimoteSDK.initialize(this, "YOUR APP ID", "YOUR APP TOKEN");

    // Configure verbose debug logging.
    EstimoteSDK.enableDebugLogging(true);
  }
}

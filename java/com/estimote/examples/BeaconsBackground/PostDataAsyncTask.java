package com.estimote.examples.BeaconsBackground;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.estimote.examples.LukasynoBeacons.SolidBeacon;
import com.estimote.examples.demos.DemosApplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PostDataAsyncTask extends AsyncTask {
	private final ProgressDialog dialog = ProgressDialog.show(
			DemosApplication.getCurrentActivity(), "",
			"Wysy³anie danych...", true);
	private List<SolidBeacon> beaconlist;
	public PostDataAsyncTask(List<SolidBeacon> beaconList) {
		this.beaconlist = beaconList;
	}

	@Override
	protected String doInBackground(Object[] params) {

		String server = "http://student.agh.edu.pl/~kryciak/android.php";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(server);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

			String targetMessage = "";
			for(SolidBeacon bee : beaconlist){
				targetMessage += bee.getNotification() + "\n";
			}
			
			nameValuePairs.add(new BasicNameValuePair("android", targetMessage));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			try {
				httpclient.execute(httppost);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			// Execute HTTP Post Request
			// ResponseHandler<String> responseHandler=new
			// BasicResponseHandler();
			// String responseBody = httpclient.execute(httppost,
			// responseHandler);

			// if (Boolean.parseBoolean(responseBody)) {
			// dialog.cancel();
			// }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("HTTP Failed", e.toString());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Object result) {
		// execution of result of Long time consuming operation
		dialog.dismiss();

		Toast toast = Toast.makeText(DemosApplication.getCurrentActivity(),
				"Wys³ano.", 1000);
		toast.show();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// Things to be done before execution of long running operation. For
		// example showing ProgessDialog

		dialog.setCancelable(false);
		dialog.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Object[] sth) {
		// Things to be done while execution of long running operation is in
		// progress. For example updating ProgessDialog

	}
}

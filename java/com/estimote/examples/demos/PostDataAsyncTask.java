package com.estimote.examples.demos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostDataAsyncTask extends AsyncTask<String, Void, String> {
	private static String[] notifications = { " jest w domu",
			" jest blisko", " w³aœnie wszed³ do domu" };
	private static int i = 0;
	private final ProgressDialog dialog = ProgressDialog.show(
			DemosApplication.getCurrentActivity(), "",
			"Wysy³anie informacji...", true);

	@Override
	protected String doInBackground(String... params) {
		// perform long running operation operation

		// SharedPreferences settings = context.getSharedPreferences(PREFS_FILE,
		// 0);
		// String server = settings.getString("server", "");
		String server = "http://student.agh.edu.pl/~kryciak/android.php";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(server);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			String date = Calendar.getInstance().getTime().toString();
			nameValuePairs.add(new BasicNameValuePair("android",
					DemosApplication.getCurrentBeaconName()
							+ notifications[(++i) % 3] + " [" + date + "]"));
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
	protected void onPostExecute(String result) {
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
	protected void onProgressUpdate(Void... values) {
		// Things to be done while execution of long running operation is in
		// progress. For example updating ProgessDialog

	}
}

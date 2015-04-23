package com.estimote.examples.demos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.app.Activity;

public class PostDataAsyncTask extends AsyncTask<String, String, String> {
	private static final String TAG = "Dywuzjon303";
	protected void onPreExecute() {
		super.onPreExecute();
		// do stuff before posting data
	}

	@Override
	protected String doInBackground(String... strings) {
		try {

			// 1 = post text data, 2 = post file
			int actionChoice = 1;

			// post a text data
			if (actionChoice == 1) {
				postText();
			}

			// post a file
			else {
				postFile();
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String lenghtOfFile) {
		// do stuff after posting data
	}

	// this will post our text data
	private void postText() {
		try {
			// url where the data will be posted
			String postReceiverUrl = "http://yourdomain.com/post_data_receiver.php";
			Log.v(TAG, "postURL: " + postReceiverUrl);

			// HttpClient
			HttpClient httpClient = new DefaultHttpClient();

			// post header
			HttpPost httpPost = new HttpPost(postReceiverUrl);

			// add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("firstname", "Mike"));
			nameValuePairs.add(new BasicNameValuePair("lastname", "Dalisay"));
			nameValuePairs.add(new BasicNameValuePair("email",
					"mike@testmail.com"));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// execute HTTP post request
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				String responseStr = EntityUtils.toString(resEntity).trim();
				Log.v(TAG, "Response: " + responseStr);

				// you can add an if statement here and do other actions based
				// on the response
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// will post our text file
	private void postFile() {
		try {

			// the file to be posted
			String textFile = Environment.getExternalStorageDirectory()
					+ "/sample.txt";
			Log.v(TAG, "textFile: " + textFile);

			// the URL where the file will be posted
			String postReceiverUrl = "http://yourdomain.com/post_data_receiver.php";
			Log.v(TAG, "postURL: " + postReceiverUrl);

			// new HttpClient
			HttpClient httpClient = new DefaultHttpClient();

			// post header
			HttpPost httpPost = new HttpPost(postReceiverUrl);

			File file = new File(textFile);
			FileBody fileBody = new FileBody(file);

			MultipartEntity reqEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("file", fileBody);
			httpPost.setEntity(reqEntity);

			// execute HTTP post request
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				String responseStr = EntityUtils.toString(resEntity).trim();
				Log.v(TAG, "Response: " + responseStr);

				// you can add an if statement here and do other actions based
				// on the response
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
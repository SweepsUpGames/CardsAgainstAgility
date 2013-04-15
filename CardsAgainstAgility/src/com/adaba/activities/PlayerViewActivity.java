package com.adaba.activities;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.adaba.R;

public class PlayerViewActivity extends Activity {
	static final String host = "http://129.21.133.101:8080/ServerAgainstAgility/GameServlet";

	protected String game;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_player);

		game = this.getIntent().getStringExtra("game");
		TextView gameName = (TextView) findViewById(R.id.gameName);
		gameName.setText(game);

		AsyncTask<Void, Void, List<String>> cardlistGetTask = new AsyncTask<Void, Void, List<String>>() {
			@Override
			protected List<String> doInBackground(Void... arg0) {
				List<String> cards = new LinkedList<String>();
				try {
					Log.d("PlayerView", "Populating onCreate()");
					HttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(host);
					httpGet.addHeader("req", "hand");
					httpGet.addHeader("game", game);
					httpGet.addHeader("player", "Yossarian");
					Log.d("PlayerView", "Connecting with string " + httpGet.getURI());
					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity resEntityGet = response.getEntity();
					if (resEntityGet != null) {
						String respString = EntityUtils.toString(response.getEntity());
						Log.d("Response", respString);					
						for (String str : respString.split("\n")) cards.add(str);
					}
				} catch (Exception e) { 
					Log.e("PlayerView", e.toString()); }
				return cards;
			}
		};
		cardlistGetTask.execute();
		List<String> games;
		try {
			games = (List<String>) cardlistGetTask.get();
			// Create ListView backed by games returned from GET to server
			ListView handView = (ListView) findViewById(R.id.handList);
			ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);
			handView.setAdapter(adapt);

			handView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int itemInt, long noClue) {
					// Play selected card?
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.e("GameView", e.toString()); 
		} catch (ExecutionException e) {
			e.printStackTrace();
			Log.e("GameView", e.toString()); 
		}
	}

}

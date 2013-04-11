package com.adaba.activities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adaba.R;

public class GameListActivity extends Activity {
	static final String host = "http://129.21.133.89:8080/ServerAgainstAgility/GameServlet";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamelist); // TODO Bill will create layout

		AsyncTask<Void, Void, List<String>> gamelistGetTask = new AsyncTask<Void, Void, List<String>>() {
			@Override
			protected List<String> doInBackground(Void... arg0) {
				List<String> games = new LinkedList<String>();
				try {
					Log.d("GameView", "Populating onCreate()");
					HttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(host + "?req=roomlist");	
					Log.d("GameView", "Connecting with string " + httpGet.getURI());
					HttpResponse response = httpclient.execute(httpGet); 						
					String respString = EntityUtils.toString(response.getEntity());
					Log.d("Response", respString);					
					for (String str : respString.split("\n")) games.add(str);					
				} catch (ClientProtocolException e) { Log.e("GameView", e.toString()); 
				} catch (ParseException e) { Log.e("GameView", e.toString());
				} catch (IOException e) { Log.e("GameView", e.toString()); 
				} catch (Exception e) { Log.e("Oh Shit", e.toString()); }
				return games;
			}
		};
		gamelistGetTask.execute();
		List<String> games;
		try {
			games = (List<String>) gamelistGetTask.get();
			// Create ListView backed by games returned from GET to server
			final ListView gamesView = (ListView) findViewById(R.id.gameRoomList);
			ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);
			gamesView.setAdapter(adapt);

			gamesView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int itemInt, long noClue) {
					createGame(gamesView.getItemAtPosition(itemInt).toString());
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private void createGame(String game){
		Intent intent = new Intent(this, PlayerViewActivity.class);
		intent.putExtra("game", game);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
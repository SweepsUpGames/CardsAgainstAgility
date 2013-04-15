package com.adaba.activities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
import android.widget.Button;
import android.widget.ListView;

import com.adaba.R;

public class GameListActivity extends Activity {
	static final String host = "http://129.21.133.101:8080/ServerAgainstAgility/GameServlet";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<String> games = null;
		setContentView(R.layout.activity_gamelist);
		
		if (savedInstanceState.getStringArrayList("GameList")!=null){
			games = savedInstanceState.getStringArrayList("GameList");
		} else {
			games = getUpdatedGameList();
		}
		
		// Create ListView backed by games returned from GET to server
		final ListView gamesView = (ListView) findViewById(R.id.gameRoomList);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);
		gamesView.setAdapter(adapt);

		gamesView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int itemInt, long noClue) {
				joinGame(gamesView.getItemAtPosition(itemInt).toString());
			}
		});

		// Set create game listenver
		Button createGameButton = (Button) findViewById(R.id.newGameButton);
		createGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createGame();
			}
		});
	}

	private ArrayList<String> getUpdatedGameList() {
		ArrayList<String> games = null;
		AsyncTask<Void, Void, ArrayList<String>> gamelistGetTask = new GetGameRoomList();
		gamelistGetTask.execute();
		try {
			games = (ArrayList<String>) gamelistGetTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
		return games;
	}

	private void joinGame(String game) {
		// Do POST to tell server we're joining a game
		try {
			HttpPost httpPost = new HttpPost(host);
			httpPost.addHeader("action", "join");
			httpPost.addHeader("game", game);
			Log.d("GameView", "Sending POST with string " + httpPost.getURI());
			new DefaultHttpClient().execute(httpPost); 						
		} catch (Exception e) { Log.e("GameView", e.toString()); }

		// Intent to next activity
		Intent intent = new Intent(this, PlayerViewActivity.class);
		intent.putExtra("game", game);
		startActivity(intent);
	}

	private void createGame() {
		// Do POST to tell server we're joining a game
		try {
			HttpPost httpPost = new HttpPost(host);
			httpPost.addHeader("action", "create");
			httpPost.addHeader("game", "New Test Game");
			Log.d("GameView", "Sending POST with string " + httpPost.getURI());
			new DefaultHttpClient().execute(httpPost);
		} catch (Exception e) { Log.e("GameView", e.toString()); }
		joinGame("New Test Game");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class GetGameRoomList extends AsyncTask<Void, Void, ArrayList<String>> {
		@Override
		protected ArrayList<String> doInBackground(Void... arg0) {
			ArrayList<String> games = new ArrayList<String>();
			try {
				Log.d("GameView", "Populating onCreate()");
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(host + "?req=roomlist");	
				Log.d("GameView", "Connecting with string " + httpGet.getURI());
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity resEntityGet = response.getEntity();
				if (resEntityGet != null) {
					String respString = EntityUtils.toString(response.getEntity());
					Log.d("Response", respString);					
					for (String str : respString.split("\n")) games.add(str);
				}
			} catch (Exception e) { 
				Log.e("GameView", e.toString()); }
			return games;
		}
	};
}
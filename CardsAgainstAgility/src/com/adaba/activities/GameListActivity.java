package com.adaba.activities;

import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Time;
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
	static final String servletURI = "/GameServlet";
	protected Properties props = new Properties();
	private String playerName = "Putin"; //TODO move to a properties lookup
	private Time lastServerCall;
	private ArrayList<String> games;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamelist);
		try {
			// Load properties file
			props.load(getResources().openRawResource(R.raw.system));
		} catch (Exception e) {
			Log.e("PlayerViewActivity", "Error reading host IP from properties", e);
		}

		if (savedInstanceState != null && savedInstanceState.getStringArrayList("GameList")!=null){
			Time old = new Time();
			old.parse(savedInstanceState.getString("GameListTime"));
			Time current = new Time();
			current.setToNow();
			Log.d("Time", "old "+old.format2445());
			Log.d("Time", "current "+current.format2445());
			Log.d("Time", "diff "+Time.compare(old, current));

			if (Time.compare(old, current)<-10){
				Log.d("Time", "True");
				lastServerCall = current;
			} else {
				lastServerCall = old;
			}
			games = savedInstanceState.getStringArrayList("GameList");
		} else {
			games = getUpdatedGameList();
			if (savedInstanceState == null){
				savedInstanceState = new Bundle();
			}
			Time recordedTime = new Time();
			recordedTime.setToNow();
			lastServerCall = recordedTime;
		}

		// Create ListView backed by games returned from GET to server
		final ListView gamesView = (ListView) findViewById(R.id.gameRoomList);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);
		gamesView.setAdapter(adapt);

		gamesView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int itemInt, long noClue) {
				Log.d("GameListActivity", "User clicked game " + gamesView.getItemAtPosition(itemInt) + " in listview");
				joinGame(gamesView.getItemAtPosition(itemInt).toString());
			}
		});

		// Set create game listener
		Button createGameButton = (Button) findViewById(R.id.newGameButton);
		createGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createGame("Test Game Creation");
			}
		});
		Log.d("here","onCreate done");
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		if (games!= null){
			savedInstanceState.putStringArrayList("GameList", games);
		}
		if (lastServerCall!= null){
			savedInstanceState.putString("GameListTime", lastServerCall.format2445());
		}
	}

	/**
	 * Asynchronously GET a list of games from the server
	 * @return List<String> the updated list of games
	 */
	private ArrayList<String> getUpdatedGameList() {		
		AsyncTask<Void, Void, ArrayList<String>> gamelistGetTask = new GetGameList();
		gamelistGetTask.execute();

		ArrayList<String> games = null;
		try {
			games = gamelistGetTask.get();
			Log.d("GameListActivity", "GET fired to update game list");
		} catch (Exception e) {
			Log.e("GameListActivity", "Exception while updating game list", e);
		}
		return games;
	}

	/**
	 * Asynchronously POST to the server to join a game
	 * @param game String name of the game to join
	 */
	private void joinGame(final String game) {
		// lol
		AsyncTask<String, Void, Void> joinPostTask = new AsyncTask<String, Void, Void>() {
			@Override
			protected Void doInBackground(String... args) {
				try {
					// Do POST to tell server we're joining a game
					HttpPost httpPost = new HttpPost(props.getProperty("host") + servletURI);
					httpPost.addHeader("action", "join");
					httpPost.addHeader("game", args[0]);
					httpPost.addHeader("pid", getPID());
					httpPost.addHeader("pname", playerName); // TODO
					Log.d("GameView", "Sending POST with string " + httpPost.getURI());
					new DefaultHttpClient().execute(httpPost); 						
				} catch (Exception e) { Log.e("GameView", e.toString()); }
				joinGameLobby(game);
				return null;
			}
		};
		joinPostTask.execute(new String[] { game });
	}
	
	private void joinGameLobby(String gameName){
		Intent intent = new Intent(this, LobbyViewActivity.class);
		//intent.putExtra("GameRoom", gameName );
		startActivity(intent);
	}

	/**
	 * Asynchronously create a game with the given name by POSTing to the server
	 * @param game String name of the game to create
	 */
	private void createGame(String game) {
		AsyncTask<String, Void, Void> createPostTask = new AsyncTask<String, Void, Void>() {
			@Override
			protected Void doInBackground(String... args) {
				try {
					// Do POST to tell server we're creating a game
					HttpPost httpPost = new HttpPost(props.getProperty("host") + servletURI);
					httpPost.addHeader("action", "create");
					httpPost.addHeader("game", args[0]);
					Log.d("GameView", "Sending POST with string " + httpPost.getURI());
					new DefaultHttpClient().execute(httpPost);
				} catch (Exception e) { Log.e("GameView", e.toString()); }
				joinGame(args[0]);
				return null;
			}
		};
		createPostTask.execute(new String[] { game });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Retrieve a unique device ID used to identify a player
	 * @return String 64-bit hex
	 */
	private String getPID() {
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}

	/**
	 * Asyncronous task used to get a list of games from the server 
	 */
	class GetGameList extends AsyncTask<Void, Void, ArrayList<String>> {
		@Override
		protected ArrayList<String> doInBackground(Void... arg0) {
			ArrayList<String> games = new ArrayList<String>();
			try {
				Log.d("GameView", "Populating onCreate()");
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(props.getProperty("host") + servletURI);	
				httpGet.addHeader("req", "roomlist");
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
package com.adaba.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.adaba.R;
import com.adaba.activities.GameListActivity.GetGameList;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LobbyViewActivity extends Activity {

	static final String servletURI = "/GameServlet";
	protected Properties props = new Properties();
	private String gameRoom;
	private ArrayList<String> players;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_lobby);
		try {
			props.load(getResources().openRawResource(R.raw.system));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameRoom = getIntent().getExtras().getString("GameRoom");
		players = getPlayersList();
		
		TextView gameRoom = (TextView) findViewById(R.id.gameRoom);
		gameRoom.setText(this.gameRoom);
		// Create ListView backed by games returned from GET to server
		
		final ListView gamesView = (ListView) findViewById(R.id.playerList);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, players);
		gamesView.setAdapter(adapt);

	}

	/**
	 * Asynchronously GET a list of players from the server
	 * 
	 * @return List<String> the updated list of players
	 */
	private ArrayList<String> getPlayersList() {
		AsyncTask<Void, Void, ArrayList<String>> playerlistGetTask = new GetPlayerList();
		playerlistGetTask.execute();

		ArrayList<String> players = null;
		try {
			players = playerlistGetTask.get();
			Log.d("LobbyViewActivity", "GET fired to update player list");
		} catch (Exception e) {
			Log.e("LobbyViewActivity", "Exception while updating player list",
					e);
		}
		return players;
	}

	/**
	 * Asyncronous task used to get a list of players from the server
	 */
	class GetPlayerList extends AsyncTask<Void, Void, ArrayList<String>> {
		@Override
		protected ArrayList<String> doInBackground(Void... arg0) {
			ArrayList<String> players = new ArrayList<String>();
			try {
				Log.d("LobbyView", "Populating onCreate()");
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(props.getProperty("host")
						+ servletURI);
				httpGet.addHeader("req", "players");
				httpGet.addHeader("game", gameRoom);
				Log.d("LobbyView", "Connecting with string " + httpGet.getURI());
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity resEntityGet = response.getEntity();
				if (resEntityGet != null) {
					String respString = EntityUtils.toString(response
							.getEntity());
					Log.d("Response", respString);
					for (String str : respString.split("\n"))
						players.add(str);
				}
			} catch (Exception e) {
				Log.e("LobbyView", e.toString());
			}
			return players;
		}
	};

}

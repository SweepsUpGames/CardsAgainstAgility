package com.adaba;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> listOfGames = new LinkedList<String>();
		listOfGames.add("first");
		listOfGames.add("second");
		listOfGames.add("third");
		listOfGames.add("forth");
		listOfGames.add("fifth");
		listOfGames.add("sixth");
		listOfGames.add("seventh");
		
		ListView gameList = (ListView) findViewById(R.id.gameRoomList);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listOfGames);
		gameList.setAdapter(adapt);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

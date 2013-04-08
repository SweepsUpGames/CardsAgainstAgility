package com.adaba.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.adaba.R;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button selectButton = (Button) findViewById(R.id.joinGameButton);
		selectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectGame();
			}
		});
	}

	private void selectGame(){
		Intent intent = new Intent(this, GameListActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
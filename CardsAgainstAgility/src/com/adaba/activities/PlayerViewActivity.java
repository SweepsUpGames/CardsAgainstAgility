package com.adaba.activities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.adaba.R;
import com.adaba.cards.Card;
import com.adaba.cards.Type;
import com.adaba.deck.DeckCreator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlayerViewActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_player);
		
		try {
			List<Card> hand = DeckCreator.makeCoHDeck(getResources().openRawResource(R.raw.white), Type.WHITE);
			List<String> stringHand = new LinkedList<String>();
			
			for(Card c: hand){
				
				stringHand.add(c.toString());
				
			}
			ListView handView = (ListView) findViewById(R.id.handList);
			ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringHand);
			handView.setAdapter(adapt);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}

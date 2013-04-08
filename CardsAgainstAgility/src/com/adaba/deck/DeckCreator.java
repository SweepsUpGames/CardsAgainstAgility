package com.adaba.deck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;
import com.adaba.cards.WhiteCard;
import com.adaba.cards.Type;

public class DeckCreator {

	public static Deck makeCoHDeck(InputStream cardFile, Type type)
			throws IOException {
		InputStreamReader isReader = new InputStreamReader(cardFile);
		BufferedReader reader = new BufferedReader(isReader);
		
List<Card> cardlist;

		if (type == Type.BLACK) {
			cardlist = new ArrayList<Card>();

			for (int i = 0; i <= 10; i++) {

				String cardLine = reader.readLine();
				if (cardLine.equals("###")) {
					break;
				} else {
					String[] parseMe = cardLine.split(",");
					String text = parseMe[0];
					int blanks = Integer.parseInt(parseMe[1]);

					BlackCard card = new BlackCard(text, blanks);
					cardlist.add(card);
				}
			}
			reader.close();
			return new Deck(cardlist);
		}

		else  {
			cardlist = new ArrayList<Card>();
			for (int i = 0; i <= 10; i++) {

				String cardLine = reader.readLine();
				if (cardLine.equals("###")) {
					break;
				} else {
					String text = cardLine;
					WhiteCard card = new WhiteCard(text);
					cardlist.add(card);
				}
			}
			reader.close();
			return new Deck(cardlist);
		}

	
	}
}

package com.adaba.deck;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.adaba.cards.Card;

public class Deck<T extends Card> {
	private List<T> cards;
	public static final int DeckSize = 60;

	public Deck(List<T> cards){
		this.cards=cards;
	}

	public T drawCard(){
		Iterator<T> iter = cards.iterator();

		if(iter.hasNext()){
			T nextCard = iter.next();
			iter.remove();
			return nextCard;
		} else{
			return null;
		}

	}

	public void shuffle(){
		Collections.shuffle(cards);
	}
}

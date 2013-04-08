package com.adaba.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.adaba.cards.*;

public class Deck {

	private List<Card> cards;
	public static final int DeckSize = 60;
	
	
	
	public Deck(List<Card> cards){
		this.cards=cards;
	}
	
	public Card drawCard(){
		Iterator<Card> iter = cards.iterator();

		if(iter.hasNext()){
			Card nextCard = iter.next();
			iter.remove();
			return nextCard;
		}
		else{
			return null;
		}
		
	}
	
	
	public Hand drawHand(){
		List<Card> newHand = new ArrayList<Card>();
		for(int i = 0; i<Hand.HandSize; i++){
			newHand.add(cards.get(i));
		}
		
		return new Hand(newHand);
	}
	
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	
	
	
	
}

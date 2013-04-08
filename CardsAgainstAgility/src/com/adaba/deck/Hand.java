package com.adaba.deck;

import java.util.List;

import com.adaba.cards.*;

public class Hand {

	private List<Card> hand;
	public static final int HandSize = 7;
	
	public Hand(List<Card> cards){
		this.hand = cards;	
	}
	
	public void addCard(Card c){
		hand.add(c);
	}
	
	public String toString(){
		return hand.toString();
	}
	
	
	
}

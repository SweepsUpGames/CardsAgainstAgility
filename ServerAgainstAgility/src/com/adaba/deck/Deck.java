package com.adaba.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.adaba.cards.Card;
import com.adaba.cards.WhiteCard;

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
		List<WhiteCard> newHand = new ArrayList<WhiteCard>();
		for(int i = 0; i<Hand.MaxSize; i++){
			newHand.add((WhiteCard) cards.get(i));
		}

		return new Hand(newHand);
	}

	public void shuffle(){
		Collections.shuffle(cards);
	}

	public class Hand {
		private List<WhiteCard> hand;
		public static final int MaxSize = 7;

		public Hand(List<WhiteCard> cards){ this.hand = cards; }

		public void addCard(WhiteCard c) { hand.add(c); }

		public String toString() {
			StringBuffer buf = new StringBuffer();
			buf.append(String.format("With %d/%d cards:\n", hand.size(), Hand.MaxSize));
			for (WhiteCard card : hand) buf.append(card.toString());
			return buf.toString();
		}

		public WhiteCard getCard(int card) { return hand.get(card); }
		public List<WhiteCard> getCards() { return hand; }
	}
}

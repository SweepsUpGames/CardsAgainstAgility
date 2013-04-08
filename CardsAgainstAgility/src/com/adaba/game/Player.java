package com.adaba.game;

import java.util.HashMap;

import com.adaba.cards.Card;
import com.adaba.deck.Hand;

public class Player
{
	private int wins;
	private Hand hand;
	private String name;
	
	public Player()
	{
		this.wins = 0;
	}
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins)
	{
		this.wins = wins;
	}

	public Hand getHand()
	{
		return hand;
	}

	public Card playCard(int card)
	{
		return hand.getCard(card);
	}
	
	public Player vote(HashMap<Player,Card> nominees)
	{
		return (Player)nominees.keySet().toArray()[0];
	}

	public void win()
	{
		this.wins++;
	}

}

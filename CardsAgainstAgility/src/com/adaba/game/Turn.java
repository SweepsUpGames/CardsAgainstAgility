package com.adaba.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;

public class Turn 
{
	private Player tsar;
	private ArrayList<Player> players;
	private HashMap<Player,Card> choices;
	private final Player winner;
	
	public Turn(BlackCard blackCard, ArrayList<Player> players, int tsar)
	{
		this.tsar = players.get(tsar);
		players.remove(tsar);
		this.players = players;
		this.choices = new HashMap<Player,Card>();
		
		for(int i = 0; i < players.size(); i++)
		{
			for(int j = 0; j < blackCard.getBlanks(); i++)
			{
				//players.get(i).getHand().drawCard();
			}
			choices.put(players.get(i), players.get(i).playCard(0));
		}
		
		this.winner = this.tsar.vote(choices);
		winner.win();
	}
}

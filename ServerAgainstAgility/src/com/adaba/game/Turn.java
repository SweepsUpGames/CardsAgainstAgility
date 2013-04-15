package com.adaba.game;

import java.util.HashMap;
import java.util.List;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;

public class Turn 
{
	private Player tsar;
	private List<Player> players;
	private HashMap<Player,Card> choices;
	private final Player winner;
	
	public Turn(BlackCard blackCard, List<Player> players, int tsar)
	{
		this.players = players;		
		this.choices = new HashMap<Player,Card>();
		this.tsar = players.remove(tsar);
		
		for (Player player : players) {
			for (int i = 0; i < blackCard.getBlanks(); i++) {
				//players.get(i).getHand().drawCard();
			}
			choices.put(player, player.playCard(0));
		}
		
		this.winner = this.tsar.vote(choices);
		winner.win();
	}
}

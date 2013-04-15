package com.adaba.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;
import com.adaba.cards.WhiteCard;
import com.adaba.deck.Deck;

public class Game 
{
	private Player winner;
	private List<Turn> turns;
	private int goal;
	
	private HashMap<Player,Card> choices;
	private HashMap<Player,List<WhiteCard>> hands;
	
	protected final List<Player> players;
	protected final Deck whiteCards;
	protected final Deck blackCards;
	
	public Game(List<Player> players, Deck blackCards, Deck whiteCards)
	{
		this.players = players;
		this.blackCards = blackCards;
		this.whiteCards = whiteCards;
	}
	
	public void addPlayer(Player player) 
	{
		players.add(player);
	}
	
	public void startGame()
	{
		//Start the game
	}
	
	public void startTurn()
	{
		this.choices = new HashMap<Player,Card>();
		for(int i = 0; i < players.size(); i++)
		{
			List<WhiteCard> tmp = hands.get(players.get(i));
			tmp.add((WhiteCard)whiteCards.drawCard());
			hands.put(players.get(i), tmp);
		}
	}
	
	public boolean playCard(Player player, WhiteCard card)
	{
		if(!choices.containsKey(player))
		{
			choices.put(player, card);
		}
		
		if(choices.keySet().size() == players.size() - 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	public Game(List<Player> players, Deck blackCards, Deck whiteCards)
	{
		this.goal = 10;
		this.players = players;
		this.whiteCards = whiteCards;
		this.blackCards = blackCards;
		
		this.turns = new ArrayList<Turn>();
		this.winner = null;
		
		while(this.winner == null)
		{
			for(int i = 0; i < players.size(); i++)
			{
				players.get(i).getHand().addCard((WhiteCard)whiteCards.drawCard());
			}
			
			Turn tmp = new Turn((BlackCard)blackCards.drawCard(), this.players, (this.turns.size() % this.players.size()));
			this.turns.add(tmp);
			
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getWins() == this.goal)
				{
					this.winner = players.get(i);
				}
			}
		}
	}
	*/
}

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
	private int goal;
	
	private HashMap<Player,Card[]> choices;
	private HashMap<Player,List<WhiteCard>> hands;
	
	protected final List<Player> players;
	protected final Deck whiteCards;
	protected final Deck blackCards;
	
	public Game(List<Player> players, Deck blackCards, Deck whiteCards)
	{
		this.players = players;
		this.hands = new HashMap<Player,List<WhiteCard>>();
		
		this.blackCards = blackCards;
		this.whiteCards = whiteCards;
	}
	
	public void addPlayer(Player player) 
	{
		players.add(player);
	}
	
	public void startGame()
	{
		for(int i = 0; i < players.size(); i++)
		{
			int j = 7;
			List<WhiteCard> tmp = new ArrayList<WhiteCard>();
			while(j != 0)
			{
				tmp.add((WhiteCard)whiteCards.drawCard());
				j--;
			}
			hands.put(players.get(i), tmp);
		}
	}
	
	public void startTurn()
	{
		this.choices = new HashMap<Player,Card[]>();
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
			Card[] tmp = {card};
			choices.put(player, tmp);
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
	
	public boolean playCard(Player player, WhiteCard[] cards)
	{
		if(!choices.containsKey(player))
		{
			choices.put(player,cards);
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
	
	public HashMap<Player,Card[]> getChoices()
	{
		return this.choices;
	}
}

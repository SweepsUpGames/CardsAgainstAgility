package com.adaba.game;

import java.util.ArrayList;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;
import com.adaba.cards.Type;

public class Game 
{
	private Player winner;
	private ArrayList<Turn> turns;
	private final int goal;
	private ArrayList<Player> players;
	
	public Game(int goal, ArrayList<Player> players)
	{
		this.goal = goal;
		this.turns = new ArrayList<Turn>();
		this.players = players;
		this.winner = null;
		while(this.winner == null)
		{
			Turn tmp = new Turn(new BlackCard("",1), this.players, (this.turns.size() % this.players.size()));
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
}

package com.adaba.game;

import java.util.List;

import com.adaba.deck.Deck;

public class Game 
{
	private Player winner;
	private List<Turn> turns;
	private int goal;
	
	protected final List<Player> players;
	protected final Deck whiteCards;
	protected final Deck blackCards;
	
	public Game(List<Player> players, Deck whiteCards, Deck blackCards) {
		this.players = players;
		this.whiteCards = whiteCards;
		this.blackCards = blackCards;
	}
	
//	public Game(int goal, List<Player> players)
//	{
//		this.goal = goal;
//		this.turns = new ArrayList<Turn>();
//		this.players = players;
//		this.winner = null;
//		while(this.winner == null)
//		{
//			Turn tmp = new Turn(new BlackCard("",1), this.players, (this.turns.size() % this.players.size()));
//			this.turns.add(tmp);
//			for(int i = 0; i < players.size(); i++)
//			{
//				if(players.get(i).getWins() == this.goal)
//				{
//					this.winner = players.get(i);
//				}
//			}
//		}
//	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
}

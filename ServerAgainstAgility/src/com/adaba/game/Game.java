package com.adaba.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.adaba.cards.BlackCard;
import com.adaba.cards.Card;
import com.adaba.cards.WhiteCard;
import com.adaba.deck.Deck;

public class Game {
	public static final int MAX_HAND_SIZE = 7;
	private int goal;

	private Map<Player,Card> choices;
	private Map<Player,List<WhiteCard>> players;
	private BlackCard tsarCard;

	protected final Deck<WhiteCard> whiteCards;
	protected final Deck<BlackCard> blackCards;

	public Game(List<Player> players, Deck<BlackCard> blackCards, Deck<WhiteCard> whiteCards) {
		this.players = new HashMap<Player,List<WhiteCard>>();

		this.blackCards = blackCards;
		this.whiteCards = whiteCards;
	}

	public void addPlayer(Player player) {
		players.put(player, new LinkedList<WhiteCard>());
	}

	public List<WhiteCard> getPlayerHand(String pid) {
		for (Player player : players.keySet()) if (player.id.equals(pid)) {
			return players.get(player);
		}
		throw new IllegalArgumentException("Passed unknown pid");
	}

	/**
	 * Each player draws cards from the Deck up to MAX_HAND_SIZE
	 */
	public void drawHands() {
		for (Player player : players.keySet()) {			
			List<WhiteCard> hand = new ArrayList<WhiteCard>();
			while(hand.size() < MAX_HAND_SIZE) hand.add((WhiteCard)whiteCards.drawCard());
			players.put(player, hand);
		}
	}

	public boolean playCard(String pid, WhiteCard card) {
		Player player = null;
		for (Player _player : players.keySet()) if (player.id.equals(pid)) player = _player;
		if (player == null) throw new IllegalArgumentException("Passed unknown pid");

		if(!choices.containsKey(player)) choices.put(player, card);

		return (choices.keySet().size() == players.size() - 1); 
	}

	//	public boolean playCard(Player player, WhiteCard[] cards) {
	//		if(!choices.containsKey(player)) {
	//			choices.put(player,cards);
	//		}
	//
	//		return (choices.keySet().size() == players.size() - 1);
	//	}

	public Map<Player,Card> getChoices() {
		return this.choices;
	}

	public Card getCurrentTsarCard() {
		return this.tsarCard;
	}

	public void drawNextTsarCard() {
		this.tsarCard = this.blackCards.drawCard();
	}
}

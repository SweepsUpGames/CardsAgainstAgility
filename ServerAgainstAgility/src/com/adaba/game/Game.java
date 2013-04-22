package com.adaba.game;

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

	/**
	 * Represents the selections Players have made for a given round.
	 * Number of entries should be fewer than the number of players - 1.
	 */
	private Map<Player,Card> choices;

	/**
	 * Represents the hands held by Players
	 */
	private Map<Player,List<WhiteCard>> players;

	private BlackCard tsarCard;
	protected final Deck<WhiteCard> whiteCards;
	protected final Deck<BlackCard> blackCards;

	public Game(List<Player> players, Deck<BlackCard> blackCards, Deck<WhiteCard> whiteCards) {
		this.players = new HashMap<Player,List<WhiteCard>>();

		this.blackCards = blackCards;
		this.whiteCards = whiteCards;
	}

	/**
	 * Get a list of Players in the current game
	 * @return players List<Player>
	 */
	public List<Player> getPlayers() { return new LinkedList<Player>(this.players.keySet()); }

	/**
	 * Add a Player to the current game.
	 * Note: the Player is given a hand that contains no cards.
	 * @param player Player
	 */
	public void addPlayer(Player player) { players.put(player, new LinkedList<WhiteCard>()); }

	/**
	 * Returns the player corresponding to the passed pid 
	 * 
	 * @param pid String id representing a Player
	 * @return player Player
	 * @exception IllegalArgumentException if invalid pid passed
	 */
	public Player getPlayer(String pid) {
		for (Player player : players.keySet()) { 
			if (player.id.equals(pid)) return player;
		}
		throw new IllegalArgumentException("Passed unknown pid");
	}

	/**
	 * Returns the list of WhiteCards representing the hand held by a 
	 * Player in this game. 
	 * 
	 * @param pid String id representing a Player
	 * @return hand List<WhiteCard>
	 * @exception IllegalArgumentException if invalid pid passed
	 */
	public List<WhiteCard> getPlayerHand(String pid) { return players.get(getPlayer(pid)); }

	/**
	 * Iterates through each entry in players, adding cards to the hand held 
	 * by each player until each player's hand contains MAX_HAND_SIZE cards.  
	 */
	public void drawHands() {
		for (Map.Entry<Player, List<WhiteCard>> entry : players.entrySet()) {
			while (entry.getValue().size() < MAX_HAND_SIZE) entry.getValue().add((WhiteCard)whiteCards.drawCard());
		}
	}

	/**
	 * Get the current Tsar card
	 * 
	 * @return tsar card BlackCard
	 */
	public BlackCard getCurrentTsarCard() { return this.tsarCard; }

	/**
	 * Remove a BlackCard from the deck and make it the new Tsar card
	 * 
	 * @return tsar card BlackCard
	 */
	public BlackCard drawNextTsarCard() { 
		this.tsarCard = this.blackCards.drawCard();
		return getCurrentTsarCard();
	}

	/**
	 * Selects a Card held by a Player to be their choice for this round.
	 * 
	 * @param pid String id representing a Player
	 * @param card int representing a card in a Player's hand
	 * @return boolean have all Players selected a card to play?
	 * @exception IllegalArgumentException if invalid pid passed
	 */
	public boolean selectCard(String pid, int card) {
		// Retrieve player; unchecked exception if player does not exist
		Player player = getPlayer(pid);

		// Add/replace player's choice with a card from their hand
		choices.put(player, getPlayerHand(player.id).get(card));

		// Return true if all players have made a selection
		return (choices.keySet().size() == players.size() - 1); 
	}

	/**
	 * Returns a Map from Players to Cards indicating this round's selections 
	 * 
	 * @return choices Map<Player,Card> indicating the cards players have chosen for this round
	 */
	public Map<Player,Card> getChoices() { return this.choices; }	
}

package com.adaba.cards;

import java.util.LinkedList;
import java.util.List;

public class BlackCard implements Card {
	private final String text;
	private final int numBlanks;
	private final List<WhiteCard> cards;

	public BlackCard(String text, int blanks) {
		assert(blanks == 1); // Black cards with multiple blanks are not supported.

		this.text = text;
		this.numBlanks = blanks;
		this.cards = new LinkedList<WhiteCard>();
	}

	public void completeWith(WhiteCard card) {
		assert(cards.size() + 1 <= numBlanks);
		cards.add(card);
	}

	public boolean isComplete() { return numBlanks == cards.size(); }

	public String toString() {
		if (isComplete()) {
			// Card has been filled
			String completedText = this.text;

			// Either substitute a white card's text for a blank, or add it to the end
			if (completedText.contains("____")) {
				completedText = completedText.replace("____", cards.remove(0).text);
			} else {
				completedText = String.format("%s : %s", completedText, cards.remove(0).text);
			}
			return completedText;
		} else {
			// Card has not been filled
			return this.text;
		}
	}
	public String getText(){ return String.format("%s: %d", this.text, this.numBlanks); }
	public int getBlanks() { return numBlanks; }
}

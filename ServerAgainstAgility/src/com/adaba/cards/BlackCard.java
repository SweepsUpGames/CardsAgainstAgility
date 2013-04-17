package com.adaba.cards;

import java.util.LinkedList;
import java.util.List;

public class BlackCard implements Card {
	private final String text;
	private final int blanks;
	private final List<WhiteCard> cards;

	public BlackCard(String text, int blanks) {
		this.text = text;
		this.blanks = blanks;
		this.cards = new LinkedList<WhiteCard>();
	}

	
	public String getText(){ return String.format("%s: %s", this.text); }
	public int getBlanks() { return blanks; }
}

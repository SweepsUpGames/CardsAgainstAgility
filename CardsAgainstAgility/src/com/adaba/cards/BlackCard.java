package com.adaba.cards;

public class BlackCard extends Card {
	private final int blanks;

	public BlackCard(String text, int blanks) {
		super(text, Card.Type.BLACK);
		this.blanks = blanks;
	}

	public int getBlanks() { return blanks; }
}

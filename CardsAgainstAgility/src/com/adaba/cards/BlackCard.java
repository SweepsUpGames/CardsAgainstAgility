package com.adaba.cards;

public class BlackCard implements Card {
	private String text;
	private final int blanks;

	public BlackCard(String text, int blanks) {
		this.text = text;
		this.blanks = blanks;
	}

	
	public String toString(){ return String.format("%s: %s", this.text); }
	public int getBlanks() { return blanks; }
}

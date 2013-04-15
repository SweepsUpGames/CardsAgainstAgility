package com.adaba.cards;

public class WhiteCard implements Card {
	protected final String text;

	public WhiteCard(String text){
		this.text = text;
	}

	public String getText() { return this.text; }


	public String toString() { return String.format("%s ", this.text); }

}

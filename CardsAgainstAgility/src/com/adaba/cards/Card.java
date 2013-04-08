package com.adaba.cards;

public class Card {
	protected final String text;
	protected final Type type;

	public Card(String text, Type type){
		this.text = text;
		this.type = type;
	}

	public String getText() { return this.text; }
	public Type getType() { return this.type; }

	public String toString() { return String.format("%s: %s", this.type, this.text); }

	public enum Type { BLACK, WHITE }
}

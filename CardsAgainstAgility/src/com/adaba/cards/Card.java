package com.adaba.cards;

public class Card {

	private String text;
	private  Type type;
	
	public Card(String text, Type type){
		this.text = text;
		this.type = type;
		
	}
	
	public String toString(){
		return text;
	}
	
	
}

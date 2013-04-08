package com.adaba.cards;

public class BlackCard
{
	private int blanks;
	private String text;
	
	public BlackCard(String text, int blanks)
	{
		this.setBlanks(blanks);
		this.setText(text);
	}

	public int getBlanks() {
		return blanks;
	}

	public void setBlanks(int blanks) {
		this.blanks = blanks;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

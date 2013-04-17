package com.adaba.game;

public class Player
{
	public final String id;
	private String name;

	public Player(String id, String name) {
		this.id = id;
		this.name = name;
	}

	private String getName() { return this.name; }
	private void setName(String name) { this.name = name; }
}

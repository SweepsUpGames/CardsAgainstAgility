package com.adaba.game;

public class Player
{
	public final long id;
	private String name;

	public Player(long id, String name) {
		this.id = id;
		this.name = name;
	}

	private String getName() { return this.name; }
	private void setName(String name) { this.name = name; }
}

package com.adaba.game;

public class Player
{
	public final String id;
	private String name;
	private boolean host;
	private boolean tsar;

	public Player(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public boolean isHost() { return host; }
	public void setIsHost(boolean b) { this.host = b; }
	
	public boolean isTsar() { return tsar; }
	public void setIsTsar(boolean b) { this.tsar = b; }
}

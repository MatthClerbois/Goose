package com.game.goose;

import java.awt.Color;

/****
 * Player class 
 * 
 * @author M³
 * 
 */
public class Player {
	private int pos;
	private static int numberPlayers;	//nombre de joueur(s)
	private String nickname;
	private Color color;
	private boolean canPlay;
	private int turnOrder;
 /** 
  * get the positions of the  players
  * @return pos
  */
	public int getPos() {
		return pos;
	}
/**
 * set the positions of the players
 * @param pos
 */
	public void setPos(int pos) {
		this.pos = pos;
	}
//todo	
	public void setTurn(int turnOrder){ //t
		this.turnOrder = turnOrder; 	//t
	}									//t
	//todo
	public int getTurn(){	//t
		return turnOrder;	//t
	}
/**
 * define if a player can play or not	
 * @param canPlay
 */
	public void setCanPlay(boolean canPlay){
		this.canPlay = canPlay;
	}
/**
 * 	see the status of canPlay
 * @return canPlay
 */
	public boolean getCanPlay(){
		return canPlay;
	}
	
	public static int getNumberPlayers() {
		return numberPlayers;
	}

	public static void setNumberPlayers(int numberPlayers) {
		Player.numberPlayers = numberPlayers;
	}

	/**
	 * default constructor receive name of the players
	 */
	public Player(String name) {
		this.nickname=name;
	}

	/***
	 * setter color
	 * 
	 * @param color
	 *            color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/***
	 * get the color
	 * 
	 * @return color selected by the player
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * set nickname
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * get nickname
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
}

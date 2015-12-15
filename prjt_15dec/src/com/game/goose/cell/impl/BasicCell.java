package com.game.goose.cell.impl;

import com.game.goose.cell.BaseCell;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;

public class BasicCell extends BaseCell{
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="Dirt_road.png";
	/**
	 * BasicCell constructor give url to setImage
	 * @param gf
	 */
	public BasicCell(GameFrame gf){
		super(gf);
		this.setImage(URL);	

	}
	/**
	 * action to do when a player is on this type of cell
	 */
	public void performSpecificAction(){
		
		if((gf.players.get(0).getPos() == gf.players.get(1).getPos()) && (gf.players.get(0).getPos() != 30) && (gf.players.get(0).getPos() != 6)){
			gf.repaint();
			gf.switchPlayer();
			gf.play(-(gf.getNewPosition()- gf.getOldPosition()));   //verifier pq ça redessine qu'une
			gf.repaint();
			gf.switchPlayer();
			gf.repaint();
		}
	
	}
}

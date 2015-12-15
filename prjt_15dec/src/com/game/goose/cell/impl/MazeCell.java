package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class MazeCell extends BaseCell{
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="maze.jpg";
	/**
	 * MazeCell constructor give url to setImage
	 * @param gf
	 */	
	public MazeCell(GameFrame gf){
		super(gf);
		this.setImage(URL);
	}
	/**
	 * action to do when a player is on the case number 42, the player will be send to case number 30
	 */
	public void performSpecificAction(){
			if(gf.players.get(0).getPos() == 42){
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" S'avanture dans un labyrinthe ! " +"\n Malheureusement, pour se retrouver à la case 30");
				gf.play(-12);
				gf.repaint();
			}else {
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" S'avanture dans un labyrinthe ! " +"\n Malheureusement, pour se retrouver à la case 30");
				gf.play(-12);
				gf.repaint();
			}
	}
}

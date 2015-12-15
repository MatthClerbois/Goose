package com.game.goose.cell.impl;
import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class GooseCell extends BaseCell{
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="goose.jpg";
	/**
	 * GooseCell constructor give url to setImage
	 * @param gf
	 */	
	public GooseCell(GameFrame gf){
		super(gf);
		this.setImage(URL);

	}
	/**
	 * action to do when a player is on this type of case, you will go forward for the same number as you did before goign on this cell
	 */
	public void performSpecificAction(){	
		
		switch(gf.players.get(0).getPos()){
			case 5: case 9: case 14: case 18: case 23: case 36: case 41: case 45: case 50: case 54: case 59:
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" Tombe sur une oie, son jet de dé est doublé !! ");
				gf.play(gf.getDe1()+gf.getDe2());
				gf.repaint();
				break;
			default:
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" Tombe sur une oie, son jet de dé est doublé !! ");
				gf.play(gf.getDe1()+gf.getDe2());
				gf.repaint();
		}
	}
}


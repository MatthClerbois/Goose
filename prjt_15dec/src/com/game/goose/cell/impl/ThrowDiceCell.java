package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class ThrowDiceCell extends BaseCell {
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="de.jpg";
	/**
	 * ThrowDiceCell constructor give url to setImage
	 * @param gf
	 */	
	public ThrowDiceCell(GameFrame gf){
		super(gf);
		this.setImage(URL);

	}
	/**
	 * action to do when a player is on the case number 26 or 53, the player will be allowed to throw the dices one more time
	 */
	public void performSpecificAction(){
		
		if(gf.players.get(0).getPos() == 26 || gf.players.get(0).getPos() == 53){
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" est tombé sur la case dé, il peut relancer les dés");
			gf.switchPlayer();
			gf.repaint();
		}else{
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" est tombé sur la case dé, il peut relancer les dés");
			gf.switchPlayer();
			gf.repaint();
		}
		
	}
	
}

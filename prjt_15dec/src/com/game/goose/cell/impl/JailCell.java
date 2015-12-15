package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;
/**
 * define a global variable who contain an image
 */
public class JailCell extends BaseCell {
	private static final String URL="jail_Door.png";
	/**
	 * JailCell constructor give url to setImage
	 * @param gf
	 */	
	public JailCell(GameFrame gf){
		 super(gf);
		this.setImage(URL);

	}
	/**
	 * action to do when a player is on the case number 52, he will wait a turn 
	 */
	public void performSpecificAction(){
		if (gf.getPlayers().get(0).getPos()==52){
			JOptionPane.showMessageDialog(null,"Case Prison ! "+ "\n"+gf.getPlayers().get(0).getNickname()+" doit passer son prochain tour");
			gf.getPlayers().get(0).setCanPlay(false);
		}else {
			JOptionPane.showMessageDialog(null,"Case Prison ! "+ "\n"+gf.getPlayers().get(1).getNickname()+" doit passer son prochain tour");
			gf.getPlayers().get(1).setCanPlay(false);
		}
		gf.repaint();
	}
}

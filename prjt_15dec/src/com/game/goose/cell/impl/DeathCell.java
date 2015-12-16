package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class DeathCell extends BaseCell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * define a global variable who contain an image
	 */
	private static final String URl = "skull.jpg";
	/**
	 * DeathCell constructor give url to setImage
	 * @param gf
	 */	
	public DeathCell(GameFrame gf){
		super(gf);
		this.setImage(URl);
	}
	/**
	 * action to do when a player is on the case number 58, he will be send to the case number 0
	 */
	public void performSpecificAction(){
		if(gf.players.get(0).getPos() == 58){
			JOptionPane.showMessageDialog(null,"Case de la MORT!"+"\n"+gf.getPlayers().get(0).getNickname()+" a march� sur la case de la MORT et doit repartir � 0 !");
			gf.play(-58);
			gf.repaint();
		}else {
			JOptionPane.showMessageDialog(null,"Case de la MORT!"+"\n"+gf.getPlayers().get(1).getNickname()+" a march� sur la case de la MORT et doit repartir � 0 !");
			gf.play(-58);
			gf.repaint();
		}		
	}
}
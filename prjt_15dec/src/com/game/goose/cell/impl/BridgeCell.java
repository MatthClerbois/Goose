package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class BridgeCell extends BaseCell{
	/**
	 * Constant serialized ID used for compatibility.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="bridge.png";
	
	/**
	 * BridgeCell constructor give url to setImage
	 * @param gf
	 */
	public BridgeCell(GameFrame gf){
		super(gf);
		this.setImage(URL);
	}
	/**
	 * action to do when a player is on the case number 6 , he will be send to the case number 12 
	 */
	public void performSpecificAction(){
			if(gf.players.get(0).getPos() == 6){
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" Trouve un pont et d�cide de le traverser !"+"\n Par chance celui-ci l'am�ne � la case 12 ! ");
				gf.play(6);
				gf.repaint();
			}else {
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+"Trouve un pont et d�cide de le traverser !"+"\n Par chance celui-ci l'am�ne � la case 12 ! ");
				gf.play(6);
				gf.repaint();
			}
	}
}
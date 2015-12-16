package com.game.goose.cell.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.MainFrame;
import com.game.goose.cell.BaseCell;

public class EndCell extends BaseCell{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="lake.jpg";
	public MainFrame mainFrame;
	/**
	 * EndCell constructor give url to setImage
	 * @param gf
	 */
	public EndCell(GameFrame gf){
		super(gf);
		this.setImage(URL);
	}
	
	/**
	 * action to do when a player is on the case number 63 , he will win the game and the game will close
	 */
	public void performSpecificAction(){
		if(gf.players.get(0).getPos() == 63){
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" Arrive le premier au Lac ! " +"\nF�licitation, vous gagnez la partie");	
			gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gf.dispose();
		}else {
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" Arrive le premier au Lac ! " +"\nF�licitation, vous gagnez la partie");
			gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gf.dispose();
		}
	}
	
}

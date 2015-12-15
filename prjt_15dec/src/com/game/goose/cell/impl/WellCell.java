package com.game.goose.cell.impl;

import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class WellCell extends BaseCell {
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="puit.jpg";
	/**
	 * WellCell constructor give url to setImage
	 * @param gf
	 */	
	public WellCell(GameFrame gf){
		super(gf);
		this.setImage(URL);

	}
	public void performSpecificAction(){
		/*if(gf.players.get(0).getPos() == 31 ){
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" est tombé dans le puit et doit attendre que "+gf.getPlayers().get(1).getNickname()+" Tombe sur sa case ou le dépasse !");
			do{
				gf.play(0);
				gf.switchPlayer();
				gf.switchPlayer();
			}while(gf.players.get(1).getPos() <= 31);
			if(gf.players.get(0).getPos() == gf.players.get(1).getPos()){
				gf.repaint();
				gf.play(-(gf.getNewPosition()- gf.getOldPosition()));   //verifier pq ça redessine qu'une
				gf.repaint();
			}
			else{
				gf.repaint();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" est tombé dans le puit et doit attendre que "+gf.getPlayers().get(0).getNickname()+" Tombe sur sa case ou le dépasse !");
			do{
				gf.play(0);
				gf.switchPlayer();
				gf.switchPlayer();
			}while(gf.players.get(0).getPos() <= 31);
			if(gf.players.get(0).getPos() == gf.players.get(1).getPos()){
				gf.repaint();
				gf.play(-(gf.getNewPosition()- gf.getOldPosition()));   //verifier pq ça redessine qu'une
				gf.repaint();
			}
			else{
				gf.repaint();
			}
		}
		
		*/
	}
	
}
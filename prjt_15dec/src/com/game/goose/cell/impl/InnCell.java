package com.game.goose.cell.impl;
import javax.swing.JOptionPane;

import com.game.goose.GameFrame;
import com.game.goose.cell.BaseCell;

public class InnCell extends BaseCell{
	/**
	 * define a global variable who contain an image
	 */
	private static final String URL="Inn2.jpg";
	static int innCountPlayer2, innCountPlayer1;
	/**
	 * InnCell constructor give url to setImage
	 * @param gf
	 */	
	public InnCell(GameFrame gf){
		super(gf);
		this.setImage(URL);

	}
	/**
	 * action to do when a player is on the case number 19, he will wait 2 turns
	 */
	public void performSpecificAction(){	
		
		if(gf.players.get(0).getPos() == 19){
			
			JOptionPane.showMessageDialog(null,"La bonne nourriture et boisson, vous rendent somnolant. Vous vous endormez !"+"\n Passé un tour ..");
			if(innCountPlayer2 != 2){
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(1).getNickname()+" C'est à vous de jouer !");
				gf.switchPlayer();
				gf.switchPlayer();
				gf.repaint();
				innCountPlayer2++;
			}	
		}
		else{
			JOptionPane.showMessageDialog(null,"La bonne nourriture et boisson, vous rendent somnolant. Vous vous endormez !"+"\n Passé un tour ..");
			if(innCountPlayer1 != 2){
				JOptionPane.showMessageDialog(null,gf.getPlayers().get(0).getNickname()+" C'est à vous de jouer !");
				gf.switchPlayer();
				gf.switchPlayer();
				gf.repaint();
				innCountPlayer1++;
			}
		}
	}
}
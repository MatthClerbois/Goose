package com.game.goose.cell;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.game.goose.GameFrame;
import com.game.goose.Player;

public abstract class BaseCell extends JPanel implements Cell  {
	private static final String IMAGE_FOLDER="./images/";
	private static int numberPlayers;	//nombre de joueur(s)
	private String name;
	private int cellNumber;
	private ImageIcon cellBackground;
	private JLabelNumber contenu;
	protected GameFrame gf;
	private Color color;


	public BaseCell(GameFrame gf){
		this.gf=gf;
		ImageIcon icon = new ImageIcon(IMAGE_FOLDER+"bridge.png");
		Image resizedImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		this.cellBackground = new ImageIcon(resizedImage);
		contenu=new JLabelNumber(cellBackground);	
		add(contenu);
	}


	public void setName(String name) {
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	/**
	 * @return the number
	 */
	public int getCellNumber() {
		return cellNumber;
	}
	/**
	 * @param number the number to set
	 */
	public void setCellNumber(int number) {
		this.cellNumber = number;
	}
	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return cellBackground;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(ImageIcon image) {
		this.cellBackground = image;
	}

	public void setImage(String imgPath) {
		ImageIcon icon = new ImageIcon(IMAGE_FOLDER+imgPath);
		Image resizedImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		this.cellBackground = new ImageIcon(resizedImage);
	}

	public void refreshCellContent(){
		contenu.setImage(cellBackground);
		contenu.setNumber(cellNumber);
	}

	@Override
	public void doAction(Player player) {
		contenu.changeBorderColor(player.getColor());
		performSpecificAction();
	}

	public abstract void performSpecificAction();



	public void leaveCell() {
		contenu.changeBorderColor();
		contenu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
	}
}


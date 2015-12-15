package com.game.goose.cell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelNumber extends JLabel { // définit le numéro de chaque case
	private int number; // numéro de la case
	private ImageIcon image;
	private Color defaultColor = Color.BLACK;

	public JLabelNumber(ImageIcon image) {
		this.image = image;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(50, 50));
		this.setForeground(Color.MAGENTA);
		this.setText(String.valueOf(number));
		this.setBorder(BorderFactory.createLineBorder(defaultColor, 1));
	}

	public int getJLabelNumber() {
		return number;
	}

	public void setJLabelNumber(int jLabelNumber) {
		number = jLabelNumber;
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image.getImage(), 0, 0, null);
		super.paintComponent(g);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		this.setText(String.valueOf(number));
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	public void changeBorderColor() {
		changeBorderColor(defaultColor);
	}
	public void changeBorderColor(Color color){
		this.setBorder(BorderFactory.createLineBorder(color, 3));
	}
}

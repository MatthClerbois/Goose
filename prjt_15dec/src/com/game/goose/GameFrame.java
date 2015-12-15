package com.game.goose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.goose.cell.BaseCell;
import com.game.goose.cell.impl.MazeCell;
import com.game.goose.util.SpinnerBuilder;
import com.game.goose.util.SpiralLayout;
import com.game.goose.cell.impl.*;
/**
 * extends JFrame
 * @author max
 *
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Plateau plateau;
	public List<Player> players = new ArrayList<>();
	private JLabel gameArea;
	private SpiralLayout layout;
	private JPanel buttons;
	private int currentPlayerIndex = 0;
	JButton throwDice;
	static int turn;
	private int de1,de2;
	private int oldPosition,newPosition;
	public int getDe1() {
		return de1;
	}
	public void setDe1(int de1) {
		this.de1 = de1;
	}

	public int getDe2() {
		return de2;
	}

	public void setDe2(int de2) {
		this.de2 = de2;
	}
	/**
	 * GameFrame constructor 
	 */
	
	public GameFrame() {
		// layout = new SpiralLayout();
		// this.setVisible(true);
		plateau = new Plateau(this);
		layout = new SpiralLayout(4, Math.toRadians(15));
		// GridLayout layout=new GridLayout(10,10);
		// this.setLayout(layout);
		// this.setSize(200,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.drawControlPanel();
		this.drawGameArea();
	}

	/***
	 * draw control panel
	 */

	@SuppressWarnings("deprecation")
	public void drawControlPanel() {
		JPanel dice = new JPanel();
		buttons = new JPanel();
		GridBagLayout gbl_buttons = new GridBagLayout();
		gbl_buttons.columnWidths = new int[] { 124, 81, 60, 51, 0 };
		gbl_buttons.rowHeights = new int[] { 23, 0, 0, 0, 0, 0 };
		gbl_buttons.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_buttons.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	/**
	 * rules button
	 */
		JButton rules = new JButton("Regles");
		rules.setBackground(new Color(240, 240, 240));
		rules.setBounds(0, 0, 100, 40);
		GridBagConstraints gbc_rules = new GridBagConstraints();
		gbc_rules.insets = new Insets(0, 0, 5, 5);
		gbc_rules.gridx = 0;
		gbc_rules.gridy = 0;
		buttons.add(rules, gbc_rules);
	/**
	 * action when button pushed	
	 */
		rules.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ev){
				JOptionPane.showMessageDialog(null,"<html><span style='color:orange'>Commencer une partie de jeu de l’Oie :</span></html>\n"
						+ "\nChaque joueur joue chacun son tour en lançant les 2 dés. Suivant le nombre ou chiffre obtenue, le joueur avance son pion Case par Case.\n "
						+ "Il existe des règles du jeu de l’Oie à respecter selon le nombre que l’on fait ou de la Case sur laquelle on tombe. \n"
						+ "\n<html><span style='color:orange'>Voyons plus en détails ces règles :</span></html>\n "
						+ "\nAu commencement de la partie, si l’un des joueurs fait 9 par 6 et 3, il doit avancer son pion immédiatement au nombre 26. S’il fait 9 par 4 et 5, il ira au nombre 53."
						+ "\nSi lors de la partie, un joueur tombe sur un autre joueur, le joueur déjà présent sur la case. Les joueurs devront échanger de cases.\n "
						+ "Si un joueur tombe sur une case de l'oie, son jet de dé sera doublé.\n "
						+ "Si un joueur tombe sur la case 6 (pont) , il doit se rendre sur la Case 12.\n "
						+ "Le joueur qui tombe sur la Case 19 (correspondant à une auberge) devra passer 1 tour.\n "
						+ "Le joueur qui tombe sur la Case 31 correspondant au puits devra passer 2 tours ou si l'autre joueur tombe sur la casse également, le joueur au fond du puits retournera à la case précédente du joueur qui vient d'arriver.\n "
						+ "Celui qui tombe sur la Case 42 correspondant au labyrinthe retournera obligatoirement à la Case 30.\n "
						+ "Qui ira en 52 correspondant la prison attendra qu’un autre joueur vienne au même numéro pour repartir ou le dépasse.\n "
						+ "Le joueur qui va sur la Case 58 correspondant à la Case Tête de mort recommencera la partie depuis le début.\n"
						+ "\n<html><span style='color:orange'>Comment gagner une partie de jeu de l’Oie :</span></html>\n"
						+ "\nPour gagner une partie de jeu de l’Oie, il faut être le premier à arriver sur la dernière Case 63 mais avec l’obligation d’arriver pile sur cette Case.\n "
						+ "Au cas où le joueur fait un score au dés supérieur au nombre de Case le séparant de la victoire, il devra reculer d’autant de cases supplémentaires.","Règles du jeu de l'oie",1);
			}
		});
	/**
	 * throwDice button 
	 *
	 */
		throwDice = new JButton("Lancer De");
		GridBagConstraints gbc_throwDice = new GridBagConstraints();
		gbc_throwDice.anchor = GridBagConstraints.NORTHWEST;
		gbc_throwDice.insets = new Insets(0, 0, 5, 5);
		gbc_throwDice.gridx = 1;
		gbc_throwDice.gridy = 0;
		buttons.add(throwDice, gbc_throwDice);
		throwDice.setEnabled(false);
	/**
	 * action when throwDice button pushed	
	 */
		throwDice.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ev){
				de1 =(int)(Math.random()*6)+1;
				de2 =(int)(Math.random()*6)+1;
				turn++;
				if((((de1==3)||(de2==3))&&((de1==6)||(de2==6)) && turn <= 2)){
					JOptionPane.showMessageDialog(null,"<html><span style='color:green'>Vous avancez jusqu'à la Case 25!</span></html>\n 1er De : "+de1+"\n 2e  De  : "+de2,null,1);
					//pos
					play(25);
				}
				else if((((de1==4)||(de2==4))&&((de1==5)||(de2==5)) && turn <=2)){
					JOptionPane.showMessageDialog(null,"<html><span style='color:green'>Vous avancez jusqu'à la Case 49!</span></html>\n 1er De : "+de1+"\n 2e  De  : "+de2,null,1);
					//pos
					play(49);
				}
				else{
					JOptionPane.showMessageDialog(null,"Vous avancez de "+(de1+de2)+" Cases!\n 1er De : "+de1+"\n 2e  De  : "+de2,null,1);
					play(de1 + de2);
				}
				switchPlayer();
				repaint();
			}
		}); 
	/**
	 * button to change the value of radius step and angle step for the spiral
	 */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 2;
		gbc.gridy = 0;
		JLabel label_1 = new JLabel("Radius step:");
		buttons.add(label_1, gbc);
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.insets = new Insets(0, 0, 5, 0);
		gbc_1.anchor = GridBagConstraints.WEST;
		gbc_1.gridx = 3;
		gbc_1.gridy = 0;
		JLabel label = new JLabel("Angle step");
		buttons.add(label, gbc_1);
		buttons.add(SpinnerBuilder.createNewRadiusSpinner(this, layout));
		buttons.add(SpinnerBuilder.createNewAngleSpinner(this, layout));
	}
	/**
	 * used to move players 
	 * @param nbCase
	 */
	public void play(int nbCase) {
		Player currentPlayer = players.get(currentPlayerIndex);
		oldPosition = currentPlayer.getPos();
		if(currentPlayer.getPos() + nbCase > 63){
			newPosition = (63 - ((currentPlayer.getPos() + nbCase)-63));  //Calcul pour tomber pile sur la case 63
		}
		else{
			newPosition = currentPlayer.getPos() + nbCase;
		}
		currentPlayer.setPos(newPosition); // deplacement du joueur

		BaseCell oldCell = plateau.getCell(oldPosition);
		oldCell.leaveCell();
		BaseCell currentCell = plateau.getCell(newPosition);
		currentCell.doAction(currentPlayer);
	}

	public int getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(int oldPosition) {
		this.oldPosition = oldPosition;
	}

	public int getNewPosition() {
		return newPosition;
	}

	public void setNewPosition(int newPosition) {
		this.newPosition = newPosition;
	}

	/***
	 * draw game area
	 */
	public void drawGameArea() {
		gameArea = new JLabel();
		gameArea.setLayout(layout);
		drawCells();
	}

	/**
	 * drawCells create cells ,return nothing
	 */
	private void drawCells() {
		List<BaseCell> cells = plateau.getCells();
		for (BaseCell cell : cells) {
			cell.refreshCellContent();
			gameArea.add(cell);
		}
	}
	/**
	 * used to put a name on each player and put them in the ArrayList 
	 */
	protected void initPlayers() {
		String playerName1 = JOptionPane.showInputDialog("Nom du joueur 1 :");
		String playerName2 = JOptionPane.showInputDialog("Nom du joueur 2 :");
		Player player1 = new Player(playerName1);
		player1.setColor(Color.BLUE);
		player1.setPos(0);
		Player player2 = new Player(playerName2);
		player2.setColor(Color.red);
		player2.setPos(0);
	/**
	 * add the each player in the ArrayList
	 */
		players.add(player1);
		players.add(player2);
		
		int nb1 =(int)(Math.random()*10)+1;	
		int nb2 =(int)(Math.random()*10)+1; 
	/**
	 * used to determine which player is strating the game	
	 */
		if(nb1>nb2){	
			player1.setTurn(1);	
			player2.setTurn(2);	
			player1.setCanPlay(true);
			player2.setCanPlay(false);
			currentPlayerIndex = 0;
		}	
		else{	
			player1.setTurn(2);	
			player2.setTurn(1);	
			player1.setCanPlay(false);
			player2.setCanPlay(true);
			currentPlayerIndex = 1;
		} 
		/**
		 * label on the top right of the JFrame where the name and the color of each player is set 
		 */
		JLabel lblPlayer_1 = new JLabel("Player1 : " + players.get(0).getNickname());
		GridBagConstraints gbc_lblPlayer_1 = new GridBagConstraints();
		gbc_lblPlayer_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_1.gridx = 0;
		gbc_lblPlayer_1.gridy = 3;
		lblPlayer_1.setOpaque(true);
		lblPlayer_1.setBackground(Color.BLUE);
		lblPlayer_1.setBorder(BorderFactory.createLineBorder(Color.black));
		buttons.add(lblPlayer_1, gbc_lblPlayer_1);

		JLabel lblPlayer_2 = new JLabel("Player2 : " + players.get(1).getNickname());
		GridBagConstraints gbc_lblPlayer_2 = new GridBagConstraints();
		gbc_lblPlayer_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayer_2.gridx = 0;
		gbc_lblPlayer_2.gridy = 4;
		lblPlayer_2.setOpaque(true);
		lblPlayer_2.setBackground(Color.red);
		lblPlayer_2.setBorder(BorderFactory.createLineBorder(Color.black));
		buttons.add(lblPlayer_2, gbc_lblPlayer_2);
	/**
	 * pop up to tell the players who's starting	
	 */
		for(int i=0; i<2; i++){
			if(players.get(i).getTurn() == 1){
				JOptionPane.showMessageDialog(null, "Le joueur "+players.get(i).getNickname()+" commence la partie","Joueur qui commence",1);
				players.get(i).setCanPlay(true);
			}
			else{
				players.get(i).setCanPlay(false);
			}
		}
		for(int i=0; i<2; i++){
			if(players.get(i).getCanPlay() == true){
				throwDice.setEnabled(true);
			}
		}
		
	}	
	/**
	 * used to switch players
	 */
	public void switchPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
/**
 * contain the look parameters for the GameFrame
 */
	protected void display() {
		getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(buttons, BorderLayout.NORTH);
		this.getContentPane().add(gameArea, BorderLayout.CENTER);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setTitle("Goose Game");
		this.setVisible(true);
	}

	public void refreshPanel() {
		gameArea.revalidate();
	}
}
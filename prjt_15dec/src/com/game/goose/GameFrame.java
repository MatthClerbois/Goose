package com.game.goose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.game.goose.cell.BaseCell;
import com.game.goose.util.SpinnerBuilder;
import com.game.goose.util.SpiralLayout;
/**
 * extends JFrame
 * @author max
 *
 */
public class GameFrame extends JFrame {

	/**
	 * Constant serialized ID used for compatibility.
	 */
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	/**
	 * GameFrame constructor 
	 */
	public GameFrame() {
		plateau = new Plateau(this);
		layout = new SpiralLayout(4, Math.toRadians(15));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initPlayers();
		this.startingPlayers();
		this.drawControlPanel();
		this.drawGameArea();
		this.throwDice();
		this.rulesButton();
		this.spiralButtons();
		this.display();
	}

	/***
	 * draw control panel
	 */
	public void drawControlPanel() {
		buttons = new JPanel();
		GridBagLayout gbl_buttons = new GridBagLayout();
		gbl_buttons.columnWidths = new int[] { 124, 81, 60, 51, 0 };
		gbl_buttons.rowHeights = new int[] { 23, 0, 0, 0, 0, 0 };
		gbl_buttons.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_buttons.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	}

	/**
	 * button to change the value of radius step and angle step for the spiral
	 */
	public void spiralButtons(){

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
	 * rules button
	 */
	public void rulesButton(){
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
				JOptionPane.showMessageDialog(null,"<html><span style='color:orange'>Commencer une partie de jeu de l�Oie :</span></html>\n"
						+ "\nChaque joueur joue chacun son tour en lan�ant les 2 d�s. Suivant le nombre ou chiffre obtenue, le joueur avance son pion Case par Case.\n "
						+ "Il existe des r�gles du jeu de l�Oie � respecter selon le nombre que l�on fait ou de la Case sur laquelle on tombe. \n"
						+ "\n<html><span style='color:orange'>Voyons plus en d�tails ces r�gles :</span></html>\n "
						+ "\nAu commencement de la partie, si l�un des joueurs fait 9 par 6 et 3, il doit avancer son pion imm�diatement au nombre 26. S�il fait 9 par 4 et 5, il ira au nombre 53."
						+ "\nSi lors de la partie, un joueur tombe sur un autre joueur, le joueur d�j� pr�sent sur la case. Les joueurs devront �changer de cases.\n "
						+ "Si un joueur tombe sur une case de l'oie, son jet de d� sera doubl�.\n "
						+ "Si un joueur tombe sur la case 6 (pont) , il doit se rendre sur la Case 12.\n "
						+ "Le joueur qui tombe sur la Case 19 (correspondant � une auberge) devra passer 1 tour.\n "
						+ "Le joueur qui tombe sur la Case 31 correspondant au puits devra passer 2 tours ou si l'autre joueur tombe sur la casse �galement, le joueur au fond du puits retournera � la case pr�c�dente du joueur qui vient d'arriver.\n "
						+ "Celui qui tombe sur la Case 42 correspondant au labyrinthe retournera obligatoirement � la Case 30.\n "
						+ "Qui ira en 52 correspondant la prison attendra qu�un autre joueur vienne au m�me num�ro pour repartir ou le d�passe.\n "
						+ "Le joueur qui va sur la Case 58 correspondant � la Case T�te de mort recommencera la partie depuis le d�but.\n"
						+ "\n<html><span style='color:orange'>Comment gagner une partie de jeu de l�Oie :</span></html>\n"
						+ "\nPour gagner une partie de jeu de l�Oie, il faut �tre le premier � arriver sur la derni�re Case 63 mais avec l�obligation d�arriver pile sur cette Case.\n "
						+ "Au cas o� le joueur fait un score au d�s sup�rieur au nombre de Case le s�parant de la victoire, il devra reculer d�autant de cases suppl�mentaires.","R�gles du jeu de l'oie",1);
			}
			//http://www.regles-de-jeux.com/regle-du-jeu-de-l-oie/
			//http://www.recoveredscience.com/gooserules.htm
		});		
	}

	/**
	 * throwDice button 
	 *
	 */
	public void throwDice(){
		throwDice = new JButton("Lancer De");
		GridBagConstraints gbc_throwDice = new GridBagConstraints();
		gbc_throwDice.anchor = GridBagConstraints.NORTHWEST;
		gbc_throwDice.insets = new Insets(0, 0, 5, 5);
		gbc_throwDice.gridx = 1;
		gbc_throwDice.gridy = 0;
		/**
		 * action when throwDice button pushed	
		 */
		throwDice.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ev){
				de1 =(int)(Math.random()*6)+1;
				de2 =(int)(Math.random()*6)+1;
				turn++;
				if((((de1==3)||(de2==3))&&((de1==6)||(de2==6)) && turn <= 2)){
					JOptionPane.showMessageDialog(null,"<html><span style='color:green'>Vous avancez jusqu'� la Case 25!</span></html>\n 1er De : "+de1+"\n 2e  De  : "+de2,null,1);
					//pos
					play(25);
				}
				else if((((de1==4)||(de2==4))&&((de1==5)||(de2==5)) && turn <=2)){
					JOptionPane.showMessageDialog(null,"<html><span style='color:green'>Vous avancez jusqu'� la Case 49!</span></html>\n 1er De : "+de1+"\n 2e  De  : "+de2,null,1);
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
		buttons.add(throwDice,gbc_throwDice);
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
		Player player1 = new Player(playerName1);
		player1.setColor(Color.BLUE);
		player1.setPos(0);
		players.add(player1);

		String playerName2 = JOptionPane.showInputDialog("Nom du joueur 2 :");
		Player player2 = new Player(playerName2);
		player2.setColor(Color.red);
		player2.setPos(0);		
		players.add(player2);
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
	}	

	/**
	 * used to determine which player is starting the game	
	 */
	public void startingPlayers(){

		int nb1 =(int)(Math.random()*10)+1;	
		int nb2 =(int)(Math.random()*10)+1; 

		if(nb1>nb2){	
			players.get(0).setTurn(1);	
			players.get(1).setTurn(2);	
			players.get(0).setCanPlay(true);
			players.get(1).setCanPlay(false);
			currentPlayerIndex = 0;
		}	
		else{	
			players.get(0).setTurn(2);	
			players.get(1).setTurn(1);	
			players.get(0).setCanPlay(false);
			players.get(1).setCanPlay(true);
			currentPlayerIndex = 1;
		} 	

		/**
		 * pop up to tell which player start
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
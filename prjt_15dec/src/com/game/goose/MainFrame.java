package com.game.goose;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
/**
 * Main Frame extends JFrame
 * @author max
 *
 */
public class MainFrame extends JFrame {
	/**
	 * Constant serialized ID used for compatibility.
	 */
	private static final long serialVersionUID = 1L;

/**
 * MainFrame constructor 
 * contain button switch to GameFrame
 */
	public MainFrame() {
		getContentPane().setBackground(Color.WHITE);
		final MainFrame mainFrame = this;
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
//		panel.setBackground(Color.WHITE);
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[]{65, 0, 183, 0, 0, 0};
//		gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		panel.setLayout(gbl_panel);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton startButton = new JButton("Commencer une nouvelle partie");
//		GridBagConstraints gbc_startButton = new GridBagConstraints();
//		gbc_startButton.insets = new Insets(0, 0, 5, 5);
//		gbc_startButton.anchor = GridBagConstraints.NORTHWEST;
//		gbc_startButton.gridx = 2;
//		gbc_startButton.gridy = 5;
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ev){
				 @SuppressWarnings("unused")
				GameFrame gameFrame = new GameFrame();
				 mainFrame.dispose();
			}
		});
		panel.add(startButton /*,gbc_startButton*/);
		
		/**
		 * MainFrame look parameters 
		 */
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setTitle("Goose Game Menu");
		this.setSize(320,440);
		this.setResizable(false);		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}


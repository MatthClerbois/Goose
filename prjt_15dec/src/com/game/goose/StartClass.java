package com.game.goose;

import java.awt.EventQueue;

public class StartClass {

	/**
	 * calling MainFrame constructor
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			/**
			 * create a new object MainFrame called frame
			 */
				@SuppressWarnings("unused")
				MainFrame frame = new MainFrame();
			}
		});
	}
}

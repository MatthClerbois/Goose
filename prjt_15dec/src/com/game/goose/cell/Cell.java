package com.game.goose.cell;

import com.game.goose.Player;

/**
 * interface cellule
 * @author Max
 *
 */
public interface Cell {
	/**
	 * execute action
	 * @param currentPlayer the player who is playing
	 */
	public void doAction(Player currentPlayer);
	}

package com.game.goose;

import java.util.ArrayList;
import java.util.List;

import com.game.goose.cell.BaseCell;
import com.game.goose.cell.impl.BasicCell;
import com.game.goose.cell.impl.BridgeCell;
import com.game.goose.cell.impl.DeathCell;
import com.game.goose.cell.impl.EndCell;
import com.game.goose.cell.impl.GooseCell;
import com.game.goose.cell.impl.InnCell;
import com.game.goose.cell.impl.JailCell;
import com.game.goose.cell.impl.MazeCell;
import com.game.goose.cell.impl.ThrowDiceCell;
import com.game.goose.cell.impl.WellCell;

public class Plateau {
	private GameFrame gf ;
	/**
	 * ArrayList
	 */
	List<BaseCell> cells = new ArrayList<>();
	/**
	 * plateau constructor receive GameFrame
	 * @param gf
	 */

	public Plateau(GameFrame gf ){
		this.gf = gf;
		for (int i = 0; i <= 63; i++) {							//résolution 4 : 229.18
			BaseCell cell=null;
			
			switch(i){
				case 5: case 9: case 14: case 18: case 23: case 36: case 41: case 45: case 50: case 54: case 59:
					cell = new GooseCell(gf);
					break;
				case 6:
					cell = new BridgeCell(gf);
					break;
				case 19:
					cell = new InnCell(gf);
					break;
				case 26: case 53:
					cell = new ThrowDiceCell(gf);
					break;
				case 31:
					cell = new WellCell(gf);
					break;
				case 42:
					cell = new MazeCell(gf);
					break;
				case 52:
					cell = new JailCell(gf);
					break;
				case 58:
					cell = new DeathCell(gf);
					break;
				case 63:
					cell = new EndCell(gf);
					break;
				default:
					cell = new BasicCell(gf);
			}	
			//cells.add(cell);
//			cellNumber.setText(String.valueOf(i)); 		//affiche numéro de cellule
			cell.setCellNumber(i);					//attribuer numéro de cellule			
//			cell.isOnCell(gf.getPlayers());			//voir tp9 pg 35 !
			cells.add(cell);	
		}
	}
	
	public List<BaseCell> getCells(){
		return cells;
	}
	
	public BaseCell getCell(int position){
		return cells.get(position);
	}
}

// oie tt les 15
//19,38 waitcell
//58 deathCell
//31 puits
//42 goTo 30
//52 jailCell
//if player on other player Cell go to previousCell from other player
//if dice=6 go to 12
//if 9 by 6+3 go to 26
//if 9 by 4+5 go to 53
//http://www.regles-de-jeux.com/regle-du-jeu-de-l-oie/
//http://www.recoveredscience.com/gooserules.htm



package stones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


import stones.Field;
import stones.GameState;


public class ConsoleUI implements Serializable {

	GameState gameState;
	Field field;
	int emptyRow = 0;
	int emptyColumn = 0;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	

	public ConsoleUI(Field field) {
		gameState = GameState.PLAYING;
		this.field = field;
		
	}

	/**
	 * Communication with player.
	 * 
	 * @throws WrongInputException
	 */
	

	

	public void newGame() {
		new ConsoleUI(new Field(field.getRowCount(), field.getRowCount()));
	}

	public void exit() {
		System.exit(0);
	}
	
	

	public void moveUp() {
		isEmpty();
		if (emptyRow + 1 <= field.getRowCount() - 1) {
			field.setTile(emptyRow, emptyColumn, field.getTile(emptyRow + 1, emptyColumn).getValue());
			field.setTile(emptyRow + 1, emptyColumn, 99);

		}
	}

	public void moveDown() {
		isEmpty();
		if (emptyRow - 1 >= 0) {
			field.setTile(emptyRow, emptyColumn, field.getTile(emptyRow - 1, emptyColumn).getValue());
			field.setTile(emptyRow - 1, emptyColumn, 99);

		}
	}

	public void moveRight() {
		isEmpty();
		if (emptyColumn - 1 >= 0) {
			field.setTile(emptyRow, emptyColumn, field.getTile(emptyRow, emptyColumn - 1).getValue());
			field.setTile(emptyRow, emptyColumn - 1, 99);

		}
	}

	public void moveLeft() {
		isEmpty();
		if (emptyColumn + 1 <= field.getRowCount() - 1) {
			field.setTile(emptyRow, emptyColumn, field.getTile(emptyRow, emptyColumn + 1).getValue());
			field.setTile(emptyRow, emptyColumn + 1, 99);

		}
	}

	
	public boolean isSucessfull() {
		boolean bool = true;
		int actValue = 0;
		for (int r = 0; r < field.getRowCount(); r++) {
			for (int c = 0; c < field.getColumnCount(); c++) {
				if (actValue > field.getTile(r, c).getValue()) {
					bool = false;
				}
				actValue = field.getTile(r, c).getValue();
			}
		}
		return bool;
	}

	public void isEmpty() {
		for (int r = 0; r < field.getRowCount(); r++) {
			for (int c = 0; c < field.getColumnCount(); c++) {
				if (field.getTile(r, c).getValue() == 99) {
					emptyRow = field.getTile(r, c).getPositionX();
					emptyColumn = field.getTile(r, c).getPositionY();
					System.out.printf("Pozicia: " + emptyRow + emptyColumn + "\n");
				}
			}
		}
	}

}

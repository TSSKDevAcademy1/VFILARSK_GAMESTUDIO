package stones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Field implements Serializable {
	
	private int emptyColumn;
	private int emptyRow;
	private int rowCount = 0;
	private int columnCount = 0;
	private final Tile[][] field;
	private int length = 0;
	private int actualLength = 0;
	private int actualRowCount = 0;
	private int actualColumnCount = 0;

	public Field() {
		this.rowCount = 2;
		this.columnCount = 2;
		this.length = rowCount * columnCount + 1;
		this.field = new Tile[rowCount][columnCount];
		generate();

	}

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.length = rowCount * columnCount + 1;
		this.field = new Tile[rowCount][columnCount];
		generate();
	}

	private boolean scaning(int s, int number) {
		int g = 0;
		for (int x = 0; x < actualRowCount; x++) {
			for (int y = 0; y < actualColumnCount; y++) {
				if (g < number) {
					if (field[x][y].getValue() == s) {
						return false;
					}
					g++;
				}
			}
		}
		return true;

	}

	/*
	public boolean isClickable(int row, int column) {
		
		if (row + 1 < rowCount) {
			if (field[row + 1][column].getValue() == 99) {
				return true;
			}
		} else if (row - 1 >= 0) {
			if (field[row - 1][column].getValue() == 99) {
				return true;
			}
		} else if (column - 1 >= 0) {
			if (field[row][column -1].getValue() == 99) {
				return true;
			}
		} else if (column + 1 < columnCount) {
			if (field[row][column+1].getValue() == 99) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	*/
	public void move(int row, int column){
		isEmpty();
		int storeValue;
		storeValue = field[row][column].getValue();
		field[row][column].setValue(field[emptyRow][emptyColumn].getValue());
		field[emptyRow][emptyColumn].setValue(storeValue);
	}
	
	
	public boolean isClickable(int row, int column) {
		
		if (row + 1 < rowCount && field[row + 1][column].getValue() == 99) {
				return true;
			
		} else if (row - 1 >= 0 && field[row - 1][column].getValue() == 99) {
				return true;
			
		} else if (column - 1 >= 0 && field[row][column -1].getValue() == 99) {
				return true;
			
		} else if (column + 1 < columnCount && field[row][column+1].getValue() == 99) {
				return true;
			
		} else {
			return false;
		}
		
	}
	
	public Tile[][] generate() {
		List<Integer> listOfIntegers = new ArrayList<>();
		int totalCount = rowCount * columnCount;
		for (int j = 1; j < totalCount; j++) {
			listOfIntegers.add(j);
		}
		listOfIntegers.add(99);
		Collections.shuffle(listOfIntegers);
		//int randomValueNumber = 0;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				field[r][c] = new Clue(r, c, listOfIntegers.get(listOfIntegers.size() - 1));
				listOfIntegers.remove(listOfIntegers.size() - 1);
				actualColumnCount++;
			}
		}
		return field;
	}

	public void vypis() {
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (field[r][c].getValue() == 99) {
					System.out.printf("   " + "Q");
				} else {
					System.out.printf("   " + field[r][c].getValue());
				}
			}
			System.out.printf("\n");
		}
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public Tile getTile(int row, int column) {
		return field[row][column];
	}

	public void setTile(int row, int column, int value) {
		field[row][column] = new Clue(row, column, value);
	}
	
	public String toString() {
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if((isClickable(r, c))){
					field[r][c].setClickable(true);
				} else {
					field[r][c].setClickable(false);
				}
			}
		}
		
		
		String string ="";
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				string = string+field[r][c].toString()+" ";
			}
			string = string+"<br>";
		}
		return string;
	}
	
	public void isEmpty() {
		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				if (getTile(r, c).getValue() == 99) {
					emptyRow = getTile(r, c).getPositionX();
					emptyColumn = getTile(r, c).getPositionY();
				}
			}
		}
	}

}

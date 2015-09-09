package minesweeper.core;
import java.util.*;

import javax.enterprise.context.SessionScoped;

import minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */

public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;
    private int unmarkedMineCount;
    /**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.unmarkedMineCount = mineCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];

        //generate the field content
        generate();
        
        System.out.println("Vytvoril som field");
    }

    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
    	if(row <=rowCount && column <= columnCount ){
    	
		        Tile tile = tiles[row][column];
		        if (tile.getState() == Tile.State.CLOSED) {
		            tile.setState(Tile.State.OPEN);
		            
		            if (tile instanceof Mine) {
		                state = GameState.FAILED;
		                return;
		            }
		
		            if (isSolved()) {
		                state = GameState.SOLVED;
		                return;
		            }
		            if(((Clue)(tiles)[row][column]).getValue() == 0){
	            		openAdjacentTiles(row,column);
	            	}
		        }
    	}
    }

    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {
    	Tile tile = tiles[row][column];
        if(tile.getState() == State.CLOSED){
        	tile.setState(State.MARKED);
        	if(tiles[row][column] instanceof Mine){
        		unmarkedMineCount--;
        	}
        } else if(tile.getState() == State.MARKED){
        	tile.setState(State.CLOSED);
        } else {
        	return;
        }
    }

    /**
     * Generates playing field.
     */
    private void generate() {
        int count = 0;
        int randomRow =0;
        int randomColumn =0;
        Random random = new Random();
    	while(count < mineCount){
    		randomRow = random.nextInt(rowCount-1);
    		randomColumn = random.nextInt(columnCount-1);
    		if(!(tiles[randomRow][randomColumn] instanceof Mine)){
    			tiles[randomRow][randomColumn] = new Mine(randomColumn,randomRow);
    			count++;
    		} 
    	}
    	for(int r=0;r<rowCount;r++){
    		for(int c=0;c<columnCount;c++){
    			if(tiles[r][c] == null){
    				tiles[r][c] = new Clue(c,r,this.countAdjacentMines(r,c));
    			}
    		}
    	}
    }
    
    private int getNumberOf(Tile.State state) {
		int openTile = 0;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (tiles[r][c].getState() == state) {
					openTile++;
				}
			}
		}
		return openTile;
	}

    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
    	if (mineCount == (rowCount*columnCount - getNumberOf(State.OPEN)))
			return true;
		else return false;
    }

    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
    
    public int getRowCount(){
    	return this.rowCount;
    }
    
    public int getColumnCount(){
    	return this.columnCount;
    }
    
    public int getMineCount(){
    	return this.mineCount;
    }
    
    public Tile getTile(int row, int column){
    	return tiles[row][column];
    }
    
    public String toString(){
    	String pole = "";
    	for(int r =0;r<rowCount;r++){
    		for(int c=0;c<columnCount;c++){
    				pole = pole + tiles[r][c].toString() +" ";
    		}
    		pole = pole + "<br>";
    	}
    	return pole;
    }
    
    public void setState(String state){
    	for(int r = 0;r < rowCount;r++){
    		for(int c = 0;c < columnCount;c++){
    			switch(state) {
    			case "OPEN" :tiles[r][c].setState(State.OPEN);break;
    			case "CLOSED" :tiles[r][c].setState(State.CLOSED);break;
    			case "MARKED" :tiles[r][c].setState(State.MARKED);break;
    			default:tiles[r][c].setState(State.MARKED);break;
    			}
    		}
    	}
    }
    
    public GameState getGameState(){
    	return state;
    }
    
    public void setGameState(GameState state){
    	this.state = state;
    }
    
    public void openAdjacentTiles(int row, int column){
    	int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                    	openTile(actRow,actColumn);
                        
                    }
                }
            }
        }
    }
    
    public int getRemainigMineCount(){
    	return mineCount - getNumberOf(State.MARKED);
    }
    
}

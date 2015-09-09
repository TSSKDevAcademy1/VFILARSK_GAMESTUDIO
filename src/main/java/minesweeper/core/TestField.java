package minesweeper.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestField {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test                
    public void isSolved() {
        Field field = new Field(6, 6, 3);
        
        assertEquals(GameState.PLAYING, field.getGameState());
        
        int open = 0;
        for(int row = 0; row < field.getRowCount(); row++) {
            for(int column = 0; column < field.getColumnCount(); column++) {
                if(field.getTile(row, column) instanceof Clue) {
                    field.openTile(row, column);
                    open++;
                }
                if(field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
                    assertEquals(GameState.SOLVED, field.getGameState());
                } else {
                    assertNotSame(GameState.FAILED, field.getGameState());
                }
            }
        }
        
        assertEquals(GameState.SOLVED, field.getGameState());
    } 
}

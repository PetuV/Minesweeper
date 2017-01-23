/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Petteri
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void boardStartsWithCorrectAmountOfTiles() {
        Board testBoard = new Board(8, 8, new Random(), 10);
        
        Tile[][] tiles = testBoard.getTiles();
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                count++;
            }
        }
        
        assertEquals(64, count);
    }
    
    @Test
    public void boardHasMines() {
        Board testBoard = new Board(8, 8, new Random(), 10);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean isMine = false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine()) isMine = true;
            }
        }
        assertEquals(true, isMine);
    }
    
    @Test
    public void boardHasCorrectAmountOfMines() {
        Board testBoard = new Board(8, 8, new Random(), 10);
        
        Tile[][] tiles = testBoard.getTiles();
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine()) count++;
            }
        }
        assertEquals(10, count);
    }
    
    @Test
    public void neighboringMinesCountedCorrectly() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        
        Tile[][] tiles = testBoard.getTiles();
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (!tiles[i][j].getIsMine()) count += tiles[i][j].getMines();
            }
        }
        assertEquals(63, count);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.awt.Color;
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
    
    @Test
    public void minesSetToNegativeOneOnMinedTiles() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean minesSetToNegativeOne = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine() && tiles[i][j].getMines() != -1) {
                    minesSetToNegativeOne = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, minesSetToNegativeOne);
    }
    
    @Test
    public void revealTileRevealsOneTile() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        Tile tile = testBoard.getTileAt(1, 1);
        
        testBoard.revealTile(tile);
        assertEquals(false, tile.getIsHidden());
    }
    
    @Test
    public void revealTileOnMineRevelsAllMines() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        Tile tile = testBoard.getTileAt(1, 1);
        tile.setIsMine(true);
        
        testBoard.revealTile(tile);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean allMinesRevealed = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine() && tiles[i][j].getIsHidden() == true) {
                    allMinesRevealed = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, allMinesRevealed);
    }
    
    @Test
    public void revealTileRevelasAllTilesOnEmptyBoard() {
        Board testBoard = new Board(8, 8, new Random(555), 0);
        Tile tile = testBoard.getTileAt(1, 1);
        
        testBoard.revealTile(tile);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean allTilesRevealed = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsHidden() == true) {
                    allTilesRevealed = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, allTilesRevealed);
    }
    
    @Test
    public void revealAllTilesRevealsAllTiles() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        
        testBoard.revealAllTiles(Color.RED);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean allTilesRevealed = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsHidden() == true) {
                    allTilesRevealed = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, allTilesRevealed);
    }
    
    @Test
    public void reavealAllTilesChangesMineColor() {
        Board testBoard = new Board(8, 8, new Random(555), 0);
        testBoard.getTileAt(1, 1).setIsMine(true);
        
        testBoard.revealAllTiles(Color.RED);
        
        assertEquals(Color.RED, testBoard.getTileAt(1, 1).getBackground());
    }
    
    @Test
    public void loseChangesMineColor() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        testBoard.revealAllTiles(Color.RED);
        
        Tile[][] tiles = testBoard.getTiles();
        boolean allMinesRed = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine() && tiles[i][j].getBackground() != Color.RED) {
                    allMinesRed = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, allMinesRed);
    }
    
    @Test
    public void toggleFlagReducesFlagsLeft() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        testBoard.getTileAt(1, 1).setIsFlagged(false);
        testBoard.toggleFlag(testBoard.getTileAt(1, 1));
        
        assertEquals(9, testBoard.getFlagsLeft());
    }
    
    @Test
    public void toggleFlagIncreasesFlagsLeft() {
        Board testBoard = new Board(8, 8, new Random(555), 10);
        testBoard.getTileAt(1, 1).setIsFlagged(true);
        testBoard.toggleFlag(testBoard.getTileAt(1, 1));
        
        assertEquals(11, testBoard.getFlagsLeft());
    }
    
    @Test
    public void checkWinChangesMineColor() {
        Board testBoard = new Board(8, 8, new Random(555), 0);
        testBoard.getTileAt(1, 1).setIsMine(true);
        testBoard.getTileAt(1, 1).setIsFlagged(true);
        testBoard.checkWin();
        
        Tile[][] tiles = testBoard.getTiles();
        boolean allMinesGreen = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine() && tiles[i][j].getBackground() != Color.GREEN) {
                    allMinesGreen = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        assertEquals(true, allMinesGreen);
    }
}

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
public class GameTest {
    
    public GameTest() {
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
    public void toggleFlagsSetsIsFlaggedToTrue() {
        Game game = new Game(8, 8, new Random(), 10);
        
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        
        assertEquals(true, game.getBoard().getTileAt(1, 1).getIsFlagged());
    }
    
    @Test
    public void toggleFlagsSetsIsFlaggedToFalse() {
        Game game = new Game(8, 8, new Random(), 10);
        
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        
        assertEquals(false, game.getBoard().getTileAt(1, 1).getIsFlagged());
    }
    
    @Test
    public void toggleFlagsLowersFlagsLeft() {
        Game game = new Game(8, 8, new Random(), 10);
        
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        game.toggleFlag(game.getBoard().getTileAt(1, 2));
        game.toggleFlag(game.getBoard().getTileAt(1, 3));
        
        assertEquals(7, game.getFlagsLeft());
    }
    
    @Test
    public void toggleFlagsIncreasesFlagsLeft() {
        Game game = new Game(8, 8, new Random(), 10);
        
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        game.toggleFlag(game.getBoard().getTileAt(1, 2));
        game.toggleFlag(game.getBoard().getTileAt(1, 3));
        
        game.toggleFlag(game.getBoard().getTileAt(1, 1));
        game.toggleFlag(game.getBoard().getTileAt(1, 2));
        game.toggleFlag(game.getBoard().getTileAt(1, 3));
        
        assertEquals(10, game.getFlagsLeft());
    }
    
    @Test
    public void clickingRevealsNonMine() {
        Game game = new Game(8, 8, new Random(), 0);
        
        game.clickTile(game.getBoard().getTileAt(1, 1));
        
        assertEquals(false, game.getBoard().getTileAt(1, 1).getIsHidden());
    }
    
    @Test
    public void clickingMineSetsIsActiveToFalse() {
        Game game = new Game(8, 8, new Random(), 64);
        
        game.clickTile(game.getBoard().getTileAt(1, 1));
        
        assertEquals(false, game.getIsActive());
    }
}

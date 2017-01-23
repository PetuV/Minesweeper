/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

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
public class TileTest {
    
    public TileTest() {
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
    public void tileStartsAsHidden() {
        Tile tile = new Tile();
        
        assertEquals(true, tile.getIsHidden());
    }
    
    @Test
    public void tileStartsWith0Mines() {
        Tile tile = new Tile();
        
        assertEquals(0, tile.getMines());
    }
    
    @Test
    public void tileDoesntStartAsMine() {
        Tile tile = new Tile();
        
        assertEquals(false, tile.getIsMine());
    }
    
    @Test
    public void customToStringPrintsMinesAndStatus() {
        Tile tile = new Tile();
        
        assertEquals("0 mines. Hidden: true. Mine: false", tile.toString());
    }
    
    @Test
    public void revealTileWorksOnEmptyTile() {
        Tile tile = new Tile();
        
        assertEquals(false, tile.revealTile());
    }
    
    @Test
    public void revealTileWorksOnTileWithMine() {
        Tile tile = new Tile();
        tile.setIsMine(true);
        assertEquals(true, tile.revealTile());
    }
    
    @Test
    public void canSetIsHidden() {
        Tile tile = new Tile();
        tile.setIsHidden(false);
        assertEquals(false, tile.getIsHidden());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.util.Random;

/**
 *
 * @author Petteri
 */
public class Game {
    private Board gameBoard;
    private int flagsLeft;
    private boolean isActive;
    //private whatever time;
    
    public Game(int x, int y, Random random, int mines) {
        this.gameBoard = new Board(x, y, random, mines);
        this.flagsLeft = mines;
        this.isActive = true;
    }
    
    public void toggleFlag(Tile tile) {
        tile.setIsFlagged(!tile.getIsFlagged());
        if (tile.getIsFlagged()) {
            flagsLeft--;
        } else {
            flagsLeft++;
        }
    }
    
    public void clickTile(Tile tile) {
        if (tile.getIsHidden() && !tile.getIsFlagged()) {
            if (tile.getIsMine()) {
                //Lose
                isActive = false;
            } else {
                gameBoard.revealTile(tile);
            }
        }
    }
    
    public void printTiles() {
        Tile[][] tiles = gameBoard.getTiles();
        
        for (int i = 0; i < tiles.length; i++) {
            String s = "";
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsFlagged()) {
                    s += "F";
                } else if (tiles[i][j].getIsHidden()) {
                    s += "?";
                } else if (tiles[i][j].getIsMine()) {
                    s += "*";
                } else  {
                    s += tiles[i][j].getMines();
                }
            }
            System.out.println(s);
        }
    }
    
    public Board getBoard() {
        return gameBoard;
    }
    
    public int getFlagsLeft() {
        return flagsLeft;
    }
    
    public boolean getIsActive() {
        return isActive;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

/**
 *
 * @author Petteri
 */
public class Tile {
    private int mines;
    private boolean isMine;
    private boolean isHidden;
    
    public Tile() {
        this.mines = 0;
        this.isMine = false;
        this.isHidden = true;
    }
    
    public boolean revealTile() {
        isHidden = false;
        return isMine;
    }
    
    public int getMines() {
        return mines;
    }
    
    public void setMines(int n) {
        mines = n;
    }
    public boolean getIsMine() {
        return isMine;
    }
    
    public void setIsMine(boolean bool) {
        isMine = bool;
    }
    
    public boolean getHidden() {
        return isHidden;
    }
    
    public void setIsHidden(boolean bool) {
        isHidden = bool;
    }
    
    @Override
    public String toString() {
        return(mines + " mines. Hidden: " + isHidden + ". Mine: " + isMine);
    }
}

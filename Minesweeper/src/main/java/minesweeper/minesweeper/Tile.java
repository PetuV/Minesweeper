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
    private int x;
    private int y;
    private int mines;
    private boolean isMine;
    private boolean isHidden;
    private boolean isFlagged;
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.mines = 0;
        this.isMine = false;
        this.isHidden = true;
        this.isFlagged = false;
    }
    
    public void reveal() {
        isHidden = false;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int n) {
        x = n;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int n) {
        x = y;
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
    
    public boolean getIsHidden() {
        return isHidden;
    }
    
    public void setIsHidden(boolean bool) {
        isHidden = bool;
    }
    
    public boolean getIsFlagged() {
        return isFlagged;
    }
    
    public void setIsFlagged(boolean bool) {
        isFlagged = bool;
    }
    
    @Override
    public String toString() {
        return (mines + " mines. Hidden: " + isHidden + ". Mine: " + isMine + ". Flagged: " + isFlagged);
    }
}

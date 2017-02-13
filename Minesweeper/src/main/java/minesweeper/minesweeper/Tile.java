/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Petteri
 */
public class Tile extends JLabel implements MouseListener {
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
        //setText(x + ", " + y);
        determineText();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.addMouseListener(this);
    }
    
    public void reveal() {
        isHidden = false;
        determineText();
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
    
    public int getTileX() {
        return x;
    }
    
    public int getTileY() {
        return y;
    }
    /**
    * Asettaa ruudun tekstin sen tilan mukaisesti.
    */
    public void determineText() {
        if (isFlagged) {
            setText("F");
        } else if (isHidden) {
            setText("?");
        } else if (isMine) {
            setText("X");
        } else if (mines == 0) {
            setText("");
        } else {
            setText(Integer.toString(mines));
        }
    }
    
    @Override
    public String toString() {
        return (mines + " mines. Hidden: " + isHidden + ". Mine: " + isMine + ". Flagged: " + isFlagged);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
//        setText("jeo");
        Board board = (Board) this.getParent();
        board.revealTile(this);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}

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
import javax.swing.SwingUtilities;

/**
 * Simuloi pelilaudon ruutua. 
 * Pitää sisälään ruudun tiedot ja osan toiminnallisuudesta.
 * @author Petteri
 */
public class Tile extends JLabel implements MouseListener {
    private int x;
    private int y;
    private int mines;
    private boolean isMine;
    private boolean isHidden;
    private boolean isFlagged;
    
    /**
    * Tilen konstruktori.
    * @param x Tilen X -koordinaatti.
    * @param y Tilen Y -koordinaatti.
    */
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
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
    }
    /**
    * Paljastaa ruudun ja päivittää sen determineText() metodilla.
    */
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
    
    //Nimetty hassusti koska getX overrideaa JLabelin metodin
    public int getTileX() {
        return x;
    }
    
    //Nimetty hassusti koska getY overrideaa JLabelin metodin
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
    /**
    * Asettaa ruutuun lipun tai ottaa sen pois, jos sellainen on jo.
    * Jos lippu poistetaan, ruutu myös piilotetaan.
    * Sitten päivitetään ruutu determineText() metodilla.
    * @return Palauttaa tiedon siitä, onko Tilessä metodin jälkeen lippu.
    */
    public boolean putFlag() {
        if (!isFlagged) {
            isFlagged = true;
            isHidden = true;
        } else {
            isFlagged = false;
        }
        
        determineText();
        return isFlagged;
    }
    
    @Override
    public String toString() {
        return (mines + " mines. Hidden: " + isHidden + ". Mine: " + isMine + ". Flagged: " + isFlagged);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Board board = (Board) this.getParent();
        if (SwingUtilities.isLeftMouseButton(me)) {
            board.revealTile(this);
        } else if (SwingUtilities.isRightMouseButton(me)) {
            board.toggleFlag(this);
        }
        
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

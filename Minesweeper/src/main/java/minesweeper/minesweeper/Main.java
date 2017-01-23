/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Petteri
 */
public class Main {
    public static void main(String[] args) {
        Board gameBoard = new Board(8, 8, new Random(), 10);
//        System.out.println(Arrays.toString(gameBoard.getTiles()));
        printTiles(gameBoard);
    }
    
    public static void printTiles(Board gameBoard) {
        Tile[][] tiles = gameBoard.getTiles();
        
        for (int i = 0; i < tiles.length; i++) {
            String s = "";
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine()) {
                    s += "*";
                } else  {
                    s += tiles[i][j].getMines();
                }
            }
            System.out.println(s);
        }
    }
}

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
//        Board gameBoard = new Board(8, 8, new Random(), 10);
////        System.out.println(Arrays.toString(gameBoard.getTiles()));
//        printTiles(gameBoard);
//        //System.out.println(gameBoard.getNeighbors(5, 5));
//        System.out.println("");
//        System.out.println("--------");
//        System.out.println("");
//        gameBoard.revealTile(gameBoard.getTileAt(1, 1));
//        printTiles(gameBoard);
//        System.out.println("");
//        System.out.println("--------");
//        System.out.println("");
//        gameBoard.revealAllTiles();
//        printTiles(gameBoard);

        Game game = new Game(8, 8, new Random(), 10);
        game.printTiles();
        System.out.println("");
        System.out.println("--------");
        System.out.println("");
        game.clickTile(game.getBoard().getTileAt(3, 3));
        game.printTiles();
        System.out.println("");
        System.out.println("--------");
        System.out.println("");
        game.getBoard().revealAllTiles();
        game.printTiles();
    }
    
    public static void printTiles(Board gameBoard) {
        Tile[][] tiles = gameBoard.getTiles();
        
        for (int i = 0; i < tiles.length; i++) {
            String s = "";
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsHidden()) {
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Petteri
 */
public class Board {
    private Tile[][] tiles;
    
    public Board(int x, int y, Random random, int mines) {
        tiles = new Tile[x][y];
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
        setMines(random, mines);
        countNeighboringMines();
    }
    
    public void setMines(Random random, int mines) {
        for (int i = 0; i < mines; i++) {
            int x = random.nextInt(tiles.length);
            int y = random.nextInt(tiles[0].length);

            while (tiles[x][y].getIsMine()) {
                x = random.nextInt(tiles.length);
                y = random.nextInt(tiles[0].length);
            }

            tiles[x][y].setIsMine(true);
        }
    }
    
    public void countNeighboringMines() {
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                if (!tiles[i][j].getIsMine()) {
//                    int mines = 0;
//                    int xmin = i - 1;
//                    int ymin = j - 1;
//                    int xmax = i + 1;
//                    int ymax = j + 1;
//                    if (i == 0) {
//                        xmin = 0;
//                    }
//                    if (j == 0) {
//                        ymin = 0;
//                    }
//                    if (i == tiles.length - 1) {
//                        xmax = tiles.length - 1;
//                    }
//                    if (j == tiles[0].length - 1) {
//                        ymax = tiles[0].length - 1;
//                    }
//                    
//                    for (int k = xmin; k <= xmax; k++) {
//                        for (int l = ymin; l <= ymax; l++) {
//                            if (tiles[k][l].getIsMine()) {
//                                mines++;
//                            }
//                        }
//                    }
//                    tiles[i][j].setMines(mines);
//                } else {
//                    tiles[i][j].setMines(-1);
//                }
//            }
//        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getIsMine()) {
                    tiles[i][j].setMines(-1);
                } else {
                    int mines = 0;
                    ArrayList<Tile> neighbors = getNeighbors(tiles[i][j]);
                    for (int k = 0; k < neighbors.size(); k++) {
                        if (neighbors.get(k).getIsMine()) {
                            mines++;
                        }
                    }
                    tiles[i][j].setMines(mines);
                }
            }
        }
    }
    
    public ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        int xmin = tile.getX() - 1;
        int ymin = tile.getY() - 1;
        int xmax = tile.getX() + 1;
        int ymax = tile.getY() + 1;
        if (tile.getX() == 0) {
            xmin = 0;
        }
        if (tile.getY() == 0) {
            ymin = 0;
        }
        if (tile.getX() == tiles.length - 1) {
            xmax = tiles.length - 1;
        }
        if (tile.getY() == tiles[0].length - 1) {
            ymax = tiles[0].length - 1;
        }

        for (int i = xmin; i <= xmax; i++) {
            for (int j = ymin; j <= ymax; j++) {
                if (i != tile.getX() || j != tile.getY()) {
                    neighbors.add(tiles[i][j]);
                }
            }
        }
        
        return neighbors;
    }
    
    public void revealTile(Tile tile) {
        if (tile.getIsHidden()) {
            tile.reveal();
            if (tile.getMines() == 0) {
                ArrayList<Tile> neighbors = getNeighbors(tile);
                for (int i = 0; i < neighbors.size(); i++) {
                    revealTile(neighbors.get(i));
                }
            }
            
        }
    }
    
    public void revealAllTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].setIsHidden(false);
            }
        }
    }
    
    public Tile[][] getTiles() {
        return tiles;
    }
    
    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Board pitää sisällään kaksiulotteisen taulukon kaikista tileistä 
 * ja suuren osan pelin toiminnallisuudesta.
 * @author Petteri
 */
public class Board extends JPanel {
    private Tile[][] tiles;
    private int flagsLeft;
    
    /**
    * Luokan konstrukstori.
    * @param x Pelilaudan leveys.
    * @param y Pelilaudan korkeus.
    * @param random Käytetään miinojen asettamisessa satunnaisiin sijainteihin.
    * @param mines Miinojen määrä.
    */
    public Board(int x, int y, Random random, int mines) {
        setLayout(new GridLayout(x, y));
        setPreferredSize(new Dimension(350, 350));
        tiles = new Tile[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tiles[i][j] = new Tile(i, j);
                this.add(tiles[i][j]);
            }
        }
        setMines(random, mines);
        countNeighboringMines();
        flagsLeft = mines;
    }
    /**
    * Asettaa ruuduille miinoja parametreina annetun
    * mines luvun verran ja randomin mukaisesti.
    * 
    * @param random Satunnaislukujuttu
    * @param mines Miinojen määrä
    */
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
    /**
    * Laskee, kuinka monta miinaa on kunkin ruudun ympärillä
    * ja asettaa ruudun mines muuttujan.
    */
    public void countNeighboringMines() {
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
    /**
    * Etsii kaikki vieressä olevat ruudut
    * syötteenä annetulle ruudulle.
    *
    * @param   tile   Ruutu, jonka naapurit halutaan etsiä.
    * 
    * @return Lista naapureista.
    */
    public ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        int xmin = tile.getTileX() - 1;
        int ymin = tile.getTileY() - 1;
        int xmax = tile.getTileX() + 1;
        int ymax = tile.getTileY() + 1;
        if (tile.getTileX() == 0) {
            xmin = 0;
        }
        if (tile.getTileY() == 0) {
            ymin = 0;
        }
        if (tile.getTileX() == tiles.length - 1) {
            xmax = tiles.length - 1;
        }
        if (tile.getTileY() == tiles[0].length - 1) {
            ymax = tiles[0].length - 1;
        }

        for (int i = xmin; i <= xmax; i++) {
            for (int j = ymin; j <= ymax; j++) {
                if (i != tile.getTileX() || j != tile.getTileY()) {
                    neighbors.add(tiles[i][j]);
                }
            }
        }
        
        return neighbors;
    }
    /**
    * Paljastaa ruudun ja kutsuu itsensä paljastamaan ruudun naapurit, 
    * jos ruudun vieressä ei ole yhtään miinaa.
    *
    * @param   tile   Ruutu, joka paljastetaan.
    */
    public void revealTile(Tile tile) {
        if (tile.getIsHidden() && !tile.getIsFlagged()) {
            if (tile.getIsMine()) {
                lose();
            } else {
                tile.reveal();
                if (tile.getMines() == 0) {
                    ArrayList<Tile> neighbors = getNeighbors(tile);
                    for (int i = 0; i < neighbors.size(); i++) {
                        revealTile(neighbors.get(i));
                    }
                }
            }
        }
    }
    /**
    * Paljastaa kaikki ruudut ja vaihtaa miinan värin parametrin mukaisesti. 
    * Käytetään pelin päättyessä.
    * 
    * @param color Haluttu väri. lightGray on "oletusväri", RED hävitessä, GREEN voittaessa.
    */
    public void revealAllTiles(Color color) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].setIsHidden(false);
                if (tiles[i][j].getIsMine()) {
                    tiles[i][j].setBackground(color);
                }
            }
        }
    }
    /**
    * Laittaa tileen lipun tai ottaa sen pois, jos sillä on sellainen.
    * Sitten päivittää lippujen määrää suoritetun toiminnon mukaisesti.
    * @param tile Tile, jolle toiminto suoritetaan.
    */
    public void toggleFlag(Tile tile) {
        if (tile.putFlag()) {
            flagsLeft--;
        } else {
            flagsLeft++;
        }
        if (flagsLeft == 0) {
            checkWin();
        }
    }
    /**
    * Tarkistaa, onko olemassa ruutuja, joissa on miina, muttei lippua.
    * Jos on, paljastetaan kaikki ruudut ja väritetään ne vihreiksi.
    * Muussa tapauksessa ei tehdä mitään.
    */
    public void checkWin() {
        boolean winning = true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (!tiles[i][j].getIsFlagged() && tiles[i][j].getIsMine()) {
                    winning = false;
                    i = tiles.length;
                    break;
                }
            }
        }
        if (winning) {
            revealAllTiles(Color.GREEN);
        }
    }
    /**
    * Kutsuu revealAllTiles() metodin parametrilla Color.RED.
    * Käytetään, kun pelaaja häviää pelin.
    */
    public void lose() {
        revealAllTiles(Color.RED);
    }
    
    public Tile[][] getTiles() {
        return tiles;
    }
    
    public int getFlagsLeft() {
        return flagsLeft;
    }
    
    public void setFlagsLeft(int i) {
        flagsLeft = i;
    }
    /**
    * Palauttaa tietyssä sijainnissa olevan Tilen.
    * @param x Tilen X -koordinaatti.
    * @param y Tilen Y -koordinaatti.
    * @return Palauttaa Tilen, joka löydettiin annetuilla koordinaateilla.
    */
    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }
}

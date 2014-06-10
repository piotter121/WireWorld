/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import wireworld.elements.ElementFactory;
import wireworld.elements.cell.Insulator;
import wireworld.elements.cell.State;
import wireworld.elements.cell.Cell;
import wireworld.elements.Element;

/**
 * Klasa reprezentująca populację komórek
 *
 * @author Piotr Pyśk
 */
public class Population {

    private Cell[][] board;
    private final int height;
    private final int width;

    /**
     *
     * @param m ilość wierszy w populacji
     * @param n ilość kolumn w populacji
     */
    public Population(int m, int n) {
        height = m;
        width = n;
        board = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Cell(new Insulator());
            }
        }
    }

    /**
     * Metoda kopiująca populację
     *
     * @return kopia populacji
     */
    public Population copy() {
        Population toCopy = new Population(this.height, this.width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                toCopy.setElementOnBoard(i, j, ElementFactory.buildElement(this.getCell(i, j).getState()));
            }
        }
        return toCopy;
    }

    /**
     * Metoda zwracająca komórkę o współrzędnych podanych w argumentach
     *
     * @param i numer wiersza, w którym znajduje się komórka
     * @param j numer kolumny, w której znajduje się komórka
     * @return referencja do oczekiwanej komórki z populacji
     */
    public Cell getCell(int i, int j) {
        if (((i >= 0 && i < height) && j >= 0) && j < width) {
            return board[i][j];
        } else {
            return new Cell();
        }
    }

    /**
     * Metoda ustawiająca stan komórki
     *
     * @param i numer wiersza, w którym znajduje się komórka
     * @param j numer kolumny, w której znajduje się komórka
     * @param state stan jaki ma zostać ustawiony
     */
    public void setCellState(int i, int j, State state) {
        if (i < height && i >= 0 && j < width && j >= 0) {
            board[i][j].setState(state);
        }
    }

    /**
     * Metoda zwracająca referencję do kolejnej populacji, według ustalonych
     * zasad
     *
     * @return referencja do kolejnej populacji
     */
    public Population nextPopulation() {
        int heads;
        Population next = copy();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                heads = countHeads(i, j);
                next.board[i][j].nextState(heads);
            }
        }
        return next;
    }

    /**
     *
     * @return ilość wierszy w populacji
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return ilość kolumn w populacji
     */
    public int getWidth() {
        return width;
    }

    private int countHeads(int i, int j) {
        int result = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if ("Head".equals(getCell(a, b).getState())) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Metoda ustawiająca wszystkie komórki na stan Izolator
     */
    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j].setState(new Insulator());
            }
        }
    }

    public void setElementOnBoard(int i, int j, Element elem) {
        elem.setElementOnBoard(i, j, this);
    }

    @Override
    public String toString() {
        String p = new String();
        for (int i = 0; i < height; i++) {
            p += "[ ";
            for (int j = 0; j < width; j++) {
                p += board[i][j].getState() + ", ";
            }
            p += " ]\n";
        }
        return p;
    }
}

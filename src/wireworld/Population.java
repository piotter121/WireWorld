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
 *
 * @author Piotrek
 */
public class Population {

    private Cell[][] board;
    private final int height;
    private final int width;

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

    public Population copy() {
        Population toCopy = new Population(this.height, this.width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                toCopy.setElementOnBoard(i, j, ElementFactory.buildElement(this.getCell(i, j).getState()));
            }
        }
        return toCopy;
    }

    public Cell getCell(int i, int j) {
        if (((i >= 0 && i < height) && j >= 0) && j < width) {
            return board[i][j];
        } else {
            return new Cell();
        }
    }

    public void setCellState(int i, int j, State state) {
        if (i < height && i >= 0 && j < width && j >= 0) {
            board[i][j].setState(state);
        }
    }

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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int countHeads(int i, int j) {
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

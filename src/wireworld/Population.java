/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.io.*;

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
                board[i][j] = new Cell(i, j, new Conductor());
            }
        }
    }

    public Population(int m, int n, File in) {
        height = m;
        width = n;
        board = new Cell[m][n];
    }

    public void setCellState(int i, int j, State state) {
        if (i < height && i >= 0 && j < width && j >= 0) {
            board[i][j].setState(state);
        }
    }

    public Population nextPopulation() {
        int heads;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                heads = countHeads(i, j);
                board[i][j].nextState(heads);
            }
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int countHeads(int i, int j) {
        int result = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            if (a < 0 || a >= getHeight()) {
                continue;
            }
            for (int b = j - 1; b <= j + 1; b++) {
                if ((a == i && b == j) || b < 0 || b >= getWidth()) {
                    continue;
                }
                if (board[a][b].getState().equals("Head")) {
                    result++;
                }
            }
        }
        return result;
    }
}

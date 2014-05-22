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

    public Population(int m, int n) {
        board = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public Population(int m, int n, File in) {

    }
}

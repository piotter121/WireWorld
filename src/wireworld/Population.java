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
                board[i][j] = new Cell(new Insulator());
            }
        }
    }

    public Population(Population toCopy) {
        this.height = toCopy.height;
        this.width = toCopy.width;
        this.board = toCopy.board;
    }

    public Cell getCell(int i, int j) {
        if (((i >= 0 && i < height) && j >= 0) && j < width) {
            return board[i][j];
        } else {
            return new Cell();
        }
    }

    public Population readFromFile(File file) throws IOException {
        Population p = null;
        BufferedReader reader = null;
        String line = null;
        String[] splited;
        try {
            reader = new BufferedReader(new FileReader(file));
            try {
                line = reader.readLine();
                splited = line.split("\\s+");
                try {
                    p = new Population(Integer.parseInt(splited[0]),
                            Integer.parseInt(splited[1]));
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.err.println("Zbyt malo danych w lini: " + line);
                    p = new Population(60, 60);
                } catch (NumberFormatException n) {
                    System.err.println("Podane dane w lini " + line
                            + "nie sa liczbami");
                    p = new Population(60, 60);
                }
                while ((line = reader.readLine()) != null) {
                    splited = line.split("\\s+");
                    switch (splited[0]) {
                        case "Conductor":
                            p.setCellState(Integer.parseInt(splited[1]),
                                    Integer.parseInt(splited[2]), new Conductor());
                            break;
                        case "Tail":
                            p.setCellState(Integer.parseInt(splited[1]),
                                    Integer.parseInt(splited[2]), new Tail());
                            break;
                        case "Head":
                            p.setCellState(Integer.parseInt(splited[1]),
                                    Integer.parseInt(splited[2]), new Tail());
                            break;
                        case "Diode":
                            p.setElementOnBoard(Integer.parseInt(splited[1]),
                                    Integer.parseInt(splited[2]), new Diode());
                            break;
                    }

                }
            } catch (ArrayIndexOutOfBoundsException i) {
                System.err.println("Zignorowana linia: " + line);
                System.err.println("Zbyt malo danych");
            } catch (NumberFormatException n) {
                System.err.println("Zignorowana linia: " + line);
                System.err.println("Przynajmniej jedna z podanych liczb nie jest "
                        + "liczba calkowita");
            }
        } catch (IOException i) {
            System.err.println(i.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return p;
    }

    public void setCellState(int i, int j, State state) {
        if (i < height && i >= 0 && j < width && j >= 0) {
            board[i][j].setState(state);
        }
    }

    public Population nextPopulation() {
        int heads;
        Population next = new Population(this);
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements;

import wireworld.Population;
import wireworld.elements.cell.Conductor;

/**
 * Bramka And-Not
 *
 * @author Piotr Pyśk
 */
public class AndNot implements Element {

    @Override
    public void setElementOnBoard(int i, int j, Population p) {
        p.setCellState(i, j, new Conductor());
        p.setCellState(i + 1, j, new Conductor());
        p.setCellState(i - 1, j, new Conductor());
        p.setCellState(i - 2, j, new Conductor());
        p.setCellState(i - 1, j + 1, new Conductor());
        p.setCellState(i + 1, j + 1, new Conductor());
        p.setCellState(i + 1, j - 1, new Conductor());
        p.setCellState(i + 1, j - 2, new Conductor());
        p.setCellState(i + 2, j - 2, new Conductor());
        p.setCellState(i + 1, j - 3, new Conductor());
        p.setCellState(i, j - 3, new Conductor());
        p.setCellState(i - 1, j - 3, new Conductor());
        p.setCellState(i - 1, j - 2, new Conductor());
        p.setCellState(i - 2, j - 2, new Conductor());
        p.setCellState(i - 1, j - 1, new Conductor());
    }

}

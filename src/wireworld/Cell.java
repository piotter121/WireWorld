/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.awt.Color;

/**
 *
 * @author Piotrek
 */
public class Cell implements Element {

    private State state;
    
    public Cell() {
        state = new Insulator();
    }

    public Cell(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void nextState(int heads) {
        state.nextState(this, heads);
    }

    public String getState() {
        return state.getClass().getSimpleName();
    }

    public Color getColor() {
        return state.getColor();
    }
    
    @Override
    public String toString() {
        return this.getState();
    }

    @Override
    public void setElementOnBoard(int i, int j, Population p) {
        p.setCellState(i, j, state);
    }
}

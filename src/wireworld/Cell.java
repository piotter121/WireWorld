/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 *
 * @author Piotrek
 */
public class Cell {

    private final int i;
    private final int j;
    private State state;

    public Cell(int i, int j, State state) {
        this.i = i;
        this.j = j;
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void nextState(int heads) {
        state.nextState(this, heads);
    }
    
    public int getWerticalPosition() {
        return i;
    }
    
    public int getHorizontalPosition() {
        return j;
    }
    
    public String getState() {
        return state.getClass().getSimpleName();
    }
    
    @Override
    public String toString() {
        return this.getState() + ": " + this.getWerticalPosition() 
                + " " + this.getHorizontalPosition();
    }
}

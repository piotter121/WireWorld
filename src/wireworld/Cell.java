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

    private State state;

    public Cell(State state) {
        this.state = state;
    }

    public Cell() {
        state = new Conductor();
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void nextState() {
        state.nextState(this);
    }
}

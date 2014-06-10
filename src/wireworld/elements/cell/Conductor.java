/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements.cell;

import java.awt.Color;

/**
 * Przewodnik
 *
 * @author Piotr Pyśk
 */
public class Conductor implements State {

    @Override
    public State nextState(int heads) {
        if (heads == 1 || heads == 2) {
            return new Head();
        } else {
            return new Conductor();
        }
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

}

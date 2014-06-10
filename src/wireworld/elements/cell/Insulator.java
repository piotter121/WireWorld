/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements.cell;

import java.awt.Color;

/**
 * Izolator
 *
 * @author Piotr Pyśk
 */
public class Insulator implements State {

    @Override
    public State nextState(int heads) {
        return new Insulator();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

}

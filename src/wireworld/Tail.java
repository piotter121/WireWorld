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
public class Tail implements State {

    @Override
    public void nextState(Cell wrapper, int heads) {
        wrapper.setState(new Conductor());
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

}

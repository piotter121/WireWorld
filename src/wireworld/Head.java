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
public class Head implements State {

    @Override
    public State nextState(int heads) {
        return new Tail();
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

}

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
class Insulator implements State {

    @Override
    public void nextState(Cell wrapper, int heads) {
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

}

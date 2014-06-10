/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements.cell;

import java.awt.Color;

/**
 *
 * @author Piotrek
 */
public interface State {

    public State nextState(int heads);

    public Color getColor();
}

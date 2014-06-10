/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements.cell;

import java.awt.Color;

/**
 * Stan komórki
 *
 * @author Piotr Pyśk
 */
public interface State {

    /**
     * Metoda zwracająca kolejny stan wg ilości głów elektronu dookoła niej
     *
     * @param heads ilość głów dookoła komórki
     * @return kolejny stan
     */
    public State nextState(int heads);

    /**
     * Metoda zwracająca kolor danego stanu
     *
     * @return kolor danego stanu
     */
    public Color getColor();
}

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
public interface State {

    public void nextState(Cell wrapper, int heads);
}

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
public class Conductor implements State {
    
    @Override
    public void nextState(Cell wrapper) {
        wrapper.setState(new Head());
    }
    
}

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
public class MooreNeighbourhood implements Neighbourhood {

    Population current;

    public MooreNeighbourhood(Population current) {
        this.current = current;
    }

    @Override
    public int defineNeighbours(Cell cell) {
        
        return 0;

    }

}

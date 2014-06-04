/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Piotrek
 */
public class WireWorld {

    private Population current = new Population(60, 60);
    private Population next = null;
    private int n = 1000;

    public void setPopulation(Population p) {
        current = p;
    }

    public void setNumberOfGenerations(int n) {
        this.n = n;
    }

    public Population getPopulation() {
        return current;
    }

    public int getNumberOfGenerations() {
        return n;
    }

    public void nextStep() {
        if (n > 0) {
            next = current.nextPopulation();
            Population tmp = current;
            current = next;
            next = tmp;
            n--;
        }
    }

    public void start() {
        while (n > 0) {
            nextStep();
            System.out.println(current);
        }
    }

}

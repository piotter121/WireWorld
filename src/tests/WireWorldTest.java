/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.File;
import java.io.IOException;
import wireworld.InputOutput;
import wireworld.WireWorld;

/**
 *
 * @author Piotrek
 */
public class WireWorldTest {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        WireWorld game = new WireWorld();
        game.setPopulation(InputOutput.readFromFile(file));
        game.setNumberOfGenerations(4);
        System.out.println(game.getPopulation());
        game.start();
    }
}

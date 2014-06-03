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
public class ElementFactory {

    public static Element buildElement(String element) {
        switch (element) {
            case "Insulator":
                return new Cell(new Insulator());
            case "Head":
                return new Cell(new Head());
            case "Tail":
                return new Cell(new Tail());
            case "Conductor":
                return new Cell(new Conductor());
            case "Diode":
                return new Diode();
            default:
                return new Cell(new Insulator());
        }
    }
}

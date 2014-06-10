/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements;

import wireworld.elements.cell.Conductor;
import wireworld.elements.cell.Head;
import wireworld.elements.cell.Insulator;
import wireworld.elements.cell.Tail;
import wireworld.elements.cell.Cell;

/**
 * Fabryka elementów
 *
 * @author Piotr Pyśk
 */
public class ElementFactory {

    /**
     * Metoda budująca element
     *
     * @param element nazwa elementu do zbudowania
     * @return element, który został zbudowany
     */
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
            case "NAND":
                return new NAND();
            case "AndNot":
                return new AndNot();
            case "OR":
                return new OR();
            case "FlipFlop":
                return new FlipFlop();
            default:
                return new Cell(new Insulator());
        }
    }
}

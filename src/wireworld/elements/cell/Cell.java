/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements.cell;

import java.awt.Color;
import wireworld.Population;
import wireworld.elements.Element;

/**
 * Klasa komórki
 *
 * @author Piotr Pyśk
 */
public class Cell implements Element {

    private State state;

    /**
     * Konstruktor tworzący nową komórkę - izolator
     */
    public Cell() {
        state = new Insulator();
    }

    /**
     * Konstruktor tworzący komórkę ze stanem podanym jako argument
     *
     * @param state stan inicjowanej komórki
     */
    public Cell(State state) {
        this.state = state;
    }

    /**
     * Metoda ustawiająca stan komórki
     *
     * @param state stan do ustawienia
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Metoda ustawiająca kolejny stan komórki
     *
     * @param heads ilość głów dookoła danej komórki
     */
    public void nextState(int heads) {
        state = state.nextState(heads);
    }

    /**
     * Metoda zwracająca stan komórki w postaci napisu
     *
     * @return stan komórki w postaci napisu
     */
    public String getState() {
        return state.getClass().getSimpleName();
    }

    /**
     * Metoda zwracająca kolor danej komórki
     *
     * @return kolor danej komórki
     */
    public Color getColor() {
        return state.getColor();
    }

    @Override
    public String toString() {
        return this.getState();
    }

    @Override
    public void setElementOnBoard(int i, int j, Population p) {
        p.setCellState(i, j, state);
    }
}

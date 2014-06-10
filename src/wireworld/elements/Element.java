/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld.elements;

import wireworld.Population;

/**
 * Interfejs określający elementy możliwe do umieszczenia na populacji
 *
 * @author Piotr Pyśk
 */
public interface Element {

    /**
     * Metoda ustawiająca element w danej populacji w danej lokalizacji
     *
     * @param i wiersz, w którym ma zostać ustawiony element
     * @param j kolumna, w której ma zostać ustawiony element
     * @param p populacja, w której ma zostać ustawiony element
     */
    public void setElementOnBoard(int i, int j, Population p);
}

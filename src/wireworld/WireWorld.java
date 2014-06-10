/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

/**
 * Klasa automatu komórkowego WireWorld
 *
 * @author Piotr Pyśk
 */
public class WireWorld {

    private Population current;
    private Population next;
    private long n;

    /**
     * Konstruktor tworzący automat z domyślnymi ustawieniami
     */
    public WireWorld() {
        current = new Population(60, 60);
        next = null;
        n = 1000;
    }

    /**
     * Konstruktor tworzący automat z zadaną populacją
     *
     * @param p populacja, która ma zostać użyta
     */
    public WireWorld(Population p) {
        current = p;
        next = null;
        n = 1000;
    }

    /**
     * Metoda ustawiająca populację w automacie
     *
     * @param p populacja, która ma zostać ustawiona
     */
    public void setPopulation(Population p) {
        current = p;
    }

    /**
     * Metoda ustawiająca liczbę generacji do przeprowadzenia
     *
     * @param n liczba generacji
     */
    public void setNumberOfGenerations(long n) {
        this.n = n;
    }

    /**
     * Metoda zwracająca uchwyt do ustawionej populacji
     *
     * @return referencja do ustawionej w automacie populacji
     */
    public Population getPopulation() {
        return current;
    }

    /**
     * Metoda zwracająca ilość pozostałych do przeprowawdzenia generacji w
     * automacie
     *
     * @return liczba pozostałych generacji
     */
    public long getNumberOfGenerations() {
        return n;
    }

    /**
     * Metoda ustawiająca automat w kolejnej generacji
     */
    public void nextStep() {
        if (n > 0) {
            next = current.nextPopulation();
            Population tmp = current;
            current = next;
            next = tmp;
            n--;
        }
    }

    /**
     * Metoda uruchamiająca automat, który wykonuje pozostałą liczbę generacji
     */
    public void start() {
        while (n > 0) {
            nextStep();
        }
    }

}

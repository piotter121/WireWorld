/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import wireworld.elements.ElementFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Klasa "kontener" służąca do zapisu i odczytu z pliku
 *
 * @author Piotr Pyśk
 */
public class InputOutput {

    /**
     * Globalna metoda pomocnicza wczytująca z pliku populację, którą zwraca
     *
     * @param file obiekt z odnośnikiem do pliku, który ma zostać użyty do
     * wczytania
     * @return wczytana z pliku populacja
     */
    public static Population readFromFile(File file) {
        Population p = null;
        Scanner reader = null;
        String line = null;
        String[] splited;
        try {
            reader = new Scanner(file);
            line = reader.nextLine();
            splited = line.split("\\s+");

            try {
                p = new Population(Integer.parseInt(splited[0]),
                        Integer.parseInt(splited[1]));
            } catch (ArrayIndexOutOfBoundsException a) {
                System.err.println("Zbyt malo danych w lini: " + line);
                p = new Population(60, 60);
            } catch (NumberFormatException n) {
                System.err.println("Podane dane w lini " + line
                        + "nie sa liczbami");
                p = new Population(60, 60);
            }

            while (reader.hasNext()) {
                try {
                    line = reader.nextLine();
                    splited = line.split("\\s+");
                    p.setElementOnBoard(Integer.parseInt(splited[1]) - 1,
                            Integer.parseInt(splited[2]) - 1,
                            ElementFactory.buildElement(splited[0]));
                } catch (NumberFormatException ex) {
                    System.err.println("Zignorowana linia: " + line
                            + ";\nPodane argumenty nie sa liczbami");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.err.println("Zignorowana linia: " + line
                            + ";\nZbyt malo argumentow");
                }
            }

        } catch (FileNotFoundException i) {
            System.err.println(i.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return p;
    }

    /**
     * Pomocnicza metoda globalna zapisująca do pliku podanego w argumencie
     * populację, która też jest podana w argumencie
     *
     * @param p obiekt z populacją, która ma być zapisana
     * @param file obiekt pliku, w którym ma zostać zapisana populacja
     */
    public static void writeToFile(Population p, File file) {
        PrintWriter buffer = null;
        try {
            buffer = new PrintWriter(file);
            buffer.println(p.getHeight() + " " + p.getWidth() + "\n");
            for (int i = 0; i < p.getHeight(); i++) {
                for (int j = 0; j < p.getWidth(); j++) {
                    if (!p.getCell(i, j).getState().equals("Insulator")) {
                        buffer.println(p.getCell(i, j).getState() + " " + (i + 1) + " "
                                + (j + 1) + "\n");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Plik " + file + " do zapisu nie istnieje!!!");
        } finally {
            if (buffer != null) {
                buffer.close();
            }
        }
    }

}

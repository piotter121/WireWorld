/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wireworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Piotrek
 */
public class InputOutput {

    public static Population readFromFile(File file) throws IOException {
        Population p = null;
        BufferedReader reader = null;
        String line = null;
        String[] splited;
        try {
            reader = new BufferedReader(new FileReader(file));
            try {
                line = reader.readLine();
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
                while ((line = reader.readLine()) != null) {
                    splited = line.split("\\s+");
                    p.setElementOnBoard(Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]) - 1, ElementFactory.buildElement(splited[0]));
                }
            } catch (ArrayIndexOutOfBoundsException i) {
                System.err.println("Zignorowana linia: " + line);
                System.err.println("Zbyt malo danych");
            } catch (NumberFormatException n) {
                System.err.println("Zignorowana linia: " + line);
                System.err.println("Przynajmniej jedna z podanych liczb nie jest "
                        + "liczba calkowita");
            }
        } catch (IOException i) {
            System.err.println(i.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return p;
    }

}

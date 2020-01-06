/**
 * Aufgabe 2.3 - Quersumme berechnen
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 30 Dec 2019
 */
public class A23_Quersumme {

    /**
     * Prüft {@link #quersummeVon(int)} für eingaben
     * 
     * @param args die jeweiligen Testfälle
     */
    public static void main(String[] args) {
        for (String zahl : args) {
            System.out.printf("%-7s: %8d%n", zahl,
                quersummeVon(Integer.parseInt(zahl)));
        }
    }

    /**
     * Berechnet die Quersumme der Eingabe
     * 
     * @param eingabe die ganze Zahl, von der die Quersumme 
     *                berechnet werden soll
     * @return die Quersumme der eingabe
     */
    static int quersummeVon(int eingabe) {
        int result = 0;
        while (eingabe > 0) {
            result += eingabe % 10;
            eingabe = eingabe / 10; // Letzte Ziffer wird als Nachkommastelle
                                    // abgeschnitten
        }
        return result;
    }
}
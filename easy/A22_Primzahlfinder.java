import java.util.Scanner;

/**
 * Aufgabe 2.2 - Primzahlfinder
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 30 Dec 2019
 */
public class A22_Primzahlfinder {

    /**
     * Führt die Primzahlberechnung durch
     * 
     * @param args wird ignoriert.
     */
    public static void main(String[] args) {

        // Eingabe:
        Scanner eingabe = new Scanner(System.in);
        System.out.print("Primzahlen von 2 bis ");
        boolean[] prime = new boolean[eingabe.nextInt() + 1];
        eingabe.close();

        // Algorythmus:

        // Alle möglichen Primzahlen auf True setzten.
        for (int i = 2; i < prime.length; i++) {
            prime[i] = true;
        }

        // Gehe bis zu wurzel des Maximums durch (das reicht,
        // da so alle Vielfachen abgedeckt werden)
        for (int i = 2; i < Math.sqrt(prime.length); i++) {
            if (prime[i]) {// Ist Primzahl
                // Markiere alle Vielfachen als "keine Primzahl"
                for (int j = i; j * i < prime.length; j++) {
                    // es reicht bei i zu starten, weil die vorherigen Kombinationen
                    // ja sicher bereits abgedeckt sind. (Betrachte z.B. 13 * 2,
                    // welches bereits bei `prime[2]` mit 2*13 abgedeckt wurde)
                    prime[i * j] = false;
                }
            }
        }

        // Ausgabe:
        System.out.println("Alle Primzahlen von 2 bis " + (prime.length - 1));
        for (int i = 0; i < prime.length; i++) {
            if (prime[i])
                System.out.println(i);
        }
    }
}
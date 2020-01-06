/**
 * Aufgabe 2.1 - Schaltjahrprüfer
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 29 Dec 2019
 */
public class A21_Schaltjahrpruefer {

/**
 * Nicht gefordert, aber trotzdem Nett:
 *   Wir prüfen für jede übergebene Jahreszahl,
 *   ob es sich um ein Schaltjahr handelt :D
 *
 * @param args Liste an zu prüfenden Jahren
 */
public static void main(String[] args) {
    for (String year : args) {
        System.out.printf("%-5s: %b%n", year,
            istSchaltjahr(Integer.parseInt(year)));
    }
}

/**
 * Liefert für ein Jahr zurück, ob es sich
 * (nach dem Gregorianischen Kalender) um ein Schaltjahr handelt.
 *
 * @param jahr das zu prüfende Jahr
 * @return true, wenn Schaltjahr, false andernfalls
 */
static boolean istSchaltjahr(int jahr) {
    if (jahr % 4 == 0) {
        if (jahr % 100 == 0 && jahr % 400 != 0) {
            return false;
        }
        return true;
    }
    return false;
}

}
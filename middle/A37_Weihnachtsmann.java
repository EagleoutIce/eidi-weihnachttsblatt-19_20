import java.time.LocalDate;

/**
 * Aufgabe 3.7 - Und bis wann kommt der Weihnachtsmann?
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 04 Jan 2020
 */
public class A37_Weihnachtsmann {

    /**
     * Die Hauptmethode die die Berechnung vornimmt
     * 
     * @param args Wir können anstelle des heutigen Datums ein anderes im Format
     *             'YYYY MM DD' angeben um von diesem Tag aus die Zeit zu
     *             berechnen. Dies war nicht gefordert!
     */
    public static void main(String[] args) {
        int tage;
        LocalDate heute = LocalDate.now();
        if (args.length == 3) // Manuelles Datum
            heute = LocalDate.of(Integer.parseInt(args[0]), /* Jahr */
                                 Integer.parseInt(args[1]), /* Monat */
                                 Integer.parseInt(args[2]));/* Tag */

        // Hier setzten wir die Länge des "Jahres" und an welchem Tag im Jahr Weihnachten
        // liegt.
        // Weihnachten:
        // Wenn kein Schaltjahr = 358
        // Wenn Schaltjahr = 359 (da der Februar ja chonrologisc vor Weihnachten kommt)
        int Weihnachten = 358;
        int laengeDesJahres = 365;
        if (istSchaltjahr(heute.getYear())) {
            Weihnachten = 359;
            laengeDesJahres = 366;  // Ein Schaltjahr hat 366 Tage
        }

        // Der heutige Tag
        int today = heute.getDayOfYear();

        if (today > Weihnachten) { // Weihnachten war dieses Jahr schon.
            if (istSchaltjahr(heute.getYear() + 1)) {
                tage = 365 - today + 359;
                laengeDesJahres = 366; // Das nächste Jahr ist ein Schaltjahr
            } else {
                tage = 365 - today + 358;
            }
        } else { // Weichnachten steht dieses Jahr noch bevor.
            tage = Weihnachten - today;
        }

        // Ausgabe:
        if(tage == 0) // Dies ist auch einfach nur nett und war nicht gefordert :D
            System.out.println("Heute ist Weihnachten! Ein frohes Fest!");
        else if (tage == 1)
            System.out.println("Morgen ist Weihnachten! Na, alle Geschenke schon gekauft?");
        else
            System.out.println("In " + tage + " Tage ist Weihnachten!");

        // Fortschrittanzeige:
        // Hier berechnen wir wie viele der 24 Kacheln ausgefüllt werden müssen
        int kacheln = (int) ((((laengeDesJahres - tage) / (float) laengeDesJahres)) * 24.0);

        // Nun geben wir die Kacheln grafisch aus.
        // "...".repeat(int) erst mit Java-11 einzug erhalten hat,
        // ist hier die Hilfsmethode 'wiederhole' gegeben
        System.out.println("[" + wiederhole('\u2588',kacheln / 2) // Fülle Fortschritt, 
                    // da wir bisher halbe Kacheln gezählt haben: Teile durch zwei
                + wiederhole('\u258c',kacheln % 2) // Eventuell die Halbe Kachel?
                + wiederhole(' ',12 - (kacheln + 1) / 2) // Fülle rest mit Leerfeldern, 
                    // dies wäre auch durch .format() möglich.
                + "]");
    }

    /**
     * Hilfsmethode, generiert einen String aus "anzahl"-mal 'C'
     * 
     * @param c das zu wiederholende Zeichen
     * @param anzahl die Anzahl der wiederholungen
     * 
     * @return einen String der 'c' genau "anzahl"-mal enthält.
     */
    public static String wiederhole(char c, int anzahl){
        String result = "";
        for (int i = 0; i < anzahl; i++) {
            result += c;
        }
        return result;
    }

    /**
     * Prüft ob das Jahr im gregorianischen Kalender ein Schaltjahr ist.
     * <p>
     * Dies ist eine bereits aus '2.1 - Schaltjahrprüfer' bekannte
     * Methode.
     * 
     * @param jahr das zu prüfende Jahr.
     * 
     * @return true, wenn es sich um eien Schalthjahr handelt, sonst false. 
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
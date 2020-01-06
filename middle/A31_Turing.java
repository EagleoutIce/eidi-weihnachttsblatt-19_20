/**
 * Aufgabe 3.1 - Turing-Maschine
 *
 * @author Nathan Gruber
 * @author Floran Sihler
 *
 * @version 1.0, 31 Dec 2019
 */
public class A31_Turing{

    /// Hält die Größe des Arbeitsbandes:
    public static final int WORKING_TAPE_SIZE = 42;

    /**
     * Nicht geforderte Testmethode, die
     * für jede Eingabe {@link #turing(String)} aufruft
     * 
     * @param args die Liste an Turing-Befehlen
     */
    public static void main(String[] args){
        // turing("!+!+!");
        // turing("-------------------------!>!+++++++++++!!+++!");
        for (String input : args) {
            System.out.println(input + ": ");
            System.out.println(turing(input));
        }
    }
    
    /**
     * Lässt die Turingmaschine mit Befehlsseqzenz 'befehl'
     * 
     * @param befehl die Sequenz an Symbolen
     * 
     * @return die Ausgabe der Turingmaschine
     */
    public static String turing(String befehl){
        //hier wird das Turingband als char-Array simuliert
        char[] band = new char[WORKING_TAPE_SIZE];
        //und mit dem Buchstaben 'a' ausgefüllt
        for(int i = 0; i < band.length; i++)
            band[i] = 'a';
            
        int kopf = 0;
        
        String output = "";
        //jetzt wird der als String übergebene Befehl ausgeführt
        for(int i = 0; i < befehl.length(); i++){
            switch(befehl.charAt(i)){
                // case < (60): wenn wir sowieso am Rand des Bandes sind setzen wir den Kopf aufs Ende sonst nicht
                case '<': kopf = (kopf == 0) ? band.length-1 : kopf-1; break;
                // case > (62): wenn wir sowieso am Rand des Bandes sind setzen wir den Kopf auf den Anfang sonst nicht
                case '>': kopf = (kopf == band.length-1) ? 0 : kopf+1; break;
                // case + (43): wir inkrementieren den Wert des Feldes, auf das der Kopf zeigt
                case '+': band[kopf]++; break;
                // case - (45): wir dekrementieren den Wert des Feldes, auf das der Kopf zeigt
                case '-': band[kopf]--; break;
                // case ! (33): wir geben den Wert des Feldes aus, auf das der Kopf zeigt
                case '!': output += band[kopf]; break;
                //wenn der Befehl fehlerhaft war
                default: System.err.format("Das Symbol '%c' (i: %d) ist ungültig.%n",
                            befehl.charAt(i),i); return output + "[ERROR]";
            }
        }
        return output;
    }
}

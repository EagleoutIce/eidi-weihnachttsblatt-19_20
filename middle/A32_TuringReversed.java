import java.lang.Math;

/**
 * Aufgabe 3.2 - Turing-Reversed
 *
 * Liefert nur ein teiloptimales Ergebnis bei dem
 * allerdings eine interne Repräsentation des Bandes vorliegt.
 * 
 * @author Nathan Gruber
 * @author Floran Sihler
 *
 * @version 1.0, 01 Jan 2020
 */
public class A32_TuringReversed{

    /// Hält die Größe des Arbeitsbandes:
    public static final int WORKING_TAPE_SIZE = 42;

    /**
     * Nicht geforderte Testmethode, 
     * die für jede Eingabe {@link #turing_reverse(String)} aufruft
     * 
     * @param args die Liste an Turing-Befehlen
     */
    public static void main(String[] args){
        for (String input : args) {
            System.out.println(input + ": ");
            System.out.println(turing_reverse(input));
        }
    }

    /**
     * Generiert einen punktweise optimalen Befehlssatz 
     * für die Turingmaschine.
     * 
     * @see A31_Turing
     * 
     * @param wort Das zu kodierende Wort/Satz
     * 
     * @return Einen Turing-Code der dieses Wort erzeugt.
     */
    public static String turing_reverse(String wort){
        //unser Band
        char[] band = new char[WORKING_TAPE_SIZE];
        for(int i = 0; i < band.length; i++)
            band[i] = 'a';
        
        int zeiger = 0;
        String befehl = "";
        
        //jetzt wird das als String übergebene Wort in einen Befehl übersetzt
        for(int i = 0; i < wort.length(); i++){
            
            //Die Differenzen zwischen dem Buchstaben und 'a' sowie dem Wert des aktuellen Feldes werden gebildet
            int minimale_differenz = Integer.MAX_VALUE, stelle_der_minimalen_differenz = -1;
            boolean muessen_wir_spaeter_nach_links = false;
            
            for(int j = 0; j < band.length; j++){
                //Gesamtanzahl an notwendigen Zeichen wird berechnet
                int differenz = Math.abs(j - zeiger) + Math.abs(wort.charAt(i) - band[j]);
                
                if(differenz < minimale_differenz){
                    minimale_differenz = differenz;
                    stelle_der_minimalen_differenz = j;
                    if(j < zeiger)
                        muessen_wir_spaeter_nach_links = true;
                    else
                        muessen_wir_spaeter_nach_links = false;
                }
            }
            
            //wir müssen jetzt überlegen ob wir links oder rechts auf dem Band zur Stelle laufen: deswegen gibt es den boolean muessen_wir_spaeter_nach_links
            //wenn wir auf dem selben Feld bleiben
            if(zeiger == stelle_der_minimalen_differenz){
                for(int k = 0; k < minimale_differenz; k++){
                    if((band[zeiger] - wort.charAt(i)) >= 0)
                        befehl += '-';
                    else
                        befehl += '+';
                }
                //Buchstaben ausgeben
                befehl += '!';
                //Band anpassen
                band[zeiger] = wort.charAt(i);
            }
            // Gehen nach links
            else if(muessen_wir_spaeter_nach_links){
                //zur richtigen Stelle am Band gehen
                for(int k = 0; k < zeiger - stelle_der_minimalen_differenz; k++)
                    befehl += '<';
                for(int k = 0; k < minimale_differenz - (zeiger - stelle_der_minimalen_differenz); k++){
                    if((band[zeiger] - wort.charAt(i)) >= 0)
                        befehl += '-';
                    else
                        befehl += '+';
                }
                befehl += '!';
                zeiger = stelle_der_minimalen_differenz;
                band[zeiger] = wort.charAt(i);
            }
            // Gehen nach rechts
            else{
                for(int k = 0; k < stelle_der_minimalen_differenz - zeiger; k++)
                    befehl += '>';
                for(int k = 0; k < minimale_differenz - (stelle_der_minimalen_differenz - zeiger); k++){
                    if((band[zeiger] - wort.charAt(i)) >= 0)
                        befehl += '-';
                    else
                        befehl += '+';
                }
                befehl += '!';
                zeiger = stelle_der_minimalen_differenz;
                band[zeiger] = wort.charAt(i);
            }
        }
        //Zum Schluss wird der erzeugte Befehl zurückgegeben
        return befehl;
    }
}

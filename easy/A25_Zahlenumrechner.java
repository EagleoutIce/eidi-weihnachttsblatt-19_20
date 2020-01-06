/**
 * Aufgabe 2.5 - Zahlenumrechner
 *
 * @author Floran Sihler
 *
 * @version 1.0, 31 Dec 2019
 */
public class A25_Zahlenumrechner {
    /// Konstante für eine Hexadezimale Basis
    public static final int HEX = 16;
    /// Konstante für eine Dezimale Basis
    public static final int DEC = 10;
    /// Konstante für eine Okale Basis
    public static final int OCT =  8;
    /// Konstante für eine Binäre Basis
    public static final int BIN =  2;
    /// Konstante für eine Römische Basis (dummy-Wert!)
    public static final int ROM = -1;

    // Umrechnungstabelle für Römische Zahlen, könnte man als HashMap verwalten
    // Wir speichern alle Kombinationen um uns über das Subtrahieren keine Gedanken zu machen :D
    /// Hält die römischen Ziffersymbolkombinationen
    public static String[] roman_chars = { "M", "CM", "D", "CD", "C", "XC", "L", 
                                           "XL", "X", "IX", "V", "IV", "I" };
    /// Hält die zu {@link #roman_chars} gehörigen dezimalen Werte
    public static int[] roman_values = { 1000, 900, 500, 400, 100, 90, 50, 
                                           40, 10, 9, 5, 4, 1 };
    
    /**
     * Konvertiert A aus der Basis baseA ins Dezimalsystem
     * 
     * @param A Eine Ziffernfolge in Basis baseA
     * @param baseA Die Basis (siehe zum Beispiel {@link #HEX})
     * 
     * @return die Ziffernfolge als Dezimalzahl
     */
    public static int fromAtoDec(String A, int baseA){
        int decimal = 0;
        // - ! - Hier wäre eine Sonderbehandlung für Römische Zahlen möglich
        // Wir Iterieren (von Hinten) über die Zeichen des Strings:
        char[] arr = A.toUpperCase().toCharArray(); // Damit in HEX egal
        for (int i = arr.length-1; i >= 0; i--) {
            // Prüfe ob "Zahl":
            int digit = 0 ;
            if(arr[i] >= '0' && arr[i] <= '9')
                digit = arr[i] - '0'; // '0' = 0, '1' = 1, ...
            else if(arr[i] >= 'A' && arr[i] <= 'F')
                digit = arr[i] - 'A' + 10; // 'A' = 10, 'B' = 11, ....
            decimal += digit * Math.pow(baseA,arr.length-i-1);// Stelle * 
        }
        return decimal;
    }

    /**
     * Besonere Variante von {@link #fromDecToB(int)} die
     * gezielt ins römische Zahlsystem umrechnet.
     * 
     * @param A die zu konvertierende Eingabe
     * @return die Eingabe in römischer Notation
     */
    public static String fromDecToRom(int A){
        // Wir probieren vom höher zum niederwertigen römischen Zeichen alle bis zur Erschöpfung
        String result = "";
        for (int i = 0; i < roman_chars.length; i++) {
            while(A >= roman_values[i]) { // Zeichen passt :D
                result += roman_chars[i];
                A -= roman_values[i]; // Abziehen der Zahl
            }
        }
        return result;
    }

    /**
     * Konvertiert die Dezimalzahl A in die Basis baseB
     * 
     * @param A Eine Ziffernfolge im Dezimalsystem
     * @param baseB Die Zielbasis (siehe zum Beispiel {@link #HEX})
     * 
     * @return Die Dezimalzahl 'A' in Zielbasis baseB
     */
    public static String fromDecToB(int A, int baseB){
        if(baseB == ROM) return fromDecToRom(A);
        // Teile A sukzessiv durch base B und konkatiniere Reste
        String result = ""; 
        int remainder = 0;
        while(A > 0) {// Es gibt noch was zu teilen
            remainder = A % baseB;
            if(remainder >= 10)
                remainder += -10 + 'A'; // 10 = 'A', 11 = 'B', ...
            else remainder += '0'; // 0 = '0', 1 = '1', ....
            result = (char) remainder + result;
            A = A/baseB; // A /= baseB; Ganzzahlige Division
        }
        return result;
    }


    /**
     * Nicht benötigte Hilfsmethode für {@link #main(String[])}
     * 
     * @param input die Eingabe
     * @param baseA die Basis der Eingabe
     */
    public static void printAll(String input, int baseA){
        int decimal = fromAtoDec(input, baseA);
        System.out.println("    HEX: " + fromDecToB(decimal, HEX));
        System.out.println("    DEC: " + decimal);
        System.out.println("    Oct: " + fromDecToB(decimal, OCT));
        System.out.println("    Bin: " + fromDecToB(decimal, BIN));
        System.out.println("    ROM: " + fromDecToB(decimal, ROM));
    }

    /**
     * Nicht notwendige Methode, die für jede Eingabe
     * automatisch alle Basen berechnet.
     * 
     * @param args die jeweiligen Zahlen
     * 
     * @see #printAll(String, int)
     */
    public static void main(String[] args) {
        for (String input : args) {
            System.out.println(input + ": ");
            if(input.startsWith("0x"))// Is Hex
                printAll(input.substring(2), HEX);
            else if(input.startsWith("0o")) // Is Oct
                printAll(input.substring(2), OCT);
            else if(input.startsWith("0b")) // Is Bin
                printAll(input.substring(2), BIN);
            else // Is Dec
                printAll(input, DEC);
        }
    }

}
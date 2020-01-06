import java.lang.Math;

/**
 * Aufgabe 3.3 - Fibonacci
 *
 * @author Nathan Gruber
 * @author Floran Sihler
 *
 * @version 1.0, 31 Dec 2019
 */
public class A33_Fibonacci{


    /**
     * Nicht geforderte Testmethode, die
     * für jede Eingabe {@link #fibonacci_iterativ(long)}
     * sowie {@link #fibonacci_rekursiv(long)} aufruft
     * und die benötigte Zeit ungefähr berechnet.
     * (Hinweis: Hier findet keine exakte Messung statt.
     * Die Werte sollten dennoch eine eindeutige Sprache
     * sprechen :D)
     * 
     * @param args die Liste an Gewünschten Zahlen
     */
    public static void main(String[] args){
        for (String str_zahl : args) {
            int zahl = Integer.parseInt(str_zahl);
            System.out.println(zahl + "-te Fibonacci-Zahl: ");
            // Prüfe iterativ
            long then = System.currentTimeMillis(); // Erhalte aktuelle Zeit
            System.out.format("    Iterativ: %d (%dms) [Mit Speichern]%n",
                fibonacci_iterativ(zahl), System.currentTimeMillis()-then);
            // Prüfe rekursiv:
            then = System.currentTimeMillis();
            System.out.format("    Rekursiv: %d (%dms) [Mit Speichern]%n",
                fibonacci_rekursiv_save(zahl), System.currentTimeMillis()-then);
            then = System.currentTimeMillis();
            System.out.format("    Rekursiv: %d (%dms)%n",
                fibonacci_rekursiv(zahl), System.currentTimeMillis()-then);
        }
    }

    /**
     * Rekursive und direkte Umsetzung der Definition von
     * Fibonacci. 
     * 
     * @param N die gesuchte Fibonacci Zahl
     * 
     * @return die N-te (hihi, quack) Fibonacci Zahl
     */
    public static long fibonacci_rekursiv(int N){
        // diese Methode ist nicht sonderlich effizient, 
        // da einige Ergebnisse doppelt berechnet werden müssen
        
        // Fehlerfall
        if(N < 0) return -1;
        // Sonderfall und Basisfall
        if(N == 0) return 0;
        // Basisfall
        if(N == 1) return 1;
        // Rekursionsfall
        return (fibonacci_rekursiv(N-1) + fibonacci_rekursiv(N-2));
    }
    
    /**
     * Berechnet die n-te Fibonaccizahl iterativ.
     * 
     * @param N die gesuchte Fibonacci Zahl
     * 
     * @return die N-te Fibonacci Zahl
     */
    public static long fibonacci_iterativ(long N){
        // diese Methode ist effizienter als die rekursive, 
        // weil sie Ergebnisse zwischenspeichert
        
        // Fehlerfall
        if(N < 0) return -1;
        // Sonderfälle
        if(N == 0) return 0;
        if(N == 1 || N == 2) return 1;
        if(N == 3) return 2;
        
        // Normalfälle
        int zahl1 = 1, zahl2 = 2;
        // wir könnten auch mit einer dritten Variable rechnen :D
        for(int i = 4; i <= N; i++){
            if(zahl1 < zahl2)
                zahl1 += zahl2;
            else if(zahl1 > zahl2)
                zahl2 += zahl1;
        }
        // es wäre auch mit if-Abfrage möglich das Maximum der Zahlen zu bestimmen
        return Math.max(zahl1, zahl2);
    }

    static long[] fib_rek_save;

    /**
     * Arbeitet wie {@link #fibonacci_rekursiv(int)} speichert die Werte
     * allerdings zwischen!
     * 
     * @param N die gesuchte Fibonacci Zahl
     * 
     * @return die N-te Fibonacci Zahl
     * 
     * @see #fibonacci_rekursiv_save_helper(int)
     */
    public static long fibonacci_rekursiv_save(int N){
        if(N < 0) return -1; // Fehlerfall
        if(N == 0) return 0; // Keine Berechnung nötig 
        if(N < 3) return 1; // Keine Berechnung nötig

        fib_rek_save = new long[N+1];
        fib_rek_save[0] = 0; 
        fib_rek_save[1] = fib_rek_save[2] = 1; 
        return fibonacci_rekursiv_save_helper(N);
    }

    /**
     * Hilfsmethode für {@link #fibonacci_rekursiv(int)},
     * bevölkert {@link #fib_rek_save} von den Standardwerten
     * ausgehend.
     * 
     * @param N die gesuchte Fibonacci Zahl
     * 
     * @return die N-te Fibonacci Zahl
     */
    private static long fibonacci_rekursiv_save_helper(int N){
        if (N <= 0) return 0; // Ende der Rekursion
        if (fib_rek_save[N] == 0) // Noch nicht berechnet
            // Berechne Wert:
            fib_rek_save[N] = fibonacci_rekursiv_save_helper(N-1) 
                              + fibonacci_rekursiv_save_helper(N-2); 
        return fib_rek_save[N]; // Da Fibonacci Zahlen ab N = 1 > 0
    }
}

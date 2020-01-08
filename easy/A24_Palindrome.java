/**
 * Aufgabe 2.4 - Palindrome
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 30 Dec 2019
 */
public class A24_Palindrome {

    /**
     * Prüft {@link #istPalindrom(String)} für eingaben
     * 
     * @param args die jeweiligen Testfälle
     */
    public static void main(String[] args) {
        for (String satz : args) {
            System.out.printf("%s: %b%n", satz,
                istPalindrom(satz));
        }
        // System.out.println(istPalindrom("rEgaLlager"));
        // System.out.println(istPalindrom("ich hci!"));
        // System.out.println(istPalindrom("DU bist, ein Toller Typ!"));

    }

    /**
     * Prüft ob es sich bei der Eingabe um ein Palindrom handelt
     * 
     * @param eingabe das zu prüfende Wort/der zu prüfende Satz
     * 
     * @return true, wenn es sich um ein Palindrom handelt, sonst false
     */
    static boolean istPalindrom(String eingabe) {
        eingabe = parseEingabe(eingabe);
        for (int i = 0; i < eingabe.length() / 2; i++) {
            if (eingabe.charAt(i) != eingabe.charAt(eingabe.length() - 1 - i))
                return false;
        }
        return true;
    }

    /**
     * Bereit die Eingabe vor, entfernt alle Nicht-Buchstaben
     * 
     * @param eingabe das zu prüfende Wort/der zu prüfende Satz
     * 
     * @return die Eingabe ohne Sonderzeichen etc.
     */
    static String parseEingabe(String eingabe) {
        String result = "";
        eingabe = eingabe.toLowerCase(); // Kleinbuchstaben reichen
        for (char x : eingabe.toCharArray()) {
            // Buchstaben check
            if (x >= 'a' && x <= 'z') // Ist Zeichen
                result += x;
            if (x == 'ä' || x == 'ö' || x == 'ü') // Ist Umlaut
                result += x;
        }
        return result;
    }

}
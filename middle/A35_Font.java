import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Aufgabe 3.5 - ASCII-Text
 * 
 * [Hilfsklasse]
 * 
 * Stellt eine Schriftart da.
 *
 * @author Julian Holl
 * @author Floran Sihler
 *
 * @version 1.1, 5 Jan 2019
 */
public class A35_Font {

    int char_height = 0;

    /** 
     * Weist die Zeichen einem Index zu. 
     * Eigentlich könnte man sich hierfür eine
     * HashMap basteln. Das führt hier
     * allerdings zu weit.
     */
    private A35_Character[] font_data;

    /**
     * Lädt die Schriftart direkt aus den Daten.
     * 
     * @param font_data die Schriftdaten
     */
    public A35_Font(A35_Character[] font){
        char_height = font[0].getLines().length;
        this.font_data = font;
    }

    /**
     * Lädt die Schriftart aus der Datei.
     * Die ersten drei Zeilen der Schriftart-Datei
     * müssen dem folgenden Muster entsprechen (Zeilenummern
     * zur Übersicht):
     * <pre>
     * {@code
     *1 -Anzahl der Buchstaben-
     *2 -Höhe eines Buchstaben-
     *3 -Die Buchstaben-
     * }
     * </pre>
     * Ein Beispiel:
     * <pre>
     * {@code
     *1 27 
     *2 11
     *3  ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * }
     * </pre>
     * Das anführende Leerzeichen in Zeile 3 ist übrigens Absicht.
     * Es notiert das Leerfeld der Schriftart :D.
     * Diese Schriftart unterstützt nur Großbuchstaben!
     * 
     * @param fontName die zu ladende Schriftart (Dateipfad)
     */
    public A35_Font(String fontName){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fontName));
            // Die ersten beiden Zeilen benötigen das Format:
            String line = reader.readLine();
            int count = Integer.parseInt(line);
            line = reader.readLine();
            this.char_height = Integer.parseInt(line);
            // Nun lesen wir in der nächsten Zeile ein, welche Zeichen kommen
            char[] chars = new char[count];
            line = reader.readLine();
            for (int i = 0; i < chars.length; i++)
                chars[i] = line.charAt(i);
            
            A35_Character[] font = new A35_Character[count];
            for(int i = 0; i < count ; i++){// Die Zeichen
                // Die erste Zeile gibt an welches Zeichen es ist: 
                char font_char = chars[i];
                String[] font_data = new String[this.char_height];
                for(int j = 0; j < this.char_height; j++){// Die Zeilen eines Zeichens
                    line = reader.readLine();
                    font_data[j] = line;
                }
                font[i] = new A35_Character(font_char, font_data);
            }
            reader.close(); // Analog zum Scanner
            // System.out.println("Schriftart \"" + fontName + ".font\" geladen.");
            this.font_data = font;
        } catch (IOException e) {
            System.err.println("Laden der Schriftart gescheitert, nutze Standardschriftart");
            this.font_data = getDefaultFont().font_data;
            this.char_height = 1;
        }
    }

    /**
     * Liefert eine Standardschriftart für den Fall dass es die angegebene
     * nicht gibt.
     * 
     * @return die Standardschriftart.
     */
    public static A35_Font getDefaultFont(){
        // Wir konstruieren die Zeichen
        A35_Character[] font = new A35_Character[26];
        for (int i = 0; i < font.length; i++) {
            font[i] = new A35_Character((char)('A' + i), new String[] {/* sloppy string cast */"" + (char)('A' + i)});
        }
        return new A35_Font(font);
    }

    /**
     * Liefert das gewünschte Zeichen, sofern es existiert
     * 
     * @param c das gewünschte Zeichen
     * @return das gewünschte Schriftzeichen
     */
    public A35_Character getChar(char c){
        for (int i = 0; i < font_data.length; i++) {
            A35_Character check_char = font_data[i];
            if(check_char.getChar() == c) // gefunden
                return check_char;
        }
        return null; // existiert nicht.
    }

    /**
     * Liefert die Höhe eines Zeichens
     * @return {@link #char_height}
     */
    public int getCharHeight() { return this.char_height; }
}
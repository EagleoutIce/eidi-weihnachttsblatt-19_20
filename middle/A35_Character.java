/**
 * Aufgabe 3.5 - ASCII-Text
 * 
 * [Hilfsklasse]
 * 
 * Stellt ein einzelnes Zeichen dar.
 *
 * @author Floran Sihler
 *
 * @version 1.1, 5 Jan 2019
 */
public class A35_Character {
    /**
     * Das Zeichen das repräsentiert wird.
     */
    private char character;

    /**
     * Die Zeilen, die das Zeichen ausmachen.
     */
    private String[] lines;
    
    /**
     * Konstruiert ein Zeichen
     * 
     * @param character der char der vom Schriftzeichen repräsentiert wird
     * @param lines die Daten des Schriftzeichens
     */
    public A35_Character(char character, String[] lines){
        this.character = character;
        this.lines = lines;
    }

    /**
     * Liefert das Zeichen, welches das Schriftzeichen
     * repräsentiert
     * 
     * @return das Zeichen in {@link #character
     */
    public char getChar() { return this.character; }

    /**
     * Einfacher Getter um an die Zeilen heran zu kommen
     * 
     * @return {@link #lines}
     */
    public String[] getLines() { return this.lines; }

    /**
     * Liefert eine einzelne Zeile des Zeichens
     * 
     * @param line die gewünschte Zeile
     * @return die gewünschte Zeile als String
     */
    public String getLine(int line) { return getLines()[line]; }
}
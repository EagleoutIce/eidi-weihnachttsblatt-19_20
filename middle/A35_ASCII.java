import java.util.Scanner;

/**
 * Aufgabe 3.5 - ASCII-Text
 * 
 * @author Julian Holl
 * @author Floran Sihler
 *
 * @version 1.1, 5 Jan 2019
 */
public class A35_ASCII {
    /**
     * Hier laden wir die'Schriftart und geben die Zeichen aus
     * 
     * @param args wenn angegeben, kann so direkt Schriftart und Text gewählt werden.
     */
    public static void main(String[] args) {
        String fontName; String text; 
        if (args.length > 0) { // Wenn über die Kommandozeile
            fontName = args[0];
            text = args[1];
        } else { // Sonst manuell
            Scanner scanner = new Scanner(System.in);
            System.out.print("Gebe die gewünschte Schriftart ein> ");
            fontName = scanner.nextLine();
            System.out.print("Gebe den Text ein> ");
            text = scanner.nextLine();
            scanner.close();
        }

        A35_Font font = new A35_Font(fontName+".font");
        char[] input_chars = text.toCharArray();
        for (int line = 0; line < font.getCharHeight(); line++) {
            // Iteriere für jede Zeile über alle Zeichen:
            for (int i = 0; i < input_chars.length; i++) {
                A35_Character the_char = font.getChar(input_chars[i]);
                if(the_char == null) { // gibbet nicht
                    System.err.format("Das Zeichen '%c' wird von der Schriftart '%s' nicht geliefert!%n", input_chars[i], fontName);
                    System.exit(1);
                } 
                System.out.print(the_char.getLine(line));
            }
            System.out.println(); // new Line
        }
    }
}

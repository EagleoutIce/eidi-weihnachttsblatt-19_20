/**
 * Aufgabe 2.6 - Papa-Erpel und Mama-Ente
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.0, 30 Dec 2019
 */
public class A26_Ente {
    /// Der Name der Ente
    private String name;
    /// Das Alter der Ente
    private short alter;
    /// Referenz auf eine Mama {@link A26_Ente}
    private A26_Ente mama;
    /// Referenz auf eine Papa {@link A26_Ente}
    private A26_Ente papa;

    /**
     * Konstruiere eine Ente mit einem Namen und einem
     * initialen Alter. Die Eltern {@link #mama} und {@link #papa}
     * werden auf 'null' gesetzt. 
     * 
     * @param name der Name der Ente
     * @param alter das Alter der Ente
     */
    public A26_Ente(String name, int alter) {
        this.name = name;
        this.alter = (short) alter;
        this.papa = null; this.mama = null;
    }

    /**
     * Konstruiere eine Ente wie in {@link #A26_Ente(String, int)}, 
     * setzt allerdings auch {@link #mama} und {@link #papa}
     * 
     * @param name der Name der Ente
     * @param alter das Alter der Ente
     * @param papa die Papa-Ente
     * @param mama die Mama-Ente
     */
    public A26_Ente(String name, int alter, A26_Ente papa, A26_Ente mama) {
        this(name,alter);
        this.mama = mama;
        this.papa = papa;
    }

    /**
     * Die Ente feiert Geburtstag!
     * 
     * {@link #alter} wird um 1 erhöht!
     * 
     * @see #geburtstag(int)
     */
    public void geburtstag() {
        geburtstag(1);
    }

    /**
     * Verändert das {@link #alter} der Ente um x
     * 
     * @param x Verändert (relativ) das Alter der Ente
     */
    public void geburtstag(int x) {
        this.alter += x;
    }

    // ---- Getter

    /**
     * Liefert den {@link #name} der Ente
     * 
     * @return der Name der Ente
     */
    public String getName() {
        return name;
    }

    /**
     * Liefert die {@link #mama} der Ente
     * 
     * @return die Mutter der Ente
     */
    public A26_Ente getMother() {
        return mama;
    }

    /**
     * Liefert den {@link #papa} der Ente
     * 
     * @return der Vater der Ente
     */
    public A26_Ente getFather() {
        return papa;
    }

    /**
     * Ändert den {@link #name} der Ente
     * 
     * @param newName der neue Name der Ente
     */
    public void changeName(String newName) {
        this.name = newName;
    }

    /** 
     * Repräsentiert die Ente als Zeichenkette
     * 
     * @return Zeichenkette der Ente
     */
    public String toString() {
        if (this.mama == null || this.papa == null) {
            return "Ich heiße " + this.name + " und bin " + this.alter + " Jahre alt.";
        } else {
            return "Ich heiße " + this.name + " und bin " + this.alter + " Jahre alt" + 
                   ", meine Mutter heißt " + mama.getName() + " und mein Vater " + 
                   papa.getName() + ".";
        }
    }


    /**
     * Nicht geforderter Test :D
     * 
     * @param args werden ignroiert
     */
    public static void main(String[] args) {
        A26_Ente dieter = new A26_Ente("Dieter", 5);
        A26_Ente jasmin = new A26_Ente("Jasmin", 7);
        A26_Ente flo    = new A26_Ente("Florian", 0, dieter, jasmin);

        System.out.println("Dieter: " + dieter);
        System.out.println("Jasmin: " + jasmin);
        System.out.println("Florian: " + flo);
    }
}
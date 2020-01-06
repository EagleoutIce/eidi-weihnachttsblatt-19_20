/**
 * Aufgabe 2.6 - Papa-Erpel und Mama-Ente
 *
 * Hier für 3.8 erhält noch eine .equals()-Methode!
 * Weiter wird (Bonus und so :D) eine EntenListe an
 * Kindern verwaltet!
 *
 * @author Felix Rieg
 * @author Floran Sihler
 *
 * @version 1.1, 4 Jan 2019
 */
public class A38_Ente {
    /** Der Name der Ente */
    private String name;
    /** Das Alter der Ente */
    private short alter;
    /** Referenz auf eine Mama {@link A38_Ente} */
    private A38_Ente mama;
    /** Referenz auf eine Papa {@link A38_Ente} */
    private A38_Ente papa;

    /** Liste an Kindern */
    private A38_EntenListe kinder;

    /**
     * Konstruiere eine Ente mit einem Namen und einem
     * initialen Alter. Die Eltern {@link #mama} und {@link #papa}
     * werden auf 'null' gesetzt. 
     * 
     * @param name der Name der Ente
     * @param alter das Alter der Ente
     */
    public A38_Ente(String name, int alter) {
        this.name = name;
        this.alter = (short) alter;
        this.papa = null; this.mama = null;
        kinder = new A38_EntenListe(); // initialisere Kinder
    }

    /**
     * Konstruiere eine Ente wie in {@link #A38_Ente(String, int)}, 
     * setzt allerdings auch {@link #mama} und {@link #papa}
     * 
     * @param name der Name der Ente
     * @param alter das Alter der Ente
     * @param papa die Papa-Ente
     * @param mama die Mama-Ente
     */
    public A38_Ente(String name, int alter, A38_Ente papa, A38_Ente mama) {
        this(name,alter);
        this.mama = mama;
        this.papa = papa;

        // Füge bei den Eltern das Kind hinzu:
        this.mama.kinder.append(this);
        this.papa.kinder.append(this);
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
    public A38_Ente getMother() {
        return mama;
    }

    /**
     * Liefert den {@link #papa} der Ente
     * 
     * @return der Vater der Ente
     */
    public A38_Ente getFather() {
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
        A38_Ente dieter = new A38_Ente("Dieter", 5);
        A38_Ente jasmin = new A38_Ente("Jasmin", 7);
        A38_Ente flo    = new A38_Ente("Florian", 0, dieter, jasmin);

        System.out.println("Dieter: " + dieter);
        System.out.println("Jasmin: " + jasmin);
        System.out.println("Florian: " + flo);
    }

    /**
     * Liefert die Kinder der Ente
     * @return {@link #kinder}
     */
    public A38_EntenListe getChildren() {
        return this.kinder;
    }

    /**
     * Sind die beiden Enten gleich?
     * 
     * @param obj das zu prüfende Objekt
     * 
     * @return true, wenn ja, sonst false.
     */
    public boolean equals(Object obj) {
        if (this == obj) // Sind identisch
            return true;
        if (obj == null) // Das Objekt ist Null
            return false;
        if (!(obj instanceof A38_Ente)) // nicht die gleice Klasse
            return false;
        // Sonst können wir casten:
        A38_Ente other = (A38_Ente) obj;
        // Alter ist verschieden
        if (alter != other.alter)
            return false;
        // Diese Ente hat keinen Namen :/
        if (name == null) {
            // Wenn die andere einen hat,
            // können sie nicht gleich sein.
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false; // Wenn sie einen Namen hat,
                          // müssen diese gleich sein
    
        // Jetzt prüfen wir (rekursiv!) ob sie den gleichen Stammbaum
        // besitzen. Zuerst für die Verwandschaft mütterlicherseits:
        if (mama == null) {
            if (other.mama != null)
                return false;
        } else if (!mama.equals(other.mama))
            return false;
        // Und jetzt für die Verwandschaft väterlicherseits:
        if (papa == null) {
            if (other.papa != null)
                return false;
        } else if (!papa.equals(other.papa))
            return false;
        // Alle Tests bestanden? Glückwunsch:
        // Es ist 'du'!
        return true;
    }
    
}
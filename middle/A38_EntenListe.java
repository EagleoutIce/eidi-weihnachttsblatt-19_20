import java.util.Arrays;

/**
 * Aufgabe 3.8 - Ein Stammbaum für Enten (Voraussetzung: Aufgabe 2.6 -
 * Papa-Erpel und Mama-Ente)
 *
 * [Hilfsklasse]
 *
 * @author Floran Sihler
 *
 * @version 1.1, 4 Jan 2019
 *
 *          Wir modellieren eine 'ArrayList' von A38_Ente
 */
public class A38_EntenListe {

    /** Konstante, die den Index eines nicht gefundenen Eintrag repräsentiert */
    public static final int NOT_FOUND = -1;

    /** das Array auf dem wir arbeiten */
    private A38_Ente[] dieQuacker;

    /**
     * Konstruiere eine neue, leere EntenLite
     */
    public A38_EntenListe() {
        this.dieQuacker = new A38_Ente[0];
    }

    /**
     * Liefert die Länge der Liste
     * 
     * @return {@link #dieQuacker}
     */
    public int size() {
        return this.dieQuacker.length;
    }

    /**
     * Ist die Liste leer?
     * 
     * @return Liefert true, wenn die Liste leer ist, sonst false;
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Erlaubt einen direkten Zugriff auf das zugrundeliegende Array.
     * 
     * @param idx der Index auf den wir zugreifen möchten.
     * 
     * @return das Element, sofern der geforderte Eintrag existiert. sonst wird
     *         'null' zurückgegeben.
     */
    public A38_Ente get(int idx) {
        if (idx >= 0 && idx < size())
            return this.dieQuacker[idx];
        return null;
    }

    /**
     * Setzt das Element an der Stelle 'idx' auf den gegebenen Wert. Erlaub tden
     * direkten Zugriff auf das zugrundeliegende Array.
     * 
     * @param idx   der Index auf den wir zugreifen möchten.
     * @param value der Wert den er bekommen soll.
     * 
     * @return true, wenn die Operation gelingt (der Index existiert) sonst false.
     */
    public boolean set(int idx, A38_Ente value) {
        if (idx >= 0 && idx < size()) {
            this.dieQuacker[idx] = value;
            return true;
        }
        return false;
    }

    /**
     * Fügt die Ente der Liste hinzu
     * 
     * @param duck die neue Ente
     */
    public void append(A38_Ente duck) {
        // Erstelle ein neues Array, das um eins größer ist:
        A38_Ente[] dieNeuenQuacker = new A38_Ente[size() + 1];

        // kopiere die alten:
        for (int i = 0; i < dieQuacker.length; i++)
            dieNeuenQuacker[i] = dieQuacker[i];

        // Füge neue Ente an:
        dieNeuenQuacker[dieNeuenQuacker.length - 1] = duck;

        // Ersetze bisheriges Array:
        this.dieQuacker = dieNeuenQuacker;
    }

    /**
     * Entfernt das Element an der Stelle 'idx' aus der List
     * 
     * @param idx das Element, dass entfernt werden soll.
     * 
     * @return true, wenn der Index gültig ist, das Element also entfernt werden
     *         konnten. Sonst false.
     */
    public boolean remove(int idx) {
        // Hier machen wir den Test mal anders rum. Sollte man zwar
        // stiltechnisch nicht machen (also mischen), ist aber hier
        // um das mal gezeigt zu haben.
        if (idx < 0 && idx >= size())
            return false; // existiert nicht.

        // Erstelle Array ohne das Element
        A38_Ente[] dieNeuenQuacker = new A38_Ente[size() - 1];
        // Kopiere alle Elemente außer dieses
        for (int i = 0; i < idx; i++)
            dieNeuenQuacker[i] = this.dieQuacker[i];
        // jetzt überspringen wir das Element und kopieren weiter
        for (int i = idx + 1; i < dieQuacker.length; i++)
            dieNeuenQuacker[i - 1] = this.dieQuacker[i];

        // Setze internes Array neu:
        this.dieQuacker = dieNeuenQuacker;
        return true;
    }

    /**
     * Hilfsmethode, die das erste Element aus der Liste entfernt, es aber
     * gleichzeitig zurückliefert. Funfact: so können wir uns auch einen Stack
     * basteln!
     * 
     * @return das (ehemals) erste Element.
     * 
     * @see #removeLast()
     */
    public A38_Ente removeFirst() {
        A38_Ente ersterQuacker = get(0); // bereits abgesichert
        remove(0); // auch abgesichert, wenn es kein erstes gibt.
        return ersterQuacker;
    }

    /**
     * Hilfsmethode, die das letzte Element aus der Liste entfernt, es aber
     * gleichzeitig zurückliefert. Funfact: so können wir uns auch einen Stack
     * basteln!
     * 
     * @return das (ehemals) letzte Element.
     * 
     * @see #removeFirst()
     */
    public A38_Ente removeLast() {
        A38_Ente letzterQuacker = get(size() - 1); // bereits abgesichert
        remove(size() - 1); // auch abgesichert, wenn es kein erstes gibt.
        return letzterQuacker;
    }

    /**
     * Liefert den Index des (ersten) Vorkommens der Ente.
     * <p>
     * Ist sie nicht enthalten, wird {@value #NOT_FOUND} zurückgegeben.
     * 
     * @param duck die zu suchende Ente
     * @return der Index, an dem sie sich befindet. ist sie nicht enthalten, wird
     *         '-1' zurückgeliefert.
     */
    public int getIndex(A38_Ente duck) {
        for (int i = 0; i < dieQuacker.length; i++) {
            if (dieQuacker[i].equals(duck))
                return i;
        }
        return NOT_FOUND;
    }

    /**
     * Hilfsmethodem die prüft, ob die Ente enthalten ist.
     * 
     * @param duck die zu prüfende Ente.
     * @return true, wenn enthalten, sonst false;
     * 
     * @see #getIndex(A38_Ente)
     */
    public boolean contains(A38_Ente duck) {
        return getIndex(duck) != NOT_FOUND;
    }

    /**
     * Einfach nur eine hübsche Ausgabe der Liste und so :D
     */
    public String toString() {
        String output =  "A38_EntenListe [:size=" + size() + ", dieQuacker={"; 
        for (int i = 0; i < dieQuacker.length; i++) {
            output += (i!=0 ? ", " : "") + (dieQuacker[i] != null ? dieQuacker[i].getName() : "NULL");
        }
        return output + "}]";
    }
    
}

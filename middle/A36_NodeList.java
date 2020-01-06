import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 3.6 TicTacToe-KI
 * 
 * [Hilfsklasse]
 * 
 * Erlaubt es Knoten {@link A36_Node} in einer Liste zu
 * verwalten.
 * <p>
 * Diese Liste orientiert sich an A36_NodeList
 * - da merkt man mal in welche rReihenfolge diese Lösungen
 * entstehen :D
 * 
 * @author Florian Sihler
 */
public class A36_NodeList {
    
    /** Konstante, die den Index eines nicht gefundenen Eintrag repräsentiert */
    public static final int NOT_FOUND = -1;

    /** das Array auf dem wir arbeiten */
    private A36_Node[] theNodes;

    /**
     * Konstruiere eine neue, leere Liste
     */
    public A36_NodeList() {
        this.theNodes = new A36_Node[0];
    }

    /**
     * Liefert die Länge der Liste
     * 
     * @return {@link #theNodes}
     */
    public int size() {
        return this.theNodes.length;
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
    public A36_Node get(int idx) {
        if (idx >= 0 && idx < size())
            return this.theNodes[idx];
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
    public boolean set(int idx, A36_Node value) {
        if (idx >= 0 && idx < size()) {
            this.theNodes[idx] = value;
            return true;
        }
        return false;
    }

    /**
     * Fügt die Node der Liste hinzu
     * 
     * @param node die neue Node
     */
    public void append(A36_Node node) {
        // Erstelle ein neues Array, das um eins größer ist:
        A36_Node[] theNewNodes = new A36_Node[size() + 1];

        // kopiere die alten:
        for (int i = 0; i < theNodes.length; i++)
            theNewNodes[i] = theNodes[i];

        // Füge neue Node an:
        theNewNodes[theNewNodes.length - 1] = node;

        // Ersetze bisheriges Array:
        this.theNodes = theNewNodes;
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
        A36_Node[] theNewNodes = new A36_Node[size() - 1];
        // Kopiere alle Elemente außer dieses
        for (int i = 0; i < idx; i++)
            theNewNodes[i] = this.theNodes[i];
        // jetzt überspringen wir das Element und kopieren weiter
        for (int i = idx + 1; i < theNodes.length; i++)
            theNewNodes[i - 1] = this.theNodes[i];

        // Setze internes Array neu:
        this.theNodes = theNewNodes;
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
    public A36_Node removeFirst() {
        A36_Node firstNode = get(0); // bereits abgesichert
        remove(0); // auch abgesichert, wenn es kein erstes gibt.
        return firstNode;
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
    public A36_Node removeLast() {
        A36_Node lastNode = get(size() - 1); // bereits abgesichert
        remove(size() - 1); // auch abgesichert, wenn es kein erstes gibt.
        return lastNode;
    }

    /**
     * Liefert den Index des (ersten) Vorkommens des Knotens.
     * <p>
     * Ist sie nicht enthalten, wird {@value #NOT_FOUND} zurückgegeben.
     * 
     * @param node der zu suchende Knoten
     * @return der Index, an dem sie sich befindet. Ist sie nicht enthalten, wird
     *         '-1' zurückgeliefert.
     */
    public int getIndex(A36_Node node) {
        for (int i = 0; i < theNodes.length; i++) {
            if (theNodes[i].equals(node))
                return i;
        }
        return NOT_FOUND;
    }

    /**
     * Hilfsmethodem die prüft, ob die Node enthalten ist.
     * 
     * @param node die zu prüfende Node.
     * @return true, wenn enthalten, sonst false;
     * 
     * @see #getIndex(A36_Node)
     */
    public boolean contains(A36_Node node) {
        return getIndex(node) != NOT_FOUND;
    }

}

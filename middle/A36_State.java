/**
 * Aufgabe 3.5 - ASCII-Text
 * 
 * [Hilfsklasse]
 * 
 * Verwaltet wer aktuell am Zug ist.
 * 
 * @author Raphael Straub
 * @author Florian Sihler
 */
public enum A36_State {
    /** Keiner ist am Zug */
    EMPTY,
    /** Kreuz/der menschliche Spieler ist am Zug */
    CROSS,
    /** Kreis/der KI-Spieler, weil Kreise perfekt sind! */
    CIRCLE;

    /**
     * wechselt den aktuellen Spieler.
     */
    public A36_State toggle(){
        switch (this){
            case CROSS: return CIRCLE;
            case CIRCLE: return CROSS;
            default: return EMPTY;
        }
    }
}

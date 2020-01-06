/**
 * Aufgabe 3.5 - ASCII-Text
 * 
 * [Hilfsklasse]
 * 
 * Verwaltet die KI und liefert ihren Zug.
 * 
 * @author Raphael Straub
 * @author Florian Sihler
 */
public class A36_Ai {

    /**
     * Liefert den besten Zug, den die KI auf dem
     * aktuellen Feld machen kann.
     * 
     * @return Feld das gesetzt werden soll.
     */
    public static int getTurn(A36_Board board) {
        return new A36_Node(board, A36_State.CIRCLE).getBestMove();
    }
}

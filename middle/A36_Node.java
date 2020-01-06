import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 3.6 TicTacToe-KI
 * 
 * [Hilfsklasse]
 * 
 * Repräsentiert eine Baumstruktur an Knoten.
 * Jeder Knoten kann eine Liste an Kindknoten
 * besitzen.
 * 
 * @author Raphael Straub
 * @author Florian Sihler
 */

public class A36_Node {
    /** Die Kinder des Knoten */
    private final A36_NodeList childA36_Nodes = new A36_NodeList();
    /** Das Spielfeld, das im Knoten gespeichert ist. */
    private final A36_Board board;
    /** Das Symbol des aktuellen Spielers (also dessen, der am Zug ist) */
    private final A36_State turn;
    /** Die Bewertung des Knotens */
    private int evaluation;
    /** Der beste Zug für den Knoten */
    private int bestMove;

    /**
     * Konstruiert einen neuen Knoten
     * 
     * @param board das aktuelle Spielfeld
     * @param turn der nächste Zug ({@link A36_State#Circle}
     *             for the Root note)
     */
    public A36_Node(A36_Board board, A36_State turn) {
        this.board = board;
        this.turn = turn;
        if(board.getWinner() == A36_State.EMPTY && board.isNotFull())
            generateChildA36_Nodes();

        evaluation = evaluate();
    }

    /**
     * Fügt jede mögliche Platzierung den Kindern hinzu.
     * Baut also die nächste Iteration des Spielbaumes auf.
     */
    private void generateChildA36_Nodes() {
        for(int i = 0; i < board.board.length; i++)
            if(board.board[i] == A36_State.EMPTY)
                childA36_Nodes.append(new A36_Node(board.clone().set(i, turn),turn.toggle()));
    }

    /**
     * Liefere den Wert des aktuellen Boards, das in diesem
     * Knoten gespeichert ist.
     * <p>
     * <pre>
     * {@code
     * -1 -> "Der Mensch gewinnt - finden wir als KI dann
     *       prinzipiell doof"
     * 1 -> "Die KI gewinnt."
     * 0 -> "Unentschieden"
     *}
     * </pre>
     * @return
     */
    private int evaluate(){
        switch (board.getWinner()){
            case CROSS: return -1;
            case CIRCLE: return 1;
            default:
                if(board.isNotFull())
                    // Sind wir am Zug? Entscheide entsprechend welches Kind
                    // gesucht wird.
                    return (turn==A36_State.CIRCLE) ? searchGreatestChilds() : searchSmallestChilds();
                else
                    return 0;
        }
    }

    /**
     * Sucht das wertvollste Kind und initialisiert {@link #bestMove}
     * 
     * @return der Wert des besten Kindes
     */
    private int searchGreatestChilds(){
        A36_Node current = childA36_Nodes.get(0);
        for (int i = 1; i < childA36_Nodes.size(); i++) {
            if(current.evaluation < childA36_Nodes.get(i).evaluation)
                current = childA36_Nodes.get(i);
        }
        bestMove = current.board.lastPositionChange;
        return current.evaluation;
    }

    /**
     * Sucht das am wenigsten wertvollste Kind und initialisiert {@link #bestMove}
     * 
     * @return der Wert des besten Kindes
     */
    private int searchSmallestChilds(){
        A36_Node current = childA36_Nodes.get(0);
        for (int i = 1; i < childA36_Nodes.size(); i++) {
            if(current.evaluation > childA36_Nodes.get(i).evaluation)
                current = childA36_Nodes.get(i);
        }
        bestMove = current.board.lastPositionChange;
        return current.evaluation;
    }

    /**
     * Liefert den besten Move
     * 
     * @return {@link #bestMove}
     */
    public int getBestMove() {
        return bestMove;
    }
}

import java.util.Arrays;

/**
 * 3.6 TicTacToe-KI
 * 
 * [Hilfsklasse]
 * 
 * Repräsentiert das Spielfeld
 * 
 * @author Raphael Straub
 * @author Florian Sihler
 */

public class A36_Board {
    /** Interne Repräsentation des Spielfeldes */
    public A36_State[] board = new A36_State[9];
    /** Letzte Änderung */
    public int lastPositionChange;
    /** Hilfsarray, welches alle Siegkombinationen hält. */
    private static int[][] winStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    /**
     * Erstellt ein neues, leeres Spielfeld.
     */
    public A36_Board(){
        for (int i = 0; i < board.length; i++)
            board[i] = A36_State.EMPTY;
    }

    /**
     * Gibt das Spielfeld in einer schönen Notation aus.
     * Man könnte auch einfach 'toString()' überladen.
     * Allerdings handelt es sich hier ja um eine grafische
     * Aufarbeitung und nicht um eine bloße Repräsentation
     * der Daten.
     */
    public void print(){
        System.out.println();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++)
                drawCell(i*3 + j);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Zeichnet eine einzelne Zelle
     * 
     * @param cellNumber nummer der Zelle
     */
    private void drawCell(int cellNumber){
        System.out.print("[");
        switch (board[cellNumber]){
            case CROSS: System.out.print("X"); break;
            case CIRCLE: System.out.print("O"); break;
            case EMPTY: System.out.print(cellNumber + 1); break;
        }
        System.out.print("]");
    }

    /**
     * Liefert den Gewinner, wenn es denn einen gibt.
     * 
     * @return Wenn ein Gewinner existiert dann seinen State,
     *         sonst {@link A36_State#EMPTY}
     */
    public A36_State getWinner(){
        for(int[] a : winStates)
            if(board[a[0]] == board[a[1]] && board[a[1]] == board[a[2]])
                return board[a[0]]; // Wir haben einen Gewinner

        return A36_State.EMPTY;
    }

    /**
     * Schaut, ob es noch möglich ist zu ziehen.
     * 
     * @return true wenn ja, sonst nein
     */
    public boolean isNotFull(){
        for (int i = 0; i < board.length; i++) {
            if(board[i] == A36_State.EMPTY)
                return true;
        }
        return false;
    }

    /**
     * Setzt ein Feld auf dem Board und liefert das neue
     * Board extra zurück (erlaubt eine einfachere 
     * Benutzung)
     * 
     * @param position die Position die gesetzt werden soll.
     * @param state der zu setzende Zustand.
     * 
     * @return eine Referenz auf das eigene Objekt.
     */
    public A36_Board set(int position, A36_State state){
        lastPositionChange = position;
        board[position] = state;
        return this;
    }

    /**
     * Liefert eine neue Kopie des Boards
     */
    public A36_Board clone() {
        A36_Board clone = new A36_Board();
        clone.lastPositionChange = this.lastPositionChange;
        // Kopiere Array 
        clone.board = new A36_State[9];
        for (int i = 0; i < board.length; i++) {
            clone.board[i] = this.board[i];
        }
        return clone;
    }
}

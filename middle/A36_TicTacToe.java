import java.util.Scanner;

/**
 * 3.6 TicTacToe-KI
 * 
 * Spielt das Spiel
 * 
 * @author Raphael Straub
 * @author Florian Sihler
 */
public class A36_TicTacToe {
    /** Repräsentation des Spielfeldes */
    private final A36_Board board = new A36_Board();
    /** 
     * Flagge die angibt wer gerade dran ist.
     * 
     * true: Spieler, false: KI
     */
    private Boolean turn;
    /** Scanner zum verarbeiten der Nutzereingaben. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Standardkonstruktor
     *
     * @param playerStarts Gibt an wer starten soll. True: Spieler, false: KI.
     */
    A36_TicTacToe(Boolean playerStarts){
        this.turn = playerStarts;
    }


    /**
     * Spielt das Spiel
     */
    public void run() {
        while(!isOver()){
            board.print();
            doTurn();
        }
        board.print();
    }

    /**
     * Entscheidet wer Ziehen darf.
     */
    private void doTurn() {
        if(turn)
            playerTurn(); // Spieler ist dran
        else
            aiTurn(); // Die KI ist dran
        turn = !turn; // toggelt wer dran ist, aus true wird false
        // aus false wird true
    }

    /**
     *  Verarbeite einen Spielzug
     */
    private void playerTurn(){
        int input = getInput();
        if(board.board[input] == A36_State.EMPTY)
            board.board[input] = A36_State.CROSS;
        else
            playerTurn();
    }

    /**
     * Frage den Nutzer so lange nach einer Eingabe,
     * bis sie gültig ist (zwischen 0-9, sowie noch nicht
     * belegt).
     * 
     * @return die (gültige) Eingabe.
     */
    private int getInput(){
        System.out.print("Enter Number: ");
        String input = scanner.nextLine().trim(); // Entferne Leerfelder
        System.out.println();
        if (!input.equals("") && Integer.parseInt(input) <= 9 && Integer.parseInt(input) > 0)
            return Integer.parseInt(input)-1;
        else
            return getInput();
    }

    /**
     * Führt den Zug der KI aus.
     */
    private void aiTurn(){
        board.board[A36_Ai.getTurn(board)] = A36_State.CIRCLE;
    }

    /**
     * Prüft, ob das Spiel vorbei ist.
     * 
     * @return true wenn das Spiel vorbei ist, sonst false
     */
    private Boolean isOver(){
        // Die Texte hier sind kreative Platzhalter :D
        switch (board.getWinner()){
            case CIRCLE: System.out.println("Juhu!! Kreis gewinnt.\n"); return true;
            case CROSS: System.out.println("Supa Dupa!! Kreuz gewinnt.\n"); return true;
            default: 
                if(board.isNotFull()) {
                    return false;
                } else {
                    System.out.println("Ein Unentschieden! Glückwunsch an beide Seiten");
                    return true;
                }
        }
    }

    public static void main(String[] args) {
        new A36_TicTacToe(/* Spieler startet: */ args.length == 0).run();
    }
}

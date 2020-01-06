import java.io.BufferedReader;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Aufgabe 3.4 - Wer Wird Entionär? 
 * 
 * [Hauptklasse]
 * 
 * @author Nikolas Rank
 * @author Floran Sihler
 *
 * @version 1.0, 03 Jan 2020
 */
public class A34_Entionaer {

    /** Konstante zur besseren Lesbarkeit */
    public static final int SUPER_EASY = 0;
    /** Konstante zur besseren Lesbarkeit */
    public static final int EASY = 1;
    /** Konstante zur besseren Lesbarkeit */
    public static final int MIDDLE = 2;
    /** Konstante zur besseren Lesbarkeit */
    public static final int HARD = 3;
    /** Konstante zur besseren Lesbarkeit */
    public static final int SUPER_HARD = 4;

    /** Flagge die angibt, ob der 50:50-Joker bereits verwendet wurde */
    static boolean fiftyfiftyJokerUsed = false;
    /** Flagge die angibt, ob der Entenjoker bereits verwendet wurde*/
    static boolean duckJokerUsed = false;
    
    /** Hält den Fragen pool, aus dem wir unsere Fragen auswählen können */
    static A34_Question[][] question_pool = new A34_Question[5][];

    /** Der Scanner für die Eingabe */
    public static Scanner scanner;

    /**
     * Die ganz tolle Main-Methode
     * @param args
     */
    public static void main(String[] args) {
        setQuestionPool();
        System.out.println("Herzlich willkomen bei \"Wer Wird Entionär?\"");
        System.out.println("Du musst nun 5 Fragen nacheinander richtig beantworten.");
        System.out.println("Du besitzt zwei verschiedene Joker. Den 50/50 Joker und den Enten-Joker.\nDu kannst das Spiel durch 'Q' beenden!");
        Random rnd = new Random();
        scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            // Wir fragen eine zufällige Frage aus diesem Schwierigkeitsgrad
            A34_Question question = question_pool[i][rnd.nextInt(2)];
            System.out.println("Uuuund hier nun die " + (i+1) + "-te Frage:\n");
            askQuestion(question,i);
        }

        // Schön immer den Scanner schließen!
        if(scanner != null)
            scanner.close();
    }

    /**
     * Hilfsmethode die das Stellen einer einzelnen Frage verwaltet
     * 
     * @param question die zu stellende Frage
     * 
     * @param level die Schwierigkeit der Frage für die Gunst des Enten-Jokers
     */
    public static void askQuestion(A34_Question question, int level){
        System.out.println(question); // Gib die Frage aus
        
        displayJokers();

        for(;;) {
            char input = readInput();
            if(handleInput(input,level,question))
                break; 
        }
    }

    /**
     * Liefert das nächste Zeichen der Eingabe des Nutzers.
     * Verschlingt eine gesamte Zeile
     * 
     * @return das nächste Zeichen.
     */
    private static char readInput(){
        System.out.print("\nDeine Eingabe> "); // Die 'Prompt'
        String input = scanner.nextLine();
        System.out.println("--------"); // Line for Optics
        return input.toUpperCase().charAt(0);
    }

    /**
     * Verarbeite die Nutzereingabe
     * 
     * @param input das eingegebene Zeichen
     * @param level die Schwierigkeit der Frage
     * @param question die Frage selbst
     * 
     * @return false, wenn die Eingabe ungültig war und wieder
     *         gefragt werden muss
     */
    public static boolean handleInput(char input, int level, A34_Question question){
        switch (input) {
            // Antwort
            case 'A': case 'B':
            case 'C': case 'D':
                int guess = input - 'A'; // Erhalte index der Eingabe
                if (question.isCorrect(guess)) {
                    correctAnswer(level);
                } else {
                    lostGame();
                }
                break;
            case 'E': // Enten Joker
                return duckJoker(level,question);
            case 'F': // 50:50 joker
                return fiftyfiftyJoker(question);
            case 'Q':
                System.out.println("Beende Spiel. Quack.");
                System.exit(0);
            default:
                System.out.println("Die Eingabe '" + input + "' ist mir leider unbekannt.");
                return false;
        }
        return true;
    }

    /**
     * Es handelt sich um eine richtige Antwort.
     * Wenn wir in der letzten Runde sind, ist das der
     * Sieg und das Spiel ist zuenede.
     * 
     * @param number
     */
    public static void correctAnswer(int turn){
        System.out.println("\nDas ist RICHTIG!");
        if(turn == 4){
            wonGame();
        }
    }

    /**
     * Beende das Spiel mit einem Sieg
     */
    public static void wonGame(){
        System.out.println("\nGlückwunsch! Du hast gewonnen!");
        System.exit(0);
    }

    /**
     * Beende das Spiel mit einer Niederlage
     */
    public static void lostGame(){
        System.out.println("Du hast leider verloren :/");
        System.exit(1);
    }

    /**
     * Zeige die noch zur Verfügung stehenden Joker
     * zusammen mit dem Tastenkürzel für sie an.
     */
    private static void displayJokers() {
        System.out.println("--------\nDu hast noch die folgenden Joker:");
        if (!duckJokerUsed) 
            System.out.println("  [E] Der Enten-Joker");
        if (!fiftyfiftyJokerUsed)
            System.out.println("  [F] Der 50:50-Joker");
    }

    /**
     * Wenet den 50:50 Joker an
     * 
     * @param question die Frage für die er angwendet werden soll
     * @return true, wenn er angwendet wurde, false wenn er schon verbraucht ist
     */
    private static boolean fiftyfiftyJoker(A34_Question question){
        if(fiftyfiftyJokerUsed)
            return false; // Schon verwendet

        System.out.println("Wir streichen zwei falsche Antworten für dich!");
        excludeAnswers(question,2);
        fiftyfiftyJokerUsed = true;
        displayJokers();
        return false;
    }

    /**
     * Der Enten-Joker
     * 
     * @param probability Die Runde in der wir uns befinden
     * @param question Die Frage auf die der Joker angewendet werden soll.
     * @return
     */
    private static boolean duckJoker(int probability, A34_Question question){
        if(duckJokerUsed)
            return false; // Schon verwendet
        if(Math.random() < 0.10*probability){
            System.out.println("Quack Quack ich weiß es!");
            System.out.println("Die richtige Antwort ist: " +((char)('A'+question.getCorrectAnswer())));
        }
        else{
            System.out.println("Quack ne tut mir leid, ich weiß es nicht.");
            excludeAnswers(question,1);
        }
        duckJokerUsed = true;
        displayJokers();
        return false; 
    }

    /**
     * Schließe (mehrere) falsche Antworten nach dem Zufallsprinzip aus
     * 
     * @param question die Frage um die es sich dreht
     * @param amount die Anzahl der auszuschließenden Antworten
     */
    private static void excludeAnswers(A34_Question question,int amount){
        int excludedQuestions = 0;
        while (excludedQuestions < amount){
            int index = (int) (Math.random()*question.getAnswers().length);
            // Wenn Auswahl nicht korrekt und noch nicht gestrichen:
            if(!question.isCorrect(index) && question.getAnswers()[index] != null){
                System.out.println(" - Die Antwort [" + (char)('A' + index) + "] ist falsch!");
                question.disableAnswer(index);
                excludedQuestions++;
            }
        }
    }



    // Fragen


    /**
     * Beispielhafte Funktion, die zeigt wie man die
     * Fragen füllen kann. Kann auch aus einer Datei
     * geladen werden oder so.
     */
    public static void setQuestionPool() {
        question_pool[SUPER_EASY] = new A34_Question[2];
        /* Super Einfache Fragen */
            question_pool[SUPER_EASY][0] = new A34_Question(
                "Sind Enten knuffig?", 
                new String[] {
                    "Aber natürlich",        /* Antwort A */
                    "Niemals",               /* Antwort B */
                    "Nur Freitag",           /* Antwort C */
                    "Hat wer Timo gesehen?", /* Antwort D */
                }, A34_Question.ANSWER_A
            );
            question_pool[SUPER_EASY][1] = new A34_Question(
                "Was ist 6+5-2?", 
                new String[] {
                    "7",        /* Antwort A */
                    "8",        /* Antwort B */
                    "9",        /* Antwort C */
                    "13",       /* Antwort D */
                }, A34_Question.ANSWER_C
            );
        
        question_pool[EASY] = new A34_Question[2];
        /* Einfache Fragen, weil ich faul bin die gleichen :D */
            question_pool[EASY][0] = new A34_Question(
                "Sind Enten knuffig?", 
                new String[] {
                    "Aber natürlich",        /* Antwort A */
                    "Niemals",               /* Antwort B */
                    "Nur Freitag",           /* Antwort C */
                    "Hat wer Timo gesehen?", /* Antwort D */
                }, A34_Question.ANSWER_A
            );
            question_pool[EASY][1] = new A34_Question(
                "Was ist 6+5-2?", 
                new String[] {
                    "7",        /* Antwort A */
                    "8",        /* Antwort B */
                    "9",        /* Antwort C */
                    "13",       /* Antwort D */
                }, A34_Question.ANSWER_C
            );
        
        question_pool[MIDDLE] = new A34_Question[2];
        /* Mittlere Fragen, weil ich faul bin die gleichen :D */
            question_pool[MIDDLE][0] = new A34_Question(
                "Sind Enten supa dupa knuffig?", 
                new String[] {
                    "Aber natürlich",        /* Antwort A */
                    "Niemals",               /* Antwort B */
                    "Nur Freitag",           /* Antwort C */
                    "Hat wer Timo gesehen?", /* Antwort D */
                }, A34_Question.ANSWER_A
            );
            question_pool[MIDDLE][1] = new A34_Question(
                "Was ist -1+6+5-1?", 
                new String[] {
                    "7",        /* Antwort A */
                    "8",        /* Antwort B */
                    "9",        /* Antwort C */
                    "13",       /* Antwort D */
                }, A34_Question.ANSWER_C
            );
        
        question_pool[HARD] = new A34_Question[2];
        /* Schwere Fragen, weil ich faul bin die gleichen :D */
            question_pool[HARD][0] = new A34_Question(
                "Sind Enten supa dupa knuffig?", 
                new String[] {
                    "Aber natürlich",        /* Antwort A */
                    "Niemals",               /* Antwort B */
                    "Nur Freitag",           /* Antwort C */
                    "Hat wer Timo gesehen?", /* Antwort D */
                }, A34_Question.ANSWER_A
            );

            question_pool[HARD][1] = new A34_Question(
                "Was ist -1+6+5-1?", 
                new String[] {
                    "7",        /* Antwort A */
                    "8",        /* Antwort B */
                    "9",        /* Antwort C */
                    "13",       /* Antwort D */
                }, A34_Question.ANSWER_C
            );
        
        question_pool[SUPER_HARD] = new A34_Question[2];
        /* Schwere Fragen, weil ich faul bin die gleichen :D */
            question_pool[SUPER_HARD][0] = new A34_Question(
                "Sind Enten supa dupa knuffig?", 
                new String[] {
                    "Aber natürlich",        /* Antwort A */
                    "Niemals",               /* Antwort B */
                    "Nur Freitag",           /* Antwort C */
                    "Hat wer Timo gesehen?", /* Antwort D */
                }, A34_Question.ANSWER_A
            );

            question_pool[SUPER_HARD][1] = new A34_Question(
                "Was ist -1+6+5-1?", 
                new String[] {
                    "7",        /* Antwort A */
                    "8",        /* Antwort B */
                    "9",        /* Antwort C */
                    "13",       /* Antwort D */
                }, A34_Question.ANSWER_C
            );
    }
}

/**
 * Aufgabe 3.4 - Wer Wird Entionär? 
 * 
 * [Hilfsklasse]
 * 
 * @author Nikolas Rank
 * @author Floran Sihler
 *
 * @version 1.0, 03 Jan 2020
 */
public class A34_Question {

    /** Konstante zur besseren Lesbarkeit */
    public static final int ANSWER_A = 0;
    /** Konstante zur besseren Lesbarkeit */
    public static final int ANSWER_B = 1;
    /** Konstante zur besseren Lesbarkeit */
    public static final int ANSWER_C = 2;
    /** Konstante zur besseren Lesbarkeit */
    public static final int ANSWER_D = 3;


    /** Die Frage selbst */
    private String questionText;
    /** Die Antwortmöglichkeiten */
    private String[] answers;
    /** Der Index der richtigen Antwort */
    private int correctAnswer;

    /**
     * Konstruiert eine neue Frage mit allen Optionen
     * 
     * @param questionText der Text der Frage. Wie zum Beispiel: "Was ist knuffig?"
     * @param answers das Array an Antworten. Jeweils ist nur der Antworttext von nöte
     * @param correctAnswer die richtige Antwort (der Index wird durch die Antworten gegeben)
     */
    public A34_Question(String questionText,String[] answers, int correctAnswer){
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Liefert den Fragetext
     * 
     * @return {@link #questionText}
     */
    private String getQuestionText(){
        return questionText;
    }

    /**
     * Liefert die Antwortmöglichkeiten
     * 
     * @return {@link #answers}
     */
    public String[] getAnswers(){
        return answers;
    }

    /**
     * Setzt die Antwort an Index 'index' auf
     * ungültig. Geht nur wenn die Antwort nicht
     * richtig ist!
     * 
     * @param index der Index der zu streichenden Antwortmöglichkeit
     */
    public void disableAnswer(int index){
        if(index != getCorrectAnswer())
            answers[index] = null;
    }

    /**
     * Ist die Antwort richtig?
     * 
     * @param guess die zu prüfende Antwort
     * 
     * @return true wenn richtig, sonst false
     */
    public boolean isCorrect(int guess){
        return guess == getCorrectAnswer();
    }

    /**
     * Liefert den Index der richtigen Antwortoption
     * 
     * @return der Index der richtigen Antwort in {@link #answers}
     */
    public int getCorrectAnswer(){
        return correctAnswer;
    }

    /**
     * To String, liefert die Frage in einer schönen Formatierung für die Ausgabe
     */
    public String toString(){
        String question = this.getQuestionText();
        String answers[] = this.getAnswers();

        String displayString = "";
        displayString += question+"\nAntwortmöglichkeiten:\n";

        for(int i = 0; i<answers.length; i++){
            if(answers[i] != null) {
                displayString += "  ["+(char) ('A' + i)+"] " + answers[i] + "\n";
            }
        }

        return displayString;
    }
}

/**
 * Aufgabe 3.8 - Ein Stammbaum für Enten
 * (Voraussetzung: Aufgabe 2.6 - Papa-Erpel und Mama-Ente)
 *
 * @author Felix Rieg
 * @author Floran Sihler
 * @author Anton Vlasjuk
 *
 * @version 1.1, 4 Jan 2019
 *
 */
public class A38_Stammbaum {
    
    /**
     * Gibt den Stammbaum der Ente in Form einer Zeichenkette aus.
     * (Diese Repräsentation ist sehr primitiv und kann entsprechend
     * angepasst werden, um besser auszusehen.)
     * 
     * @param duck die Ente von der der Stammbaum benötigt wird.
     * 
     */
    public static void printStammbaum(A38_Ente duck){
        A38_EntenListe baum = printStammbaumHelper(duck);

        //Repräsentation als Zeichenkette (Voraussetzung: Vollständiger Baum <-> jede Ente hat Vater und Mutter)
        int familysize = baum.size()+1;
        int tiefe = 0;
        for(int i = familysize; i > 1; i = i/2){
            ++tiefe;
        }
        int counter = 0;
        String out = "";
        //von oben nach unten durch die Generationen
        for (int i = tiefe-1; i >= 0; i--) {

            int amountOfPeopleInGeneration = (int) Math.pow(2, i);
            for (int j = 1; j <= amountOfPeopleInGeneration; j++) {
                out += " " + baum.removeLast().getName();
                ++counter;

                if(counter >= 2 && j != amountOfPeopleInGeneration || Math.pow(2, i) == 1 && j != amountOfPeopleInGeneration){
                    counter = 0;
                    out += ", ";
                }
            }
            out += "\n";
        }
        System.out.println("Stammbaum von " + duck.getName() + ":\n" + out);
    }

    /**
     * Hilfsmethode für {@link #printStammbaum()}, sodass eine Liste der
     * Mitglieder {@link A38_EntenListe} des Stammbaums gefüllt wird (mithilfe der Breitensuche).
     *
     * @param ente steht für das Wurzelelement im Baum (ab hier wird traversiert)
     * */
    public static A38_EntenListe printStammbaumHelper(A38_Ente ente) {
        // Liste aller verwandten 
        A38_EntenListe out = new A38_EntenListe();
        //Hilfsvariable für die Breitensuche
        A38_Ente e;
        //Warteschlange zur Realisierung der Breitensuche
        A38_EntenListe queue = new A38_EntenListe();

        //Füge Wurzel (Ente) hinzu, falls sie exisitiert
        if(ente != null)
            queue.append(ente);
        
        //Solange noch Enten im Binärbaum existieren
        while(!queue.isEmpty()){
            e = queue.removeFirst();
            //Aktion gemäß der Breitensuche (ersetzbar für ander Suchmuster)
            out.append(e);

            //Traversierung...
            if(e.getFather() != null) // Füge Vater zu: muss besucht werden
                queue.append(e.getFather());
            
            
            if(e.getMother() != null)
                queue.append(e.getMother());
            
            
        }

        return out; // Aus gewohnheitsgründen, weil musso!
    }

    /**
     * Bonusaufgabe: Gibt zu der jeweiligen Ente die Geschwister als Zeichen-
     *               kette an.
     * */
    public static void getSiblings(A38_Ente duck){
        A38_EntenListe geschwister = new A38_EntenListe();
        A38_Ente papa = duck.getFather();
        A38_Ente mama = duck.getMother();

        //Per se nur eins nötig, da vermutlich davon ausgegangen wird, dass es nur Kinder unter dem gleichen Paar gibt

        //Falls es mal Kinder mit einer anderen Frau gab °.°
        if(papa != null){
            //geht alle Kinde des Vaters durch
            A38_EntenListe kinder = papa.getChildren();
            for (int i = 0; i < kinder.size(); i++) {
                A38_Ente kind = kinder.get(i);
                if(kind != null && !geschwister.contains(kind)){
                    // nicht null und noch nicht hinzugefügt:
                    geschwister.append(kind);
                }
            }
        }

        // Bonusaufgabe: lagere diesen redundanten Aufruf für Mutter
        // und Vater in eine extra Methode aus.

        // Falls es mal Kinder mit einem anderen Mann gab °.° (schockiertes Quaaack!)
        if(mama != null){
            //geht alle Kinde der Mutter durch
            A38_EntenListe kinder = mama.getChildren();
            for (int i = 0; i < kinder.size(); i++) {
                A38_Ente kind = kinder.get(i);
                if(kind != null && !geschwister.contains(kind)){
                    // nicht null und noch nicht hinzugefügt:
                    geschwister.append(kind);
                }
            }
        }


        //Erzeugt einen String mit den Namen der Geschwister (getrennt mit einem ", ")
        String out = "";
        int anzahlGeschwister = geschwister.size();
        for (int i = 0; i < anzahlGeschwister; i++) {
            out += geschwister.get(i).getName();
            if(i != anzahlGeschwister) // shöne Kommasetzung :D
                 out += ", ";
        }
        System.out.println("Siblings of " + duck.getName() + ": " + out);
    }


    /**
     * Einfacher Test des Stammbaums (und der getSiblings()-Methode (Bonus))
     *
     * @param args werden ignoriert
     * */
    public static void main(String[] args) {
        A38_Ente peter = new A38_Ente("Peter", 70);
        A38_Ente erika = new A38_Ente("Erika", 73);

        A38_Ente wolfgang = new A38_Ente("Wolfgang", 80);
        A38_Ente rosalinde = new A38_Ente("Rosalinde", 75);

        A38_Ente dieter = new A38_Ente("Dieter", 35, peter, erika);
        A38_Ente jasmin = new A38_Ente("Jasmin", 38, wolfgang, rosalinde);
        A38_Ente florian = new A38_Ente("Florian", 18, dieter, jasmin);

        printStammbaum(florian);

        A38_Ente anton = new A38_Ente("Anton", 20, dieter, jasmin);
        A38_Ente maya = new A38_Ente("Maya", 14, dieter, rosalinde); // Da gabs krach hihi

        getSiblings(florian);
    }

}

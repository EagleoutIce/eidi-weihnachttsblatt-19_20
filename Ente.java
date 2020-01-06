import java.util.Arrays;

/**
 * Ente
 */
public class Ente {
    private String name;
    private int alter;
    private Ente mama=null;
    private Ente papa=null;

    // Ohne Eltern:
    public Ente(String name, int alter){
        this.name = name; this.alter = alter;
    }

    // Mit Eltern:
    public Ente(String name, int alter, Ente papa, Ente mama){
        this.name=name; this.alter=alter; this.papa=papa; this.mama=mama;
    }

    public static void main(String[] args) {
        Ente peter = new Ente("Peter", 9);
        Ente wolfgang = new Ente("Wolfgang", 9);
        Ente erika = new Ente("Erika", 9);
        Ente rosalinde = new Ente("Rosalinde", 9);
        Ente dieter = new Ente("Dieter", 5, peter, erika);
        Ente jasmin = new Ente("Jasmin", 7, wolfgang, rosalinde);
        Ente flo    = new Ente("Florian", 0, dieter, jasmin);
        System.out.println(dieter);
        System.out.println(jasmin);
        System.out.println(flo);

        System.out.println(Arrays.toString(dieter.getAncestors()));
        System.out.println("---");
        System.out.println(Arrays.toString(jasmin.getAncestors()));
        System.out.println("---");
        System.out.println(Arrays.toString(flo.getAncestors()));
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        if(this.papa == null)
        return String.format("Ich heiße %s und bin %d Jahre alt.", this.name, this.alter);
        else return String.format("Ich heiße %s, bin %d Jahre alt, meine Mutter heißt %s und mein Vater %s.", this.name, this.alter, this.mama.name, this.papa.name);
    }

    public String[] getAncestors(){
        String[] d_a = new String[2];
        String[] m_a = new String[2];

        if(papa != null){
            d_a = papa.getAncestors();
            m_a = mama.getAncestors();
            String[] my_ancestors = new String[2];
            my_ancestors[0] = d_a[0] + " " + m_a[0] + "\n"
                    + d_a[1] + " " + m_a[1];
            my_ancestors[1] = this.name;
            return my_ancestors;

        }
        return new String[] {"",this.name};

    }


}
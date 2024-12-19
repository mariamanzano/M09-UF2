package src;
public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private static final String[] JUGADORS = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo","Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBpapé"};

    public int getNgols() { return ngols; }

    public Futbolista() {
        this.ngols = 0;
        this.ntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Inici dels xuts --------------------");

        Futbolista[] futbolistas = new Futbolista[NUM_JUGADORS];

        for (int i = 0; i < NUM_JUGADORS; i++) {
            futbolistas[i] = new Futbolista();
            futbolistas[i].setName(JUGADORS[i]);
            futbolistas[i].start();
        }

        for (Futbolista futbolista : futbolistas) {
            try {
                futbolista.join();
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.println(JUGADORS[i] + " -> " + futbolistas[i].getNgols() + " gols");
        }
    }
}
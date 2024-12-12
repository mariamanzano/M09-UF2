public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error al fil " + nom);
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}

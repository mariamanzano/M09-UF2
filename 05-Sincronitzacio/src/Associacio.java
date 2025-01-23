public class Associacio {
    private int numSocis;
    private Soci[] socis;

    public Associacio(int numSocis) {
        this.numSocis = numSocis;
        Compte compte = Compte.getInstance();
        this.socis = new Soci[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci(compte);
        }
    }

    public void iniciaCompteTempsSocis() {
        for (Soci soci : socis) {
            soci.start();
        }
    }

    public void esperaPeriodesSocis() {
        for (Soci soci : socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancSocis() {
        System.out.printf("Saldo total: %.2f%n", Compte.getInstance().getSaldo());
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio(100); 
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodesSocis();
        associacio.mostraBalancSocis();
    }
}
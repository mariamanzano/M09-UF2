public class Compte {
    private float saldo;
    private static Compte instance;
    
    public synchronized float getSaldo() {
        return saldo;
    }

    public void setSaldo(float  saldo) {
        this.saldo = saldo;
    }

    private Compte() {}

    public synchronized static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public synchronized void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        saldo -= quantitat;
    }
}

public class Compte {
    private float saldo;
    private static Compte instance;
    
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float  saldo) {
        this.saldo = saldo;
    }

    private Compte() {}

    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public void retirar(float quantitat) {
        saldo -= quantitat;
    }
}

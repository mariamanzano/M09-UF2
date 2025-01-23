import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio;
    private int esperaMax;
    Random random;
    private int maxAnys;

    
    public Soci(Compte compte) {
        this.compte = compte;
        this.aportacio = 10f;
        this.esperaMax = 100;
        this.random = new Random();
        this.maxAnys = 10;
    }

    @Override
    public void run() {
        for (int any = 1; any <= maxAnys; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0) {
                    compte.ingressar(aportacio);
                } else {
                    compte.retirar(aportacio);
                }

                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}

import java.util.Random;

public class Assistent extends Thread {
    private final Esdeveniment esdeveniment;
    private final Random random;

    public Assistent(Esdeveniment esdeveniment, String nom) {
        super(nom);
        this.esdeveniment = esdeveniment;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));

                if (random.nextInt(100) < 70) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

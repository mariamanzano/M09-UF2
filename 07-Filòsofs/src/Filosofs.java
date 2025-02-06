import java.util.Random;

public class Filosofs extends Thread {
    public Forquilla forquillaDreta;
    public Forquilla forquillaEsquerra;
    private int gana;
    private Random random = new Random();

    public Filosofs(Forquilla forquillaDreta, Forquilla forquillaEsquerra, int gana, String nom) {
        super(nom);
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.gana = gana;
    }

    public void menja() {
        while (true) {
            try {
                if (forquillaEsquerra.agafar()) {
                    System.out.printf("%s agafa la forquilla esquerra %d%n", getName(), forquillaEsquerra.getNum());
                    if (forquillaDreta.agafar()) {
                        System.out.printf("%s agafa la forquilla dreta %d%n", getName(), forquillaDreta.getNum());
                        System.out.println(getName() + " menja");
                        Thread.sleep(random.nextInt(1000) + 1000);
                        System.out.printf("%s ha acabat de menjar%n", getName());
                        forquillaDreta.deixar();
                        forquillaEsquerra.deixar();
                        return;
                    } else {
                        System.out.printf("%s deixa la forquilla esquerra (%d) i espera (dreta ocupada)%n", 
                                        getName(), forquillaEsquerra.getNum());
                        forquillaEsquerra.deixar();
                        gana++;
                        System.out.printf("%s gana = %d%n", getName(), gana);
                        Thread.sleep(random.nextInt(500) + 500);
                    }
                } else {
                    Thread.sleep(random.nextInt(500) + 500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pensa() {
        try{
            Thread.sleep(random.nextInt(2000));
            System.out.println(getName() + " pensa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            menja();
            pensa();
        }
    }
}

import java.util.Random;

public class Filosof extends Thread {
    public final Forquilla forquillaDreta;
    public final Forquilla forquillaEsquerra;
    private int gana;
    private final Random random = new Random();
    private final boolean primerForquillaEsquerra;
    private final boolean primerForquillaDreta;

    public Filosof(int numComensal, Forquilla forquillaDreta, Forquilla forquillaEsquerra) {
        super("Comensal" + numComensal);
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.gana = 0;

        if (numComensal % 2 == 0) {
            this.primerForquillaEsquerra = true;
            this.primerForquillaDreta = false;
        } else {
            this.primerForquillaEsquerra = false;
            this.primerForquillaDreta = true;
        }
    }

    public boolean agafarForquilles() throws InterruptedException {
        while (true) {
            if (primerForquillaEsquerra) {
                if (agafaForquillaEsquerra()) {
                    if (agafaForquillaDreta()) {
                        return true;
                    } else {
                        forquillaEsquerra.deixar();
                    }
                }
            } else {
                if (agafaForquillaDreta()) {
                    if (agafaForquillaEsquerra()) {
                        return true;
                    } else {
                        forquillaDreta.deixar();
                    }
                }
            }

            gana++;
            System.out.printf("%s té gana (%d). Esperant...%n", getName(), gana);
            Thread.sleep(random.nextInt(500) + 500);
        }
    }

    public boolean agafaForquillaEsquerra() throws InterruptedException {
        forquillaEsquerra.agafar(Integer.parseInt(getName().replace("Comensal", "")));
        return true;
    }
    
    public boolean agafaForquillaDreta() throws InterruptedException {
        forquillaDreta.agafar(Integer.parseInt(getName().replace("Comensal", "")));
        return true;
    }

    public void deixarForquilles() {
        System.out.printf("%s deixa les forquilles %d i %d%n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());
        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
    }

    public void menja() throws InterruptedException {
        while (true) {
            if (!agafarForquilles()) {
                gana++;
                System.out.printf("%s té gana (%d). Esperant...%n", getName(), gana);
                Thread.sleep(random.nextInt(500) + 500);
            } else {
                gana = 0;
                System.out.printf("%s menja%n", getName());
                Thread.sleep(random.nextInt(1000) + 1000);
                System.out.printf("%s ha acabat de menjar%n", getName());
                deixarForquilles();
            } 
        }
    }

    public void pensa() {
        try {
            Thread.sleep(random.nextInt(1000) + 1000);
            System.out.printf("%s pensa gana: %d%n", getName(), gana);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                menja();
                pensa();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

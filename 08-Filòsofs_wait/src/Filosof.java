import java.util.Random;

public class Filosof extends Thread {
    public final Forquilla forquillaDreta;
    public final Forquilla forquillaEsquerra;
    private int gana;
    private final Random random = new Random();
    private final boolean primerForquillaEsquerra;

    public Filosof(int numComensal, Forquilla forquillaDreta, Forquilla forquillaEsquerra) {
        super("fil" + numComensal);
        this.gana = 0;

        if (numComensal % 2 == 0) {
            this.forquillaEsquerra = forquillaEsquerra;
            this.forquillaDreta = forquillaDreta;
            this.primerForquillaEsquerra = true;
        } else {
            this.forquillaEsquerra = forquillaDreta;
            this.forquillaDreta = forquillaEsquerra;
            this.primerForquillaEsquerra = false;
        }
    }

    public boolean agafarForquilles() throws InterruptedException {
        while (true) {
            if (primerForquillaEsquerra) {
                if (agafaForquillaEsquerra()) {
                    if (agafaForquillaDreta()) {
                        return true;
                    } else {
                        System.out.printf("Filòsof: %s deixa l'esquerra (%d) i espera (dreta ocupada) ", getName(), forquillaEsquerra.getNum());
                        forquillaEsquerra.deixar();
                    }
                }
            } else {
                if (agafaForquillaDreta()) {
                    if (agafaForquillaEsquerra()) {
                        return true;
                    } else {
                        System.out.printf("Filòsof: %s deixa la dreta (%d) i espera (esquerra ocupada) ", getName(), forquillaDreta.getNum());
                        forquillaDreta.deixar();
                    }
                }
            }

            gana++;
            System.out.printf("Filòsof: %s gana=%d%n", getName(), gana);
            Thread.sleep(random.nextInt(500) + 500);
        }
    }

    public boolean agafaForquillaEsquerra() throws InterruptedException {
        synchronized (forquillaEsquerra) {
            if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
                forquillaEsquerra.agafar(Integer.parseInt(getName().replace("fil", "")));
                System.out.printf("Filòsof: %s agafa la forquilla esquerra %d%n", getName(), forquillaEsquerra.getNum());
                return true;
            }
        }
        return false;
    }

    public boolean agafaForquillaDreta() throws InterruptedException {
        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                forquillaDreta.agafar(Integer.parseInt(getName().replace("fil", "")));
                System.out.printf("Filòsof: %s agafa la forquilla dreta %d%n", getName(), forquillaDreta.getNum());
                return true;
            }
        }
        return false;
    }

    public void deixarForquilles() {
        System.out.printf("Filòsof: %s deixa les forquilles (%d i %d)%n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());

        synchronized (forquillaEsquerra) {
            forquillaEsquerra.deixar();
            forquillaEsquerra.notifyAll();
        }

        synchronized (forquillaDreta) {
            forquillaDreta.deixar();
            forquillaDreta.notifyAll();
        }
    }

    public void menja() {
        try {
            if (!agafarForquilles()) {
                gana++;
                System.out.printf("Filòsof: %s gana=%d%n", getName(), gana);
                Thread.sleep(random.nextInt(500) + 500);
            } else {
                gana = 0;
                System.out.printf("Filòsof: %s menja%n", getName());
                Thread.sleep(random.nextInt(1000) + 1000);
                deixarForquilles();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pensa() {
        try {
            System.out.printf("Filòsof: %s pensant%n", getName());
            Thread.sleep(random.nextInt(1000) + 1000);
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

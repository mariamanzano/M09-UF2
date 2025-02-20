import java.util.Random;

public class Filosof extends Thread {
    public final Forquilla forquillaDreta;
    public final Forquilla forquillaEsquerra;
    private int gana;
    private int iniciGana;
    private int fiGana;
    private final Random random = new Random();

    public Filosof(int numComensal, Forquilla forquillaDreta, Forquilla forquillaEsquerra) {
        super("fil" + numComensal);
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.gana = 0;
        this.iniciGana = (int) (System.currentTimeMillis());
        this.fiGana = 0;
    }

    public int calcularGana() {
        return gana;
    }

    public void resetGana() {
        this.gana = 0;
        this.iniciGana = (int) (System.currentTimeMillis());         
    }

    public boolean agafarForquilles() throws InterruptedException {
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                return true;
            } else {
                deixarForquilles();
            }
        }
        return false;
    }

    public boolean agafaForquillaEsquerra() throws InterruptedException {
        return forquillaEsquerra.agafar();
    }

    public boolean agafaForquillaDreta() throws InterruptedException {
        return forquillaDreta.agafar();
    }

    public void deixarForquilles() {
        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
        System.out.printf("%s deixa les forquilles%n", getName());
    }

    public void menja() {
        while (true) {
            try {
                if (agafarForquilles()) {
                    fiGana = (int) (System.currentTimeMillis());
                    gana = (fiGana - iniciGana) / 1000;

                    System.out.printf("%s té forquilles esq(%d) dreta(%d)%n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());
                    System.out.printf("%s menja amb gana %d%n", getName(), gana);

                    Thread.sleep(random.nextInt(1000) + 1000);

                    System.out.printf("%s ha acabat de menjar%n", getName());
                    deixarForquilles();
                    resetGana();
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(random.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pensa() {
        try {
            resetGana();
            System.out.printf("%s pensant%n", getName());
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

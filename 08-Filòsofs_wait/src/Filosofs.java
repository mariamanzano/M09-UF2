import java.util.Random;

public class Filosofs extends Thread {
    public Forquilla forquillaDreta;
    public Forquilla forquillaEsquerra;
    private int gana;
    private Random random = new Random();

    public Filosofs(int numComensal, Forquilla forquillaDreta, Forquilla forquillaEsquerra) {
        super("Comensal" + numComensal);
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.gana = 0;
    }

    public boolean agafarForquilles() throws InterruptedException {
        int idFilosof = Integer.parseInt(getName().replace("Comensal", ""));
        if (idFilosof % 2 == 0) {
            if (agafaForquillaEsquerra() && agafaForquillaDreta()) {
                return true;
            }
        } else {
            if (agafaForquillaDreta() && agafaForquillaEsquerra()) {
                return true;
            }
        }
        return false;
    }
    

    public boolean agafaForquillaEsquerra() throws InterruptedException {
        int idFilosof = Integer.parseInt(getName().replace("Comensal", ""));
        return forquillaEsquerra.agafar(idFilosof);
    }
    
    public boolean agafaForquillaDreta() throws InterruptedException {
        int idFilosof = Integer.parseInt(getName().replace("Comensal", ""));
        return forquillaDreta.agafar(idFilosof);
    }
    

    public void deixarForquilles() {
        System.out.printf("%s deixa les forquilles %d i %d%n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());
        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
    }

    public void menja() {
        try {
            agafarForquilles();
            System.out.printf("%s menja%n", getName());
            Thread.sleep(random.nextInt(1000) + 1000);
            System.out.printf("%s ha acabat de menjar%n", getName());
            deixarForquilles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pensa() {
        try {
            Thread.sleep(random.nextInt(1000) + 1000);
            System.out.printf("%s pensa%n", getName());
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

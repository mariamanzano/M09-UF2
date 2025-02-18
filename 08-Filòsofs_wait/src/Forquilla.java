public class Forquilla {
    private int propietari;
    private final int num;

    public static final int LLIURE = -1;

    public Forquilla(int num) {
        this.num = num;
        this.propietari = LLIURE;
    }

    public synchronized void agafar(int idFilosof) throws InterruptedException {
        while (this.propietari != LLIURE) {
            wait();
        }
        this.propietari = idFilosof;
        System.out.printf("Filòsof %d agafa la forquilla %d%n", idFilosof, num);
    }

    public synchronized void deixar() {
        System.out.printf("Filòsof %d deixa la forquilla %d%n", propietari, num);
        this.propietari = LLIURE;
        notifyAll();
    }

    public int getNum() {
        return num;
    }

    public int getPropietari() {
        return propietari;
    }
}

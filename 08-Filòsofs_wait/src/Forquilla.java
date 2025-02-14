public class Forquilla {
    private int propietari;
    private final int num;

    public static final int LLIURE = -1;

    public Forquilla(int num) {
        this.num = num;
        this.propietari = LLIURE;
    }

    public synchronized boolean agafar(int idFilosof) throws InterruptedException {
        while (propietari != LLIURE) {
            wait();
        }
        propietari = idFilosof;
        System.out.printf("Filòsof %d agafa la forquilla %d%n", idFilosof, num);
        return true;
    }

    public synchronized void deixar() {
        System.out.printf("Filòsof %d deixa la forquilla %d%n", propietari, num);
        propietari = LLIURE;
        notifyAll();
    }

    public int getNum() {
        return num;
    }

    public int getPropietari() {
        return propietari;
    }
}

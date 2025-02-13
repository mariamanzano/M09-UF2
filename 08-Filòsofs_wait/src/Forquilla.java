public class Forquilla {
    private int propietari;
    private final int num;
    private final Object monitor = new Object();

    public static final int LLIURE = -1;

    public Forquilla(int num) {
        this.num = num;
        this.propietari = LLIURE;
    }

    public boolean agafar(int idFilosof) throws InterruptedException {
        synchronized (monitor) {
            while (propietari != LLIURE) {
                monitor.wait();
            }
            propietari = idFilosof;
            System.out.printf("Filòsof %d agafa la forquilla %d%n", idFilosof, num);
            return true;
        }
    }

    public void deixar() {
        synchronized (monitor) {
            propietari = LLIURE;
            monitor.notifyAll();
            System.out.printf("Filòsof %d deixa la forquilla %d%n", propietari, num);
        }
    }

    public int getNum() {
        return num;
    }

    public int getPropietari() {
        return propietari;
    }
}

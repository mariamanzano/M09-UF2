import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    public boolean tabac;
    public boolean llumi;
    public boolean paper;
    private int numFumades;

    public Fumador(int id, Estanc estanc) {
        this.id = id;
        this.estanc = estanc;
        this.tabac = false;
        this.llumi = false;
        this.paper = false;
        this.numFumades = 0;
    }

    public void fuma() {
        if (tabac && llumi && paper) {
            System.out.println("Fumador " + id + " fumant");
            tabac = false;
            llumi = false;
            paper = false;
            numFumades++;        
            Random rand = new Random();            
            try {
                Thread.sleep(500 + rand.nextInt(501));  // Random sleep between 0.5 and 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
        }
    }

    public synchronized void compraTabac() {
        if (estanc.teTabac()) {
            estanc.venTabac();
            tabac = true;
            System.out.println("Fumador " + id + " ha comprat tabac");
        }
    }

    public synchronized void compraPaper() {
        if (estanc.tePaper()) {
            estanc.venPaper();
            paper = true;
            System.out.println("Fumador " + id + " ha comprat paper");
        }
    }

    public synchronized void compraLlumi() {
        if (estanc.teLlumi()) {
            estanc.venLlumi();
            llumi = true;
            System.out.println("Fumador " + id + " ha comprat llumi");
        }
    }

    @Override
    public void run() {
        while (numFumades < 3) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
        System.out.println("Fumador " + id + " ha acabat de fumar");
    }
}

import java.util.Random;
import java.util.ArrayList;

class Estanc extends Thread {
    private final ArrayList<Tabac> tabac;
    private final ArrayList<Paper> paper;
    private final ArrayList<Llumi> llumins;
    public boolean obert;
    private final Random random;

    public Estanc() {
        tabac = new ArrayList<>();
        paper = new ArrayList<>();
        llumins = new ArrayList<>();
        obert = true;
        random = new Random();
    }

    public synchronized boolean teTabac() {return !tabac.isEmpty();}

    public synchronized boolean tePaper() {return !paper.isEmpty();}

    public synchronized boolean teLlumi() {return !llumins.isEmpty();}

    public synchronized void nouSubministrament() {
        int tipus = random.nextInt(3);
        switch (tipus) {
            case 0:
                addTabac();
                System.out.println("Afegint Tabac");
                break;
            case 1:
                addPaper();
                System.out.println("Afegint Paper");
                break;
            case 2:
                addLlumi();
                System.out.println("Afegint Llumi");
                break;
        }
        notifyAll();
    }

    public synchronized void addTabac() {tabac.add(new Tabac());}

    public synchronized void addPaper() {paper.add(new Paper());}

    public synchronized void addLlumi() {llumins.add(new Llumi());}

    public synchronized Tabac venTabac() {
        while (tabac.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tabac.isEmpty() ? null : tabac.remove(0);
    }

    public synchronized Paper venPaper() {
        while (paper.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return paper.isEmpty() ? null : paper.remove(0);
    }

    public synchronized Llumi venLlumi() {
        while (llumins.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return llumins.isEmpty() ? null : llumins.remove(0);
    }

    public void tancarEstanc() {
        obert = false;
        System.out.println("Estanc tancat");
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            try {
                nouSubministrament();
                Thread.sleep(500 + random.nextInt(1001));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

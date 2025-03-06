import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int cadires;
    private final Object condBarber = new Object();
    private static Barberia barberia;

    public Barberia(int cadires) {
        this.cadires = cadires;
        this.salaEspera = new LinkedList<>();
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }

    public void entrarClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < cadires) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }
            Thread.sleep(10000);
            for (int i = 11; i <= 20; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        barberia = new Barberia(3);
        Barber barber = new Barber("Pepe", barberia);
        barber.start();
        barberia.start();
    }
}

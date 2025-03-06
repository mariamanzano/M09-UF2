public class Barber extends Thread {
    private String nom;
    private Barberia barberia;

    public Barber(String nom, Barberia barberia) {
        this.nom = nom;
        this.barberia = barberia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() { 
        while (true) {
            synchronized (barberia.getCondBarber()) {
                Client client = barberia.seguentClient();
                if (client != null) {
                    System.out.println("Li toca al client " + client.getNom());
                    client.tallarseElCabell();
                } else {
                    try {
                        System.out.println("Ningú en espera");
                        System.out.println("Barber " + nom + " dormint");
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
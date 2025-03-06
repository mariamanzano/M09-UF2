public class Client {
    private int id;
    private String nom;

    public Client(int id) { 
        this.id = id;
        this.nom = "Client-" + id;
    }

    public void tallarseElCabell() {
        try {
            Thread.sleep(900 + (int) (Math.random() * 100));
            System.out.println("Tallant el cabell de " + nom);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}

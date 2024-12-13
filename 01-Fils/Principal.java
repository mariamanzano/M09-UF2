public class Principal {
    public static void main(String[] args) {
        Fil Juan = new Fil("Juan");
        Fil Pepe = new Fil("Pepe");

        Juan.start();

        System.out.println("Termina thread main");

        try {
            Juan.join();
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }

        Pepe.start();

        try {
            Pepe.join();
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }

        System.out.println("Termina el fil Juan");
        System.out.println("Termina el fil Pepe");

    }
}
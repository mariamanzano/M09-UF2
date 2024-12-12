public class Principal {
    public static void main(String[] args) {
        Fil Juan = new Fil("Juan");
        Fil Pepe = new Fil("Pepe");

        Juan.setPriority(Thread.MIN_PRIORITY);
        Pepe.setPriority(Thread.MAX_PRIORITY);

        Juan.start();
        Pepe.start();

        System.out.println("Termina thread main");
    }
}
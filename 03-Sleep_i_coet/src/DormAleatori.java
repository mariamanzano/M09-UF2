public class DormAleatori extends Thread {

    private String name;

    private long tempsInici;

    public DormAleatori(String name) {
        this.name = name;
        this.tempsInici = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int intervalAleatori = (int) (Math.random() * 1000);
            long tempsPassat = System.currentTimeMillis() - tempsInici;
            System.out.println(String.format("%s(%d) a dormir %dms total %dms",name, i, intervalAleatori, tempsPassat));            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
    }

	public static void main(String[] args) {
        DormAleatori Joan = new DormAleatori("Joan");
        DormAleatori Pep = new DormAleatori("Pep");
        Joan.start();
        Pep.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
        System.out.println("-- Fi de main -----------");
    }
}
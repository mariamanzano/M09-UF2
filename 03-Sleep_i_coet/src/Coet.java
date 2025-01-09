public class Coet {

    private Motor[] motors = new Motor[4];

    public Coet() {
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor();
        }
    }

    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            for (int i = 0; i < motors.length; i++) {
                motors[i].setPotenciaObj(p);
            }
        } else {
            System.out.println("El valor de la potencia ha de ser entre 0 i 10");
        }
    }    

    public void arrenca() {
        for (int i = 0; i < motors.length; i++) {
            motors[i].start();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();

        coet.arrenca();

        int potencia = Integer.parseInt(Entrada.readLine());
        coet.passaAPotencia(potencia);
        coet.arrenca();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
        coet.passaAPotencia(0);
    }
}

public class Coet {
    private Motor[] motors = new Motor[4];

    public Coet() {
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            System.out.println("Passant a potència: " + p);

            for (int i = 0; i < motors.length; i++) {
                motors[i].setPotenciaObj(p);
            }

            for (int i = 0; i < motors.length; i++) {
                try {
                    motors[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("El valor de la potència ha de ser entre 0 i 10");
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        int potencia = -1;
        while (potencia != 0) {
            try {
                potencia = Integer.parseInt(Entrada.readLine());
                coet.passaAPotencia(potencia);
            } catch (NumberFormatException e) {
            
            }
        }
    }
}

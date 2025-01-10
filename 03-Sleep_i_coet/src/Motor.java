public class Motor extends Thread {
    private int potenciaObj = 0;
    private int potenciaAct = 0;
    private int id;

    public Motor(int id) {
        this.id = id;  // Inicialitzem el id
    }

    public int getPotenciaObj() {
        return potenciaObj;
    }

    public void setPotenciaObj(int p) {
        if (p < 0 || p > 10) {
            System.out.printf("La potència ha de ser entre 0 i 10.\n");
            return;
        }

        if (p == 0 && potenciaAct > 0) {
            while (potenciaAct > 0) {
                potenciaAct--;
                System.out.printf("Motor %d: Decre. Objectiu: 0 Actual: %d\n", id, potenciaAct);
                try {
                    int temps = (int) (Math.random() * 1001) + 1000;
                    Thread.sleep(temps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("Motor %d: Aturat. Objectiu: 0 Actual: %d\n", id, potenciaAct);
            return;
        }

        this.potenciaObj = p;
        start();
    }

    @Override
    public void run() {
        while (potenciaAct != potenciaObj) {
            if (potenciaAct < potenciaObj) {
                potenciaAct++;
                System.out.printf("Motor %d: Incre. Objectiu: %d Actual: %d\n", id, potenciaObj, potenciaAct);
            } else {
                potenciaAct--;
                System.out.printf("Motor %d: Decre. Objectiu: %d Actual: %d\n", id, potenciaObj, potenciaAct);
            }

            try {
                int temps = (int) (Math.random() * 1001) + 1000;
                Thread.sleep(temps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Motor %d: FerRes. Objectiu: %d Actual: %d\n", id, potenciaObj, potenciaAct);
    }
}

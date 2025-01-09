public class Motor extends Thread {
    private int potenciaObj = 0;
    private int potenciaAct = 0;

    public int getPotenciaObj() {
        return potenciaObj;
    }

    public void setPotenciaObj(int p) {
        if (p == 0) {
            while (potenciaAct > 0) {
                potenciaAct--;
                int temps = (int) (Math.random() * 1001) + 1000;
                try {
                    Thread.sleep(temps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        this.potenciaObj = p;
        start();
    }

    @Override
    public void run() {
        if (potenciaAct == 0) {
            while (potenciaAct > 0) {
                potenciaAct--;
                System.out.println("Motor " + this.getName() + " : Decre. Objectiu: 0 Actual: " + potenciaAct);
                int temps = (int) (Math.random() * 1001) + 1000;
                try {
                    Thread.sleep(temps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Motor " + this.getName() + ": FerRes Objectiu: 0 Actual: " + potenciaAct);
        }

        while (potenciaAct != potenciaObj) {
            if (potenciaAct < potenciaObj) {
                potenciaAct++;
                System.out.println("Motor " + this.getName() + ": Incre. Objectiu: " + potenciaObj + " Actual: " + potenciaAct);

            } else {
                potenciaAct--;
                System.out.println("Motor " + this.getName() + ": Decre. Objectiu: " + potenciaObj + " Actual: " + potenciaAct);

            }
            int temps = (int) (Math.random() * 1001) + 1000;
            try {
                Thread.sleep(temps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        fumadors = new Fumador[3];
        estanc = new Estanc();

        for (int i = 0; i < fumadors.length; i++) {
            fumadors[i] = new Fumador(i, estanc);
        }
        
        estanc.start(); 
    }

    public void esperarFumadors() {
        try {
            for (Fumador fumador : fumadors) {
                fumador.start();
            }
            for (Fumador fumador : fumadors) {
                fumador.join();
            }
            estanc.tancarEstanc();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.esperarFumadors();
    }
}

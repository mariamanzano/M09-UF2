public class Taula {
    private Filosofs[] comensals;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        comensals = new Filosofs[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            comensals[i] = new Filosofs(i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
        }
    }

    public void showTaula() {
        for (Filosofs comensal : comensals) {
            System.out.printf("Comensal: %s esq: %d dret: %d%n", comensal.getName(), comensal.forquillaEsquerra.getNum(), comensal.forquillaDreta.getNum());
        }
    }

    public void cridarATaula() {
        for (Filosofs comensal : comensals) {
            comensal.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}

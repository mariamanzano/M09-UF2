import java.util.Random;

public class Treballador extends Thread {
    
    private float nou_anual_brut;

    private int edat_inici_treball;

    private int edat_final_treball;

    private int edat_actual = 0;

    private float cobrat = 0.0f;

    private Random rnd;

    public int getEdat() { return edat_actual;}

    public float getCobrat() { return cobrat;}

    public Treballador(float nou_anual_brut, int edat_inici_treball, int edat_final_treball, String fil) {
        super(fil);
        this.nou_anual_brut = nou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_final_treball = edat_final_treball;
        this.edat_actual = edat_actual;
        this.cobrat = 0.0f;
        this.rnd = new Random(); 
    }

    public void cobra() {
        float souMensual = nou_anual_brut / 12.0f;
        cobrat += souMensual;
        pagaImpostos(souMensual);
        try {
            Thread.sleep(rnd.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void pagaImpostos(float souMensual) {
        cobrat -= souMensual * 0.24f;
        try {
            Thread.sleep(rnd.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int totalMes = (edat_final_treball - edat_inici_treball)*12;
        for (int i = 0; i < totalMes; i++) {
            cobra();
        }
        edat_actual = edat_inici_treball + (totalMes / 12);
    }    
}

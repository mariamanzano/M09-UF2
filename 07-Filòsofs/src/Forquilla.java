public class Forquilla {
    private boolean enUs;
    private int num;
    
    public boolean enUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Forquilla(int num) {
        this.enUs = false;
        this.num = num;
    }

    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public void deixar() {
        enUs = false;
    }
}
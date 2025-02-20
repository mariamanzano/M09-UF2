import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private final int num;
    private final ReentrantLock lock;

    public Forquilla(int num) {
        this.num = num;
        this.lock = new ReentrantLock();
    }

    public boolean agafar() throws InterruptedException {
        return lock.tryLock();
    }

    public void deixar() {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}

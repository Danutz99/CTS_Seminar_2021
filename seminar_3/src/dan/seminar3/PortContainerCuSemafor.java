package dan.seminar3;

import java.util.concurrent.Semaphore;

public class PortContainerCuSemafor extends PortContainer{
    private Semaphore operare;
    private int nrPermisiuni;

    public PortContainerCuSemafor(char[] eticheta, TipContainer[] tipContainer, int[] nrContainere, int nrPermisiuni) {
        super(eticheta, tipContainer, nrContainere);
        this.nrPermisiuni = nrPermisiuni;
        this.operare = new Semaphore(nrPermisiuni);
    }

    public PortContainerCuSemafor( int nrPermisiuni) {
        this.nrPermisiuni = nrPermisiuni;
        this.operare = new Semaphore(nrPermisiuni);
    }

    public synchronized boolean isOperare() {
        return this.operare.tryAcquire();
    }

    public synchronized void obtinereOperare() {
        try {
            this.operare.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void elibereazaOperare() {
        this.operare.release();
    }
}

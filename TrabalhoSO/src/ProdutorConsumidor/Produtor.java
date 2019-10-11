package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Produtor extends Thread {
    private int thread;
    private int valor;
    private Semaphore preencher;
    private Semaphore esvaziar;
    private BufferDados buffer;
    private Lock mutex;

    public Produtor(int thread, BufferDados buffer, Semaphore preencher, Semaphore esvaziar, Lock mutex) {
        this.thread = thread;
        this.buffer = buffer;
        this.preencher = preencher;
        this.esvaziar = esvaziar;
        this.mutex = mutex;
    }

    public void put() {
        try {
            valor = (int)(Math.random() * 20 + 1);
            System.out.println("Thread Produtor #" + thread + " colocando valor " + valor + " no buffer...");
            buffer.adicionarValorNoBuffer(valor);
            Thread.sleep((long)(Math.random() * 250));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                // Proberen (Decrementar)
                esvaziar.acquire();
                mutex.lock();
                put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock();
                // Verhogen (Incrementar)
                preencher.release();
            }
        }
    }
}

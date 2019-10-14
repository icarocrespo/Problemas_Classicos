package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Produtor extends Thread {
    private Integer n_thread;
    private Integer valor;
    private Lock mutex;
    private Semaphore coloca;
    private Semaphore tira;
    private Buffer buffer;
    

    public Produtor(Integer n_thread, Buffer buffer, Semaphore coloca, Semaphore tira, Lock mutex) {
        this.n_thread = n_thread;
        this.buffer = buffer;
        this.coloca = coloca;
        this.tira = tira;
        this.mutex = mutex;
    }

    public void put() {
        try {
            valor = (int)(Math.random() * 20 + 1);
            System.out.println("Thread Produtor #" + n_thread + " colocando valor " + valor + " no buffer...");
            buffer.add(valor);
            Thread.sleep((long)(Math.random() * 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                tira.acquire();
                mutex.lock();
                put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock();
                coloca.release();
            }
        }
    }
}

package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Consumidor extends Thread {

    private Integer n_thread;
    private Integer valor;
    private Lock mutex;
    private Semaphore preencher;
    private Semaphore esvaziar;
    private Buffer buffer;

    public Consumidor(int n_thread, Buffer buffer, Semaphore preencher, Semaphore esvaziar, Lock mutex) {
        this.n_thread = n_thread;
        this.buffer = buffer;
        this.preencher = preencher;
        this.esvaziar = esvaziar;
        this.mutex = mutex;
    }
    
    public Consumidor(){

    }

    public void get() {
        try {
            valor = buffer.remove();
            System.out.println("Thread Consumidor #" + n_thread + " retirando valor " + valor + " do buffer");
            Thread.sleep((long) (Math.random() * 500));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                preencher.acquire();
                mutex.lock();
                get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock();
                esvaziar.release();
            }
        }
    }
}

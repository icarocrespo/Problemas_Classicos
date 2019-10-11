package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Consumidor extends Thread {
    private int thread;
    private int valor;
    private Semaphore preencher;
    private Semaphore esvaziar;
    private BufferDados buffer;
    private Lock mutex;

    public Consumidor(int idThread, BufferDados buffer, Semaphore  preencher, Semaphore esvaziar, Lock mutex) {
        this.thread = idThread;
        this.buffer = buffer;
        this. preencher =  preencher;
        this.esvaziar = esvaziar;
        this.mutex = mutex;
    }

    public void get() {
        try {
            valor = buffer.retirarValorDoBuffer();
            System.out.println("Thread Consumidor #" + thread + " retirando valor " + valor + " do buffer...");
            Thread.sleep((long)(Math.random() * 500));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                // Decrementar
                preencher.acquire();
                mutex.lock();
                get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.unlock();
                // Incrementar
                esvaziar.release();
            }
        }
    }
}

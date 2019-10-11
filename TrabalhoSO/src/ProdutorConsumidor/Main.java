package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int Preencher = 0;
        int Esvaziar = 20;
        BufferDados buffer = new BufferDados(Esvaziar);
        Semaphore preencher = new Semaphore(Preencher);
        Semaphore esvaziar = new Semaphore(Esvaziar);
        Lock mutex = new ReentrantLock();

        Produtor produtor = new Produtor(1, buffer, preencher, esvaziar, mutex);
        Consumidor consumidor = new Consumidor(1, buffer, preencher, esvaziar, mutex);

        produtor.start();
        consumidor.start();
    }
}


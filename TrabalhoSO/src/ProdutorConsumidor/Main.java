package ProdutorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Integer coloca = 0;
        Integer tira = 20;
        Buffer buffer = new Buffer(tira);
        Semaphore colocar = new Semaphore(coloca);
        Semaphore retirar = new Semaphore(tira);

        Lock mutex = new ReentrantLock();

        Produtor produtor = new Produtor(1, buffer, colocar, retirar, mutex);
        Consumidor consumidor = new Consumidor(1, buffer, colocar, retirar, mutex);

        produtor.start();
        consumidor.start();
    }
}

package JantarDosFilosofos2;

import JantarDosFilosofos2.SleepUtilities;

public class Philosopher implements Runnable{

    private int id;
    private PhilosophersSemaphore semaphore;
    private String name;

    public Philosopher(int id, PhilosophersSemaphore semaphore, String name) {
        super();
        this.id = id;
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        while(true){
            semaphore.takeForks(id);
            eating();
            semaphore.returnForks(id);
            thinking();
        }
    }

    private void thinking() {
        System.out.println(name + " está pensando");
        SleepUtilities.nap();
    }

    private void eating() {
        System.out.println(name + " está comendo");
        SleepUtilities.nap();
    }

}
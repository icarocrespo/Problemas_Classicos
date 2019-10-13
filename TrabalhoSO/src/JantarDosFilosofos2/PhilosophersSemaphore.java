package JantarDosFilosofos2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosophersSemaphore {
    private Lock lock = new ReentrantLock();
    enum State{THINKING, HUNGRY, EATING};
    State[] states = new State[5];
    Condition[] self = new Condition[5];
    private Semaphore sem = new Semaphore(1);

    public PhilosophersSemaphore(){
        for(int i = 0; i < 5; i++){
            states[i] = State.THINKING;
            self[i] = lock.newCondition();
        }
    }

    public void takeForks(int i){
        states[i] = State.HUNGRY;
        if((states[i]) != State.EATING){
            //lock.lock();
            try {
                sem.acquire();
                test(i);
                //synchronized(this.self[i]){self[i].wait();}
            } catch (InterruptedException e) {}
            finally{
                //lock.unlock();
            }
        }
    }

    public void returnForks(int i){
        states[i] = State.THINKING;
        test((i+4)%5);
        test((i+1)%5);
    }

    private void test(int i){
        if((states[(i+4)%5] != State.EATING) && (states[i] == State.HUNGRY)
                && (states[(i+1)%5]) != State.EATING) {
            states[i] = State.EATING;
            //self[i].signal();
            sem.release();
        }
    }
}

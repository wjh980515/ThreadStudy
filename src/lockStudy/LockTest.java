package lockStudy;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        LockTest2 lock = new LockTest2();
        new Thread(lock).start();
        new Thread(lock).start();
        new Thread(lock).start();
    }

}

class LockTest2 implements Runnable {

    int ticketNums = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticketNums > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(ticketNums--);
                }else {
                    break;
                }
            }finally {
                //解锁
                lock.unlock();
            }
        }
    }

}
package demo;

//买火车票的例子
public class RunnableDemo{

    public static void main(String[] args) {
        buyTicket station = new buyTicket();

        new Thread(station,"小明").start();
        new Thread(station,"老师").start();
        new Thread(station,"黄牛").start();
    }

}

class buyTicket implements Runnable {
    //票数
    private int tickNums = 10;
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {

            buy();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //买票方法
    private synchronized void buy() {
        if (tickNums <= 0) {
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName() + "--->拿到了第" + tickNums-- + "张票");
    }
}
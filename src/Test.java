public class Test{

    public static void main(String[] args) {
        Count count = new Count();
        new Thread(count,"111").start();
        new Thread(count,"222").start();
    }

}

class Count implements Runnable{

    int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + num++);
        }
    }
}
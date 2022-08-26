package demo3;

//守护线程
public class DaemonTest {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//守护线程

        thread.start();

        new Thread(you).start();
    }

}

class God implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑你");
        }
    }
}

class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("--开心地活着--");
        }
        System.out.println("=====Game over!=====");
    }
}

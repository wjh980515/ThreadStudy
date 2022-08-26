package demo;

public class RunnableStudy implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("写代码：" + i);
        }
    }

    public static void main(String[] args) {

        RunnableStudy runnableStudy = new RunnableStudy();
        new Thread(runnableStudy).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("学习多线程：" + i);
        }
    }

}

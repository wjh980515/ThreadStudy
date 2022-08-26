package demo;

public class ThreadStudy extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("子线程：" + i);
        }
    }

    public static void main(String[] args) {

        ThreadStudy threadStudy = new ThreadStudy();
        //threadStudy.start();
        threadStudy.run();

        for (int i = 0; i < 20; i++) {
            System.out.println("主线程：" + i);
        }
    }
}

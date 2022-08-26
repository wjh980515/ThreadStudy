package lockStudy;

public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0,"白雪公主");
        Makeup g2 = new Makeup(1,"灰姑娘");

        g1.start();
        g2.start();
    }
}

//口红
class Lipstick{

}
//镜子
class Mirror{

}

class Makeup extends Thread{

    //static表示资源唯一
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String name;

    Makeup(int choice,String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //化妆，互相持有对方想拥有的资源，需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice == 0 ){
            synchronized (lipstick) {//获得口红的锁
                System.out.println(this.name + "获得口红");
                Thread.sleep(1000);

            }
            synchronized (mirror) {//一秒之后获得镜子
                System.out.println(this.name + "获得镜子");
            }
        }else {
            synchronized (mirror) {//获得镜子的锁
                System.out.println(this.name + "获得镜子");
                Thread.sleep(2000);

            }
            synchronized (lipstick) {//一秒之后获得口红
                System.out.println(this.name + "获得口红");
            }
        }
    }
}
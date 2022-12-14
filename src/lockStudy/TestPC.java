package lockStudy;

//管程法 生产者消费者模型 -> 利用缓冲区解决
public class TestPC {

    public static void main(String[] args) {

        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();

    }

}

//生产者
class Productor extends Thread{

    SynContainer container;

    public Productor(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了" + i + "只鸡");
            container.push(new Chicken(i));
        }
    }
}
//消费者
class Consumer extends Thread{

    SynContainer container;

    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了->" + container.pop().id + "只鸡");
        }
    }

}
//产品
class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}
//缓冲区
class SynContainer{
    //需要一个容器
    Chicken[] chickens = new Chicken[10];
    int count = 0;
    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了需要消费者消费
        if (count == chickens.length){
            //通知消费者消费 生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果容器没满就让生产者放入
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }
    //消费者消费产品
    public synchronized Chicken pop(){
        if (count == 0) {
            //等待生产者生产 消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();
        //通知生产者生产
        return chicken;
    }
}
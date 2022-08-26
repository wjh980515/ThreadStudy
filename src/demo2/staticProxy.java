package demo2;

public class staticProxy {

    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.happyMarry();
    }

}

interface Marry{

    void happyMarry();

}

//真实角色
class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("我开心的结婚");
    }
}

//代理对象
class WeddingCompany implements Marry{

    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    private void before() {
        System.out.println("结婚前 布置现场");
    }

    private void after() {
        System.out.println("结婚之后 收尾款");
    }

}
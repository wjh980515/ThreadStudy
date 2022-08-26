package Lambda;

public class LambdaStudy {

    //静态内部类 要加关键词static
    static class Me2 implements iLike{
        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }

    public static void main(String[] args) {
        iLike like = new Me();
        like.lambda();
        like = new Me2();
        like.lambda();

        //局部内部类
        class Me3 implements iLike{
            @Override
            public void lambda() {
                System.out.println("i like lambda3");
            }
        }
        like = new Me3();
        like.lambda();

        //匿名内部类
        like = new iLike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda4");
            }
        };
        like.lambda();

        //lambda
        like = () -> {
            System.out.println("i like lambda5");
        };
        like.lambda();

    }

}

interface iLike{
    void lambda();
}

//原始写法
class Me implements iLike{
    @Override
    public void lambda() {
        System.out.println("i like lambda1");
    }
}
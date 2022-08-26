package demo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class CallableStudy implements Callable<Boolean> {
    private String url;
    private String name;

    public CallableStudy(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader2 webDownloader = new WebDownloader2();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableStudy threadDemo1 = new CallableStudy("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/06/27/kuangstudyd348c260-cd59-4f2b-90ab-b36b26256af1.png","first.png");
        CallableStudy threadDemo2 = new CallableStudy("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/07/02/kuangstudy3e7737e2-3b49-4df3-9b52-e045fa935729.png","second.png");
        CallableStudy threadDemo3 = new CallableStudy("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/07/02/kuangstudy695087bc-1a25-40e9-a58b-b53546124cfc.png","third.png");

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> r1 = service.submit(threadDemo1);
        Future<Boolean> r2 = service.submit(threadDemo2);
        Future<Boolean> r3 = service.submit(threadDemo3);
        //获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();
        //关闭服务
        service.shutdown();
    }
}

//下载器
class WebDownloader2{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader出现问题!");
        }
    }
}
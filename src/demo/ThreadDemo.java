package demo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ThreadDemo extends Thread{

    private String url;
    private String name;

    public ThreadDemo(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件：" + name);
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/06/27/kuangstudyd348c260-cd59-4f2b-90ab-b36b26256af1.png","first.png");
        ThreadDemo threadDemo2 = new ThreadDemo("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/07/02/kuangstudy3e7737e2-3b49-4df3-9b52-e045fa935729.png","second.png");
        ThreadDemo threadDemo3 = new ThreadDemo("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/07/02/kuangstudy695087bc-1a25-40e9-a58b-b53546124cfc.png","third.png");

        threadDemo1.start();
        threadDemo2.start();
        threadDemo3.start();
    }

}

//下载器
class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader出现问题!");
        }
    }
}
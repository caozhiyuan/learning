package com.example.demo.utils;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientManagerTest {

    static HttpClientManager httpClientManager = new HttpClientManager();

    @Test
    public void TestHttp() throws Exception {

        while (true){
            long startTime=System.currentTimeMillis();   //获取开始时间

            int count = 10000;
            CountDownLatch countDownLatch = new CountDownLatch(count);
            for (int i = 0; i < count; i++) {
                httpClientManager.getTestAsync("http://10.1.62.66:5500/plaintext", new FutureCallback<HttpResponse>() {
                    @Override
                    public void completed(HttpResponse httpResponse) {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void failed(Exception e) {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void cancelled() {
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();

            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            Thread.sleep(1000);
        }
    }


    @Test
    public void TestHttp2() throws Exception {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(200);
        while (true) {

            long startTime = System.currentTimeMillis();   //获取开始时间

            int count = 10000;
            CountDownLatch countDownLatch = new CountDownLatch(count);
            for (int i = 0; i < count; i++) {
                fixedThreadPool.execute(() -> {
                    try {
                        String str = httpClientManager.getAsync("http://10.1.62.66:5500/plaintext");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }

            countDownLatch.await();
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
            Thread.sleep(1000);
        }
    }
}

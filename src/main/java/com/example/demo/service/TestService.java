package com.example.demo.service;

import com.example.demo.cache.redis.RedisCache;
import com.example.demo.dao.TestDal;
import com.example.demo.domain.Test;
import com.example.demo.util.HttpClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by admin on 2018-06-26.
 */
@Service
public class TestService {

    @Autowired
    public HttpClientManager httpClientManager;

    @Autowired
    public TestDal testDal;

    public String benchmark() throws Exception {
        return httpClientManager.getAsync("http://10.1.62.66:5500/plaintext").get();
    }

    @RedisCache(type = Test.class, cacheTime = 100 )
    public Test getTestById(long  id){
        Optional<Test> optest = testDal.findById(id);
        return optest.get();
    }

    @Async
    public CompletableFuture<Boolean> sendMail() throws InterruptedException {
        CompletableFuture ts = new CompletableFuture();
        Thread.sleep(20);
        ts.complete(true);
        return ts;
    }
}

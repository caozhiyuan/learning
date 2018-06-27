package com.example.demo.service;

import com.example.demo.dao.CityMapper;
import com.example.demo.dao.TestRepository;
import com.example.demo.domain.City;
import com.example.demo.domain.Test;
import com.example.demo.util.HttpClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by admin on 2018-06-26.
 */
@Service
public class TestService {

    @Autowired
    public HttpClientManager httpClientManager;

    @Autowired
    public TestRepository testDal;

    @Autowired
    private CityMapper cityMapper;

    public String benchmark() throws Exception {
        return httpClientManager.getAsync("http://10.1.62.66:5500/plaintext").get();
    }

    public City getCityById(int  id){
        return cityMapper.selectByPrimaryKey(id);
    }

    public Test getTestById(long  id){
        return testDal.getOne(id);
    }

    public Test getTest2ById(long  id) {
        Test test = new Test();
        test.setId(id);
        test.setName("test2");
        return test;
    }

    @Async
    public CompletableFuture<Boolean> sendMail() throws InterruptedException {
        CompletableFuture ts = new CompletableFuture();
        Thread.sleep(20);
        ts.complete(true);
        return ts;
    }
}

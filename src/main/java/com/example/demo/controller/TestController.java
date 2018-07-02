package com.example.demo.controller;
import com.example.demo.domain.RespEntity;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
public class TestController {

    @Autowired
    public TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity test() throws Exception {
        return new RespEntity(testService.getTestById(1L));
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity test2() throws Exception {
        return new RespEntity(testService.getTest2ById(1L));
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity test3() throws Exception {
        Thread.sleep(80);
        return new RespEntity("1111");
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity test4() throws Exception {
        return new RespEntity(testService.getCityById(1));
    }

    @RequestMapping(value = "/async_test", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity asynctest() throws Exception {
        CompletableFuture<Boolean> b1 = testService.sendMail();
        CompletableFuture<Boolean> b2 = testService.sendMail();

        CompletableFuture.allOf(b1,b2).join();

        return new RespEntity(b1.get());
    }
}

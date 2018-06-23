package com.example.demo.controller;
import com.example.demo.dao.TestDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    public TestDal testDal;

    @RequestMapping("/test")
    public String test() throws Exception {
        long a = testDal.count();
        return String.valueOf(a);
    }

}

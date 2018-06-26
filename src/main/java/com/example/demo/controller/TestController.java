package com.example.demo.controller;
import com.example.demo.dao.TestDal;
import com.example.demo.domain.RespCode;
import com.example.demo.domain.RespEntity;
import com.example.demo.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class TestController {

    @Autowired
    public TestDal testDal;

    @RequestMapping("/test")
    @ResponseBody
    public RespEntity test() throws Exception {
        Optional<Test> optest = testDal.findById(1L);
        return new RespEntity(optest.get());
    }

    @RequestMapping("/test2")
    @ResponseBody
    public RespEntity test2() throws Exception {
        return new RespEntity("1111");
    }
}

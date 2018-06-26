package com.example.demo.controller;

import com.example.demo.domain.RespEntity;
import com.example.demo.exception.BizException;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	public TestService testService;

	@RequestMapping("/benchmark")
	public RespEntity benchmark() throws Exception {
		return new RespEntity(testService.benchmark());
	}

	@RequestMapping("/benchmark2")
	public RespEntity benchmark2() throws Exception {
		return new RespEntity("benchmark2");
	}

	@RequestMapping("/ex1")
	public RespEntity extest() throws Exception {
		throw new BizException("参数错误");
	}

	@RequestMapping("/ex2")
	public RespEntity ex2test() throws Exception {
		throw new Exception("ex2");
	}
}

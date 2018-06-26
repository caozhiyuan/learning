package com.example.demo.controller;

import com.example.demo.domain.RespEntity;
import com.example.demo.exception.BizException;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	public TestService testService;

	@RequestMapping(value = "/benchmark", method = RequestMethod.GET)
	public RespEntity benchmark() throws Exception {
		return new RespEntity(testService.benchmark());
	}

	@RequestMapping(value = "/benchmark2", method = RequestMethod.GET)
	public RespEntity benchmark2() throws Exception {
		return new RespEntity("benchmark2");
	}

	@RequestMapping(value = "/ex1", method = RequestMethod.GET)
	public RespEntity extest() throws Exception {
		throw new BizException("参数错误");
	}

	@RequestMapping(value = "/ex2", method = RequestMethod.GET)
	public RespEntity ex2test() throws Exception {
		throw new Exception("ex2");
	}
}

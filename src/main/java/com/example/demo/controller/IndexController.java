package com.example.demo.controller;

import com.example.demo.domain.RespEntity;
import com.example.demo.exceptions.BizException;
import com.example.demo.utils.HttpClientManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	static HttpClientManager httpClientManager = new HttpClientManager();

	@RequestMapping("/benchmark")
	public RespEntity benchmark() throws Exception {
		return new RespEntity(httpClientManager.getAsync("http://10.1.62.66:5500/plaintext").get());
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

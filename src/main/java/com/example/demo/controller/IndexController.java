package com.example.demo.controller;

import com.example.demo.utils.HttpClientManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	static HttpClientManager httpClientManager=new HttpClientManager();

	@RequestMapping("/benchmark")
	public String benchmark() throws Exception {
		return httpClientManager.getAsync("http://10.1.62.66:5500/plaintext").get();
	}

	@RequestMapping("/benchmark2")
	public String benchmark2() throws Exception {
		return "benchmark2";
	}
}

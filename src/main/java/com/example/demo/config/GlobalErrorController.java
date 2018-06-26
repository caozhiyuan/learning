package com.example.demo.config;

import com.example.demo.domain.RespCode;
import com.example.demo.domain.RespEntity;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseStatus(HttpStatus.OK)
    public RespEntity error() {
        return new RespEntity(RespCode.NOTFOUND);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

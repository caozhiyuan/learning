package com.example.demo.cache;

/**
 * Created by admin on 2018-06-26.
 */
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CacheService cacheService;

    @Pointcut("execution(public * com.example.demo.service..*.*(..))")
    public void webAspect(){}

    @Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        String redisResult;
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        String key = genKey(className, methodName, args);

        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Cache cache = method.getAnnotation(Cache.class);
        if (cache != null) {
            int cacheTime = cache.cacheTime();
            Object result;
            redisResult = cacheService.get(key);
            if (redisResult == "" || redisResult == null) {
                result = pjp.proceed(args);
                redisResult = JSON.toJSONString(result);
                cacheService.set(key, redisResult, cacheTime);
            } else {
                //得到被代理方法的返回值类型
                Class returnType = method.getReturnType();
                result = JSON.parseObject(redisResult, returnType);
            }
            return result;
        }
        return pjp.proceed(args);
    }

    private String genKey(String className, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder("SpringBoot:");
        sb.append(className);
        sb.append("_");
        sb.append(methodName);
        sb.append("_");
        for (Object object: args) {
            logger.info("obj:"+object);
            if(object!=null) {
                sb.append(object+"");
                sb.append("_");
            }
        }
        return sb.toString();
    }

}
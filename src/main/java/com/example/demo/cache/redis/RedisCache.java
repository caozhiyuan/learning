package com.example.demo.cache.redis;

/**
 * Created by admin on 2018-06-26.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 加上该注解，代理service命中缓存则从缓存中读取数据，否则从service业务逻辑获得，并存入缓存
 * @Param:
 * @return:
 * @Author:
 * @Date: 2018/5/16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RedisCache {

    /**
     * @Description: 数据返回类型
     * @Param:
     * @return:
     * @Author:
     * @Date: 2018/5/16
     */
    Class type();

    /**
     * @Description: 数据缓存时间单位s秒
     * @Param:  默认10分钟
     * @return:
     * @Author:
     * @Date: 2018/5/16
     */
    int cacheTime() default 600;

}
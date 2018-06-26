package com.example.demo.cache.redis;

import com.example.demo.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018-06-26.
 */
@Service
public class JedisServiceImpl implements CacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value, int seconds) {
        redisTemplate.opsForValue().set(key,value);
        if(seconds!=0){
            redisTemplate.expire(key,seconds , TimeUnit.SECONDS);
        }
    }

    @Override
    public String get(String key) {
        String str = redisTemplate.opsForValue().get(key);
        return str;
    }

    @Override
    public void delKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean lock(String key, int seconds) {
        if(redisTemplate.opsForValue().increment(key,1) == 1 ) {
            redisTemplate.expire(key,seconds , TimeUnit.SECONDS);
            return false;
        }
        return true;
    }

    @Override
    public void unlock(String key) {
        redisTemplate.delete(key);
    }
}
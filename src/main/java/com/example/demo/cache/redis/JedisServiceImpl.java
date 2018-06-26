package com.example.demo.cache.redis;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by admin on 2018-06-26.
 */
@Service
public class JedisServiceImpl implements JedisService {

    @Autowired
    private Jedis jedis;

    @Override
    public boolean exists(String key) {
        boolean flag = false;
        flag = jedis.exists(key);
        return flag;
    }

    @Override
    public String set(String key, String value, int seconds) {
        String responseResult = jedis.set(key,value);
        if(seconds!=0)
            jedis.expire(key,seconds);
        return responseResult;
    }

    @Override
    public String getSet(String key, String value, int seconds) {
        String jedisClusterSet = jedis.getSet(key, value);
        jedis.expire(key,seconds);
        return jedisClusterSet;
    }

    @Override
    public String get(String key) {
        String str = jedis.get(key);
        return str;
    }

    @Override
    public void delKey(String key) {
        jedis.del(key);
    }

    @Override
    public Map<String, Object> getMapData(String key) {
        String str = jedis.get(key);
        Map<String,Object> map = JSON.parseObject(str, Map.class);
        return map;
    }

    @Override
    public boolean lock(String key, int seconds) {
        if(jedis.incr(key) == 1 ) {
            jedis.expire(key,seconds);
            return false;
        }
        return true;
    }

    @Override
    public void unlock(String key) {
        jedis.del(key);
    }

    @Override
    public String getLocakValue(String key) {
        return jedis.get(key);
    }
}
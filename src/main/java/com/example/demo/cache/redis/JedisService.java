package com.example.demo.cache.redis;

import java.util.Map;

/**
 * Created by admin on 2018-06-26.
 */
public interface JedisService {

    boolean exists(String key);

    String set(String key,String value,int seconds);

    String getSet(String key,String value, int seconds);

    String get(String key);

    void delKey(String key);

    Map<String ,Object> getMapData(String key);

    boolean lock(String key,int seconds);

    void unlock(String key);

    String getLocakValue(String key);
}

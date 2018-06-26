package com.example.demo.cache;

/**
 * Created by admin on 2018-06-26.
 */
public interface CacheService {

    void set(String key,String value,int seconds);

    String get(String key);

    void delKey(String key);

    boolean lock(String key,int seconds);

    void unlock(String key);
}

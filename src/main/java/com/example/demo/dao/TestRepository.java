package com.example.demo.dao;


import com.example.demo.cache.Cache;
import com.example.demo.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    @Cache(type = Test.class, cacheTime = 100)
    Test getOne(long id);
}

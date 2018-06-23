package com.example.demo.dao;


import com.example.demo.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDal extends JpaRepository<Test, Long> {

    
}

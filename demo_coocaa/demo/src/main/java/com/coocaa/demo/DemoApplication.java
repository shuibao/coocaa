package com.coocaa.demo;

import com.coocaa.demo.util.NameUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@MapperScan("com.coocaa.demo.dao")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(DemoApplication.class, args);
        new NameUtil().insertMap();
    }
}

package com.wujiale.springbootsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wujiale.springbootsystem.mapper")
@SpringBootApplication
public class SpringbootSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSystemApplication.class, args);
    }

}

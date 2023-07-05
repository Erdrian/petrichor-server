package com.petrichor.sincerity;

import com.petrichor.sincerity.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.petrichor.sincerity.mapper")
@SpringBootApplication
public class SincerityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SincerityApplication.class, args);
    }

}

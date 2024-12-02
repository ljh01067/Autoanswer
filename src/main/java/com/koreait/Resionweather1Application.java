package com.koreait;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.koreait.autoanswer1.repository") // 매퍼 패키지 경로
public class Resionweather1Application {
    public static void main(String[] args) {
        SpringApplication.run(Resionweather1Application.class, args);
    }
}

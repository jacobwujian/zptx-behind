package com.ecit.edu.zpxtbehind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.ecit.edu.zpxtbehind.*.mapper"})
@SpringBootApplication
public class ZpxtBehindApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpxtBehindApplication.class, args);
    }

}

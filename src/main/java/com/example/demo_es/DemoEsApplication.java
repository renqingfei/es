package com.example.demo_es;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.example.demo_es.dao")
@SpringBootApplication
@EnableSwagger2
@EnableCaching //开启注解
public class DemoEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEsApplication.class, args);
    }

}

package com.halcyon.techcheckbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.halcyon.techcheckbackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class TechCheckBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechCheckBackendApplication.class, args);
    }

}

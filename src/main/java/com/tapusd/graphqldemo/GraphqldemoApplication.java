package com.tapusd.graphqldemo;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class GraphqldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqldemoApplication.class, args);
    }

}

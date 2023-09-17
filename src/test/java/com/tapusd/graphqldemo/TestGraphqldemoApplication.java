package com.tapusd.graphqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestGraphqldemoApplication {

    @Bean
    @ServiceConnection
    @RestartScope
    MongoDBContainer mongoDbContainer() {
        return new MongoDBContainer(DockerImageName.parse("mongo:latest"));
    }

    public static void main(String[] args) {
        SpringApplication.from(GraphqldemoApplication::main).with(TestGraphqldemoApplication.class).run(args);
    }

}

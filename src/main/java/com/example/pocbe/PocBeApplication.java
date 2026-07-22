package com.example.pocbe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @OpenAPIDefinition 讓產生出來的 spec 有穩定的 title/version/servers，
// 避免 info 區塊或 RANDOM_PORT 的 server url 漂移造成前端假 diff。
@OpenAPIDefinition(
    info = @Info(
        title = "api-be-test",
        version = "1.0.0",
        description = "POC 後端 (Spring Boot + springdoc 自動產生合約)"),
    servers = @Server(url = "http://localhost:8080", description = "local"))
@SpringBootApplication
public class PocBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PocBeApplication.class, args);
    }
}

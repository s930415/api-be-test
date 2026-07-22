package com.example.pocbe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

// 啟動整個 app（RANDOM_PORT），打 springdoc 的 /v3/api-docs.yaml，
// 把產生出來的合約寫到 repo 根目錄的 openapi.yaml。
// CI 跑 `mvn test` 就會產出 spec，接著 workflow 再 commit 回 repo 並發 dispatch。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenApiExportTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void exportOpenApiSpec() throws Exception {
        String yaml = rest.getForObject("/v3/api-docs.yaml", String.class);

        assertThat(yaml).isNotBlank();
        assertThat(yaml).contains("openapi:");
        assertThat(yaml).contains("Product");

        Files.writeString(Path.of("openapi.yaml"), yaml);
        System.out.println("[OpenApiExportTest] wrote openapi.yaml (" + yaml.length() + " bytes)");
    }
}

package com.example.pocbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    public record Health(
        @Schema(description = "服務狀態", example = "ok") String status
    ) {}

    @GetMapping("/health")
    @Operation(operationId = "getHealth", summary = "健康檢查")
    public Health health() {
        return new Health("ok");
    }
}

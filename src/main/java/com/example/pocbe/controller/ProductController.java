package com.example.pocbe.controller;

import com.example.pocbe.dto.Product;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    @Operation(operationId = "listProducts", summary = "取得商品列表")
    public List<Product> list() {
        return List.of(
            new Product(1, "Coffee", 4.5, 100, "drinks", "SKU-001"),
            new Product(2, "Tea", 3.0, 50, "drinks", "SKU-002")
        );
    }
}

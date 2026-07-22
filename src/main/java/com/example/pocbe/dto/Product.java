package com.example.pocbe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// 改這個 record 的欄位 = 模擬一次 API 變更。
// springdoc 會從這裡的欄位與 @Schema 自動產生 OpenAPI 的 Product schema，
// merge 到 main 後 CI 重新產生 spec 並觸發前端同步型別。
public record Product(
    @Schema(description = "商品 ID", example = "1") long id,
    @Schema(description = "商品名稱", example = "Coffee") String name,
    @Schema(description = "價格", example = "4.5") double price,
    @Schema(description = "庫存數量", example = "100") int stock,
    @Schema(description = "商品分類", example = "drinks") String category,
    @Schema(description = "商品貨號", example = "SKU-001") String sku,
    @Schema(description = "條碼", example = "4710011401234") String barcode
) {}

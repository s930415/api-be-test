# api-be-test(後端 · Java / Spring Boot)

POC 後端。**OpenAPI 合約由 springdoc 從程式碼(Controller + DTO)自動產生**,不是手寫。

- 改 `src/main/java/.../dto/Product.java` 或 Controller = 模擬一次 API 變更。
- PR merge 到 main 後,`.github/workflows/notify-frontend.yml` 會:
  1. `mvn test` 跑 `OpenApiExportTest` → 打 springdoc 的 `/v3/api-docs.yaml` → 寫出 `openapi.yaml`
  2. 若 `openapi.yaml` 有變更 → commit 回 repo
  3. 帶「最新 sha」送 `repository_dispatch` 給前端 `api-fe-test`
  4. 前端抓該 sha 的 `openapi.yaml` → 重新產生型別 → 開 PR

## 技術棧

- Spring Boot 3.3 / Java 17 / Maven
- springdoc-openapi-starter-webmvc-api(提供 `/v3/api-docs` 與 `/v3/api-docs.yaml`)

## 本機跑

```bash
mvn spring-boot:run                       # http://localhost:8080
# curl localhost:8080/products
# curl localhost:8080/v3/api-docs.yaml     # 看自動產生的合約

mvn test                                  # 產生/更新根目錄 openapi.yaml
```

## 為什麼合約要 commit 回 repo

springdoc 產生的 spec 不在原始碼裡。commit 回 repo 有兩個好處:
(1) 合約被版本控管、後端 PR 也看得到 diff;(2) 前端能用 `raw.githubusercontent.com/.../<sha>/openapi.yaml`
免驗證抓取,前端 workflow 完全不用改。

## 需要的 secret

`FE_DISPATCH_TOKEN` —— fine-grained PAT,授權 `api-fe-test` 的 Contents: write。設定見上層 [SETUP.md](../SETUP.md)。

## 決定論注意

`PocBeApplication` 上的 `@OpenAPIDefinition` 固定了 `title/version/servers`,避免 `RANDOM_PORT`
的 server url 或 info 區塊每次漂移,造成前端收到無意義的假 diff PR。

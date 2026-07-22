# api-be-test(後端)

POC 後端。`openapi.yaml` 是 API 合約的 source of truth。

- **改 `openapi.yaml` = 模擬一次 API 變更。**
- PR merge 到 main 後,`.github/workflows/notify-frontend.yml` 會送 `repository_dispatch`
  給前端 repo `api-fe-test`,觸發它重新產生型別並開 PR。

## 本機跑起來(選用)

```bash
npm install
npm start            # http://localhost:3000
# curl localhost:3000/products
```

## 需要的 secret

`FE_DISPATCH_TOKEN` —— fine-grained PAT,授權 `api-fe-test` 的 Contents: write。
設定方式見上層 [SETUP.md](../SETUP.md)。

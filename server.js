// 極簡後端,實作 openapi.yaml 裡描述的 endpoint。
// CI 不需要跑它 —— workflow 只會讀 openapi.yaml 這份合約。
// 它存在只是為了證明 spec 是「真的」,本機可 `npm start` 起來試打。
const express = require('express');

const app = express();
const port = process.env.PORT || 3000;

app.get('/health', (req, res) => {
  res.json({ status: 'ok' });
});

app.get('/products', (req, res) => {
  res.json([
    { id: 1, name: 'Coffee', price: 4.5, stock: 100 },
    { id: 2, name: 'Tea', price: 3.0, stock: 50 },
  ]);
});

app.listen(port, () => {
  console.log(`api-be-test listening on http://localhost:${port}`);
});

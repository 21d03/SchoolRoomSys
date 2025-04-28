# 宿管催办维修接口文档

## 宿管催办维修人员

### 接口描述
宿管对已分配的维修工进行催办，记录催办信息。

### 请求信息
- 请求路径：`/hm/repair/urge`
- 请求方法：POST
- 请求头：
  ```
  Content-Type: application/json
  ```

### 请求参数
请求体JSON格式：
```json
{
    "repairId": "String",     // 报修单ID（必填）
    "hmId": "String",         // 宿管ID（必填）
    "workerId": "String",     // 维修工ID（必填）
    "urgeContent": "String"   // 催办内容（必填）
}
```

### 响应结果
```json
// 成功响应
{
    "code": 200,
    "message": "success",
    "data": null
}

// 失败响应
{
    "code": 500,
    "message": "催办失败",
    "data": null
}
```

### 错误码说明
| 错误描述 | 说明 |
|----------|------|
| 催办失败 | 创建催办记录时发生错误 |

### CURL示例

```bash
curl -X POST 'http://localhost:8080/hm/repair/urge' \
-H 'Content-Type: application/json' \
-d '{
    "repairId": "RP2024001",
    "hmId": "HM001",
    "workerId": "RW001",
    "urgeContent": "请尽快处理维修申请"
}'
```

### 业务规则说明
1. 同一宿管对同一维修工的同一报修单只能催办一次
2. 催办记录会永久保存，用于后续统计和分析
3. 催办类型固定为2（表示宿管催办维修人员）
4. 审批类型固定为1（表示报修审批） 
# 宿管报修审批接口文档

## 处理报修审批

### 接口描述
处理宿管对报修申请的审批，包括通过或拒绝报修申请。通过时会自动分配维修工。

### 请求信息
- 请求路径：`/hm/repair/approve`
- 请求方法：PUT
- 请求头：
  ```
  Content-Type: application/json
  ```

### 请求参数
请求体JSON格式：
```json
{
    "repairId": "String",    // 报修单ID（必填）
    "status": "Integer",     // 审批状态(1:通过,2:拒绝)（必填）
    "opinion": "String",     // 审批意见（状态为拒绝时必填）
    "buildId": "String"      // 宿舍楼ID（必填）
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
    "message": "错误信息",
    "data": null
}
```

### 错误码说明
| 错误描述 | 说明 |
|----------|------|
| 拒绝时必须填写审批意见 | 当status=2时，opinion参数为空 |
| 报修单不存在 | 提供的repairId无效 |
| 该校区暂无可用维修工 | 当status=1时，找不到可用的维修工 |
| 更新失败 | 数据库更新操作失败 |

### CURL示例

1. 通过审批：
```bash
curl -X PUT 'http://localhost:8080/hm/repair/approve' \
-H 'Content-Type: application/json' \
-d '{
    "repairId": "RP2024001",
    "status": 1,
    "buildId": "B001"
}'
```

2. 拒绝审批：
```bash
curl -X PUT 'http://localhost:8080/hm/repair/approve' \
-H 'Content-Type: application/json' \
-d '{
    "repairId": "RP2024001",
    "status": 2,
    "opinion": "物品已过保修期",
    "buildId": "B001"
}'
```

### 业务规则说明
1. 当审批通过时（status=1）：
   - 系统会自动分配工作量最少的维修工
   - 更新报修单状态和维修工ID
   - 记录审批时间和审批意见

2. 当审批拒绝时（status=2）：
   - 必须提供审批意见
   - 更新报修单状态
   - 记录审批时间和审批意见

3. 所有操作都会记录操作时间 
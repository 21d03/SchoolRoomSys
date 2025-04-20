# 学生请假申请接口

## 请求URL
- `/student/leave/apply`

## 请求方式
- POST

## 请求参数
```json
{
  "studentId": "S20240001",          // 学生ID，必填
  "startTime": "2025-04-25 08:00:00", // 开始时间，格式：yyyy-MM-dd HH:mm:ss，必填
  "endTime": "2025-04-27 18:00:00",   // 结束时间，格式：yyyy-MM-dd HH:mm:ss，必填
  "reason": "回家探亲",               // 请假原因，必填
  "destination": "北京市朝阳区",      // 请假去向，必填
  "contactPhone": "13800138000"       // 紧急联系电话，必填
}
```

## 请求参数说明
| 参数名        | 必选  | 类型   | 说明                                    |
|--------------|------|--------|----------------------------------------|
| studentId    | 是   | String | 学生ID                                  |
| startTime    | 是   | String | 开始时间，格式：yyyy-MM-dd HH:mm:ss       |
| endTime      | 是   | String | 结束时间，格式：yyyy-MM-dd HH:mm:ss       |
| reason       | 是   | String | 请假原因                                 |
| destination  | 是   | String | 请假去向                                 |
| contactPhone | 是   | String | 紧急联系电话                             |

## 返回示例

### 成功
```json
{
  "code": 200,
  "message": "success",
  "data": "LV20250420001"  // 请假申请ID
}
```

### 失败 - 存在待审批申请
```json
{
  "code": 500,
  "message": "您已有待审批的请假申请，请等待审批完成后再提交新的申请",
  "data": null
}
```

## 返回参数说明
| 参数名  | 类型    | 说明                                  |
|--------|--------|--------------------------------------|
| code   | Integer| 状态码，200表示成功，500表示失败          |
| message| String | 状态消息                               |
| data   | String | 请假申请ID，格式：LVyyyyMMddxxx         |

## 业务规则
- 请假申请ID生成规则：LVyyyyMMddxxx，yyyyMMdd表示日期，xxx表示流水号
- **同一学生不能同时存在多个待审批的请假申请**，必须等待当前请假申请审批完成后才能提交新的申请

## CURL示例
```bash
curl -X POST \
  http://localhost:8080/student/leave/apply \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "studentId": "S20240001",
  "startTime": "2025-04-25 08:00:00",
  "endTime": "2025-04-27 18:00:00",
  "reason": "回家探亲",
  "destination": "北京市朝阳区",
  "contactPhone": "13800138000"
}'
``` 
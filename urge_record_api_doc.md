# 催促接口文档

## 请求URL
- `/urge/create`

## 请求方式
- POST

## 请求参数
```json
{
  "fromId": "S20240001",       // 催促人ID，必填
  "toId": "HM001",             // 接收人ID，必填
  "approvalId": "RP20250420001", // 审批单ID，必填
  "approvalType": "1",         // 审批类型，默认为1（1-报修 2-请假）
  "urgeType": "1",             // 催促类型，必填（1-学生催促宿管 2-学生催促维修 3-宿管催促维修）
  "urgeContent": "请尽快审批我的报修申请" // 催促内容，必填
}
```

## 请求参数说明
| 参数名       | 必选 | 类型   | 说明                                           |
|-------------|-----|--------|------------------------------------------------|
| fromId      | 是  | String | 催促人ID，发起催促的用户ID                        |
| toId        | 是  | String | 接收人ID，被催促的用户ID                          |
| approvalId  | 是  | String | 审批单ID，报修单或请假单ID                        |
| approvalType| 否  | String | 审批类型（1-报修 2-请假），默认为1                 |
| urgeType    | 是  | String | 催促类型（1-学生催促宿管 2-学生催促维修 3-宿管催促维修）|
| urgeContent | 是  | String | 催促内容，催促的具体文字内容                      |

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": true  // true表示催促成功
}
```

## 业务规则
- 同一用户对同一审批单的同一催促类型只会记录一次，重复催促会返回成功但不会重复记录
- 系统会自动记录催促发生的时间

## CURL示例
```bash
curl -X POST \
  http://localhost:8080/urge/create \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "fromId": "S20240001",
  "toId": "HM001",
  "approvalId": "RP20250420001",
  "approvalType": "1",
  "urgeType": "1",
  "urgeContent": "请尽快审批我的报修申请"
}'
``` 
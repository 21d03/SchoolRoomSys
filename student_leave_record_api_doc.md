# 学生请假记录查询接口

## 请求URL
- `/student/leave/records`

## 请求方式
- POST

## 请求参数
```json
{
  "studentId": "S20240001",          // 学生ID，必填
  "pageNum": 1,                      // 页码，默认为1
  "pageSize": 10,                    // 每页数量，默认为10
  "status": "0",                     // 审批状态，非必填（0-待审批，1-已通过，2-已驳回）
  "startTimeBegin": "2025-04-01 00:00:00", // 开始时间范围(起始)，非必填
  "startTimeEnd": "2025-04-30 23:59:59",   // 开始时间范围(结束)，非必填
}
```

## 请求参数说明
| 参数名         | 必选  | 类型   | 说明                                    |
|---------------|------|--------|----------------------------------------|
| studentId     | 是   | String | 学生ID                                  |
| pageNum       | 否   | Integer| 页码，默认为1                            |
| pageSize      | 否   | Integer| 每页数量，默认为10                        |
| status        | 否   | String | 审批状态（0-待审批，1-已通过，2-已驳回）    |
| startTimeBegin| 否   | String | 开始时间范围(起始)，格式：yyyy-MM-dd HH:mm:ss |
| startTimeEnd  | 否   | String | 开始时间范围(结束)，格式：yyyy-MM-dd HH:mm:ss |

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [
      {
        "id": "LV20250420001",               // 请假申请ID
        "studentId": "S20240001",            // 学生ID
        "startTime": "2025-04-25 08:00:00",  // 开始时间
        "endTime": "2025-04-27 18:00:00",    // 结束时间
        "reason": "回家探亲",                // 请假原因
        "destination": "北京市朝阳区",        // 请假去向
        "contactPhone": "13800138000",       // 紧急联系电话
        "teacherId": "T001",                 // 教师ID
        "status": "0",                       // 审批状态（0-待审批，1-已通过，2-已驳回）
        "opinion": null,                     // 审批意见
        "approveTime": null,                 // 审批时间
        "createTime": "2025-04-20 10:30:00", // 创建时间
        "updateTime": "2025-04-20 10:30:00"  // 更新时间
      },
      {
        "id": "LV20250410002",
        "studentId": "S20240001",
        "startTime": "2025-04-15 08:00:00",
        "endTime": "2025-04-16 18:00:00",
        "reason": "参加比赛",
        "destination": "上海市",
        "contactPhone": "13800138000",
        "teacherId": "T001",
        "status": "1",
        "opinion": "同意",
        "approveTime": "2025-04-12 09:30:00",
        "createTime": "2025-04-10 14:20:00",
        "updateTime": "2025-04-12 09:30:00"
      }
    ],
    "total": 2,       // 总记录数
    "size": 10,       // 每页数量
    "current": 1,     // 当前页码
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "pages": 1        // 总页数
  }
}
```

## 返回参数说明
### 一级参数
| 参数名  | 类型    | 说明                                  |
|--------|--------|--------------------------------------|
| code   | Integer| 状态码，200表示成功                     |
| message| String | 状态消息                               |
| data   | Object | 分页数据                               |

### data对象参数
| 参数名           | 类型    | 说明                                 |
|-----------------|--------|-------------------------------------|
| records         | Array  | 请假记录列表                          |
| total           | Integer| 总记录数                             |
| size            | Integer| 每页数量                             |
| current         | Integer| 当前页码                             |
| pages           | Integer| 总页数                               |

### records列表项参数
| 参数名        | 类型   | 说明                                   |
|--------------|-------|---------------------------------------|
| id           | String | 请假申请ID                             |
| studentId    | String | 学生ID                                |
| startTime    | String | 开始时间，格式：yyyy-MM-dd HH:mm:ss     |
| endTime      | String | 结束时间，格式：yyyy-MM-dd HH:mm:ss     |
| reason       | String | 请假原因                               |
| destination  | String | 请假去向                               |
| contactPhone | String | 紧急联系电话                           |
| teacherId    | String | 教师ID                                |
| status       | String | 审批状态（0-待审批，1-已通过，2-已驳回）   |
| opinion      | String | 审批意见                               |
| approveTime  | String | 审批时间，格式：yyyy-MM-dd HH:mm:ss     |
| createTime   | String | 创建时间，格式：yyyy-MM-dd HH:mm:ss     |
| updateTime   | String | 更新时间，格式：yyyy-MM-dd HH:mm:ss     |

## CURL示例
```bash
curl -X POST \
  http://localhost:8080/student/leave/records \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "studentId": "S20240001",
  "pageNum": 1,
  "pageSize": 10,
  "status": "0",
  "startTimeBegin": "2025-04-01 00:00:00",
  "startTimeEnd": "2025-04-30 23:59:59",
  "reason": "探亲"
}'
``` 
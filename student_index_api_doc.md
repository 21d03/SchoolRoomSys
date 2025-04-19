# 学生首页数据统计接口

## 请求URL
- `/student/index/data`

## 请求方式
- GET

## 请求参数
| 参数名     | 必选  | 类型   | 说明       |
|-----------|------|--------|------------|
| studentId | 是   | String | 学生ID     |

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "buildName": "第一宿舍楼",    // 宿舍楼，学生所在宿舍楼
    "roomId": "101",           // 房间号，学生所在房间号
    "bedId": "01",             // 床位号，学生所在床位号
    "leavePendingCount": 2,    // 请假待审批数量，status=0的请假数量
    "leaveProcessedCount": 5,  // 请假已处理数量，status!=0的请假数量
    "repairPendingCount": 1,   // 报修待审批数量，hm_status=0或(hm_status=1且rp_status=0)的报修数量
    "repairProcessedCount": 3  // 报修已完成数量，hm_status=2或(hm_status=1且rp_status!=0)的报修数量
  }
}
```

## 返回参数说明
| 参数名              | 类型    | 说明                                                            |
|--------------------|--------|----------------------------------------------------------------|
| buildName          | String | 宿舍楼，学生所在宿舍楼                                             |
| roomId             | String | 房间号，学生所在房间号                                             |
| bedId              | String | 床位号，学生所在床位号                                             |
| leavePendingCount  | Integer| 请假待审批数量，status=0的请假数量                                 |
| leaveProcessedCount| Integer| 请假已处理数量，status!=0的请假数量                                |
| repairPendingCount | Integer| 报修待审批数量，hm_status=0或(hm_status=1且rp_status=0)的报修数量   |
| repairProcessedCount| Integer| 报修已完成数量，hm_status=2或(hm_status=1且rp_status!=0)的报修数量 |

## CURL示例
```bash
curl -X GET \
  'http://localhost:8080/student/index/data?studentId=S20240001' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -H 'Content-Type: application/json'
``` 
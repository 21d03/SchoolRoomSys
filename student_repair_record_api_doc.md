# 学生报修记录查询接口

## 请求URL
- `/student/repair/records`

## 请求方式
- POST

## 请求参数
```json
{
  "studentId": "S20240001",          // 学生ID，必填
  "pageNum": 1,                      // 页码，默认为1
  "pageSize": 10,                    // 每页数量，默认为10
  "repairType": "1",                 // 报修类型，非必填（1-宿舍物品 2-公共物品）
  "hmStatus": "0",                   // 宿管审批状态，非必填（0-待审批 1-已通过 2-已驳回）
  "rpStatus": "0",                   // 维修人员审批状态，非必填（0-待维修 1-已完成 2-已驳回）
  "itemName": "水龙头"               // 报修物品名称，支持模糊查询，非必填
}
```

## 请求参数说明
| 参数名      | 必选  | 类型    | 说明                                    |
|------------|------|---------|----------------------------------------|
| studentId  | 是   | String  | 学生ID                                  |
| pageNum    | 否   | Integer | 页码，默认为1                            |
| pageSize   | 否   | Integer | 每页数量，默认为10                        |
| repairType | 否   | String  | 报修类型（1-宿舍物品 2-公共物品）          |
| hmStatus   | 否   | String  | 宿管审批状态（0-待审批 1-已通过 2-已驳回）  |
| rpStatus   | 否   | String  | 维修状态（0-待维修 1-已完成 2-已驳回）      |
| itemName   | 否   | String  | 报修物品名称，支持模糊查询                  |

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [
      {
        "id": "RP20250420001",               // 报修申请ID
        "studentId": "S20240001",            // 学生ID
        "repairType": "1",                   // 报修类型（1-宿舍物品 2-公共物品）
        "roomId": "101",                     // 宿舍号（报修类型为1时有值）
        "publicArea": null,                  // 公共区域（报修类型为2时有值）
        "itemName": "水龙头",                // 报修物品名称
        "description": "水龙头漏水",         // 问题描述
        "images": "http://xxx/img1.jpg,http://xxx/img2.jpg", // 图片地址，多个用逗号分隔
        "hmId": "HM001",                     // 宿管ID
        "hmName": "张宿管",                  // 宿管姓名
        "hmStatus": "0",                     // 宿管审批状态（0-待审批 1-已通过 2-已驳回）
        "hmOpinion": null,                   // 宿管审批意见
        "hmTime": null,                      // 宿管审批时间
        "rpId": null,                        // 维修人员ID
        "rpName": null,                      // 维修人员姓名
        "rpStatus": null,                    // 维修状态（0-待维修 1-已完成 2-已驳回）
        "rpOpinion": null,                   // 维修意见
        "rpTime": null,                      // 维修完成时间
        "createTime": "2025-04-20 10:30:00", // 创建时间
        "canUrgeHm": true,                   // 是否可以催促宿管
        "canUrgeRp": false                   // 是否可以催促维修人员
      },
      {
        "id": "RP20250419001",
        "studentId": "S20240001",
        "repairType": "2",
        "roomId": null,
        "publicArea": "一楼走廊",
        "itemName": "灯管",
        "description": "灯管不亮",
        "images": "http://xxx/img3.jpg",
        "hmId": "HM001",
        "hmName": "张宿管",
        "hmStatus": "1",
        "hmOpinion": "同意维修",
        "hmTime": "2025-04-19 14:30:00",
        "rpId": "RP001",
        "rpName": "王维修",
        "rpStatus": "0",
        "rpOpinion": null,
        "rpTime": null,
        "createTime": "2025-04-19 10:00:00",
        "canUrgeHm": false,
        "canUrgeRp": true
      }
    ],
    "total": 2,       // 总记录数
    "size": 10,       // 每页数量
    "current": 1,     // 当前页码
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
| 参数名   | 类型    | 说明                                 |
|---------|--------|-------------------------------------|
| records | Array  | 报修记录列表                          |
| total   | Integer| 总记录数                             |
| size    | Integer| 每页数量                             |
| current | Integer| 当前页码                             |
| pages   | Integer| 总页数                               |

### records列表项参数
| 参数名      | 类型    | 说明                                    |
|------------|--------|----------------------------------------|
| id         | String | 报修申请ID                              |
| studentId  | String | 学生ID                                  |
| repairType | String | 报修类型（1-宿舍物品 2-公共物品）          |
| roomId     | String | 宿舍号（报修类型为1时有值）                |
| publicArea | String | 公共区域（报修类型为2时有值）              |
| itemName   | String | 报修物品名称                             |
| description| String | 问题描述                                |
| images     | String | 图片地址，多个用逗号分隔                   |
| hmId       | String | 宿管ID                                  |
| hmName     | String | 宿管姓名                                |
| hmStatus   | String | 宿管审批状态（0-待审批 1-已通过 2-已驳回）  |
| hmOpinion  | String | 宿管审批意见                             |
| hmTime     | String | 宿管审批时间，格式：yyyy-MM-dd HH:mm:ss   |
| rpId       | String | 维修人员ID                              |
| rpName     | String | 维修人员姓名                             |
| rpStatus   | String | 维修状态（0-待维修 1-已完成 2-已驳回）      |
| rpOpinion  | String | 维修意见                                |
| rpTime     | String | 维修完成时间，格式：yyyy-MM-dd HH:mm:ss   |
| createTime | String | 创建时间，格式：yyyy-MM-dd HH:mm:ss       |
| canUrgeHm  | Boolean| 是否可以催促宿管                          |
| canUrgeRp  | Boolean| 是否可以催促维修人员                      |

## 催促规则说明
1. 催促宿管（canUrgeHm）：
   - 当宿管审批状态为待审批（hmStatus=0）时可以催促
   - 其他状态不可催促

2. 催促维修人员（canUrgeRp）：
   - 当宿管已审批通过（hmStatus=1）且维修人员未处理（rpStatus=0或null）时可以催促
   - 其他状态不可催促

## CURL示例
```bash
curl -X POST \
  http://localhost:8080/student/repair/records \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "studentId": "S20240001",
  "pageNum": 1,
  "pageSize": 10,
  "repairType": "1",
  "hmStatus": "0",
  "rpStatus": "0",
  "itemName": "水龙头"
}'
``` 
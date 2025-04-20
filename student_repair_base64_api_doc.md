# 学生报修接口文档（更新版-Base64图片）

## 1. 上传报修图片

### 请求URL
- `/student/repair/upload`

### 请求方式
- POST

### 请求参数
- Content-Type: multipart/form-data

| 参数名 | 必选 | 类型   | 说明     |
|--------|------|--------|----------|
| file   | 是   | File   | 图片文件 |

### 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": "D:\\code\\dl\\SchoolRoomSys\\src\\main\\resources\\images\\20250420\\550e8400-e29b-41d4-a716-446655440000.jpg"  // 图片存储的绝对路径
}
```

### CURL示例
```bash
curl -X POST \
  http://localhost:8080/student/repair/upload \
  -H 'Authorization: Bearer 您的TOKEN' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@/path/to/your/image.jpg'
```

## 2. 提交报修申请

### 请求URL
- `/student/repair/apply`

### 请求方式
- POST

### 请求参数
```json
{
  "studentId": "S20240001",          // 学生ID，必填
  "repairType": "1",                 // 报修类型，必填（1-宿舍物品 2-公共物品）
  "roomId": "101",                   // 宿舍号，报修类型为1时必填
  "publicArea": null,                // 公共区域，报修类型为2时必填
  "itemName": "水龙头",              // 报修物品名称，必填
  "description": "水龙头漏水",       // 问题描述，必填
  "images": "D:\\code\\dl\\SchoolRoomSys\\src\\main\\resources\\images\\20250420\\image1.jpg,D:\\code\\dl\\SchoolRoomSys\\src\\main\\resources\\images\\20250420\\image2.jpg"  // 图片绝对路径，多个用逗号分隔，选填
}
```

### 请求参数说明
| 参数名      | 必选  | 类型   | 说明                                    |
|------------|------|--------|----------------------------------------|
| studentId  | 是   | String | 学生ID                                  |
| repairType | 是   | String | 报修类型（1-宿舍物品 2-公共物品）         |
| roomId     | 否   | String | 宿舍号，报修类型为1时必填                 |
| publicArea | 否   | String | 公共区域，报修类型为2时必填               |
| itemName   | 是   | String | 报修物品名称                             |
| description| 是   | String | 问题描述                                |
| images     | 否   | String | 图片绝对路径，多个用逗号分隔               |

### 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": "RP20250420001"  // 报修申请ID
}
```

### 报修申请ID生成规则
- 格式：RPyyyyMMddxxx
- RP: 固定前缀
- yyyyMMdd: 当前年月日
- xxx: 当天的流水号，从001开始递增

### CURL示例
```bash
curl -X POST \
  http://localhost:8080/student/repair/apply \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "studentId": "S20240001",
  "repairType": "1",
  "roomId": "101",
  "publicArea": null,
  "itemName": "水龙头",
  "description": "水龙头漏水",
  "images": "D:\\code\\dl\\SchoolRoomSys\\src\\main\\resources\\images\\20250420\\image1.jpg,D:\\code\\dl\\SchoolRoomSys\\src\\main\\resources\\images\\20250420\\image2.jpg"
}'
```

## 3. 查询报修记录

### 请求URL
- `/student/repair/records`

### 请求方式
- POST

### 请求参数
```json
{
  "studentId": "S20240001",     // 学生ID，必填
  "pageNum": 1,                 // 页码，必填
  "pageSize": 10,               // 每页数量，必填
  "repairType": "1",            // 报修类型（1-宿舍物品 2-公共物品），选填
  "hmStatus": "0",              // 宿管审批状态（0-待审批 1-已通过 2-已驳回），选填
  "rpStatus": "0",              // 维修状态（0-待维修 1-已完成 2-已驳回），选填
  "itemName": "水龙头"           // 报修物品名称，模糊查询，选填
}
```

### 请求参数说明
| 参数名     | 必选  | 类型    | 说明                                      |
|-----------|------|---------|------------------------------------------|
| studentId | 是   | String  | 学生ID                                    |
| pageNum   | 是   | Integer | 页码，从1开始                              |
| pageSize  | 是   | Integer | 每页数量                                  |
| repairType| 否   | String  | 报修类型（1-宿舍物品 2-公共物品）           |
| hmStatus  | 否   | String  | 宿管审批状态（0-待审批 1-已通过 2-已驳回）   |
| rpStatus  | 否   | String  | 维修状态（0-待维修 1-已完成 2-已驳回）       |
| itemName  | 否   | String  | 报修物品名称，支持模糊查询                   |

### 返回示例
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
        "images": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD...,data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD...", // 图片Base64编码，多个用逗号分隔
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
      }
    ],
    "total": 1,       // 总记录数
    "size": 10,       // 每页数量
    "current": 1,     // 当前页码
    "pages": 1        // 总页数
  }
}
```

### 返回参数说明
| 参数名  | 类型    | 说明                             |
|--------|--------|----------------------------------|
| code   | Integer| 状态码，200表示成功               |
| message| String | 状态消息                         |
| data   | Object | 分页数据                         |

#### data对象参数
| 参数名   | 类型    | 说明                           |
|---------|--------|--------------------------------|
| records | Array  | 报修记录列表                    |
| total   | Integer| 总记录数                       |
| size    | Integer| 每页数量                       |
| current | Integer| 当前页码                       |
| pages   | Integer| 总页数                         |

#### records列表项参数
| 参数名      | 类型    | 说明                                     |
|------------|--------|------------------------------------------|
| id         | String | 报修申请ID                                |
| studentId  | String | 学生ID                                    |
| repairType | String | 报修类型（1-宿舍物品 2-公共物品）           |
| roomId     | String | 宿舍号（报修类型为1时有值）                 |
| publicArea | String | 公共区域（报修类型为2时有值）               |
| itemName   | String | 报修物品名称                              |
| description| String | 问题描述                                  |
| images     | String | 图片Base64编码，多个用逗号分隔              |
| hmId       | String | 宿管ID                                    |
| hmName     | String | 宿管姓名                                  |
| hmStatus   | String | 宿管审批状态（0-待审批 1-已通过 2-已驳回）   |
| hmOpinion  | String | 宿管审批意见                              |
| hmTime     | String | 宿管审批时间，格式：yyyy-MM-dd HH:mm:ss     |
| rpId       | String | 维修人员ID                                |
| rpName     | String | 维修人员姓名                              |
| rpStatus   | String | 维修状态（0-待维修 1-已完成 2-已驳回）       |
| rpOpinion  | String | 维修意见                                  |
| rpTime     | String | 维修完成时间，格式：yyyy-MM-dd HH:mm:ss     |
| createTime | String | 创建时间，格式：yyyy-MM-dd HH:mm:ss         |
| canUrgeHm  | Boolean| 是否可以催促宿管                           |
| canUrgeRp  | Boolean| 是否可以催促维修人员                       |

### CURL示例
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

## 业务规则说明
1. 图片上传：
   - 支持jpg、jpeg、png等常见图片格式
   - 图片会按日期存储在服务器指定目录
   - 返回的是图片存储的绝对路径，而不是URL前缀

2. 报修申请：
   - 报修类型为1（宿舍物品）时，必须填写roomId，不能填写publicArea
   - 报修类型为2（公共物品）时，必须填写publicArea，不能填写roomId
   - 系统会自动根据学生ID查询对应的宿舍楼ID和宿管ID
   - 图片地址支持多张图片，使用逗号分隔

3. 查询报修记录：
   - 图片以Base64格式返回，可直接嵌入HTML中显示
   - Base64格式：data:image/jpeg;base64,/9j/4AAQSkZJRgAB...
   - 多张图片用逗号分隔

4. 催促规则：
   - 催促宿管（canUrgeHm）：当宿管审批状态为待审批（hmStatus=0）时可以催促
   - 催促维修人员（canUrgeRp）：当宿管已审批通过（hmStatus=1）且维修人员未处理（rpStatus=0或null）时可以催促 
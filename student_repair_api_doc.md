# 学生报修接口文档

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
  "data": "http://localhost:8080/images/20250420/550e8400-e29b-41d4-a716-446655440000.jpg"  // 图片访问URL
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
  "images": "http://localhost:8080/images/20250420/image1.jpg,http://localhost:8080/images/20250420/image2.jpg"  // 图片地址，多个用逗号分隔，选填
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
| images     | 否   | String | 图片地址，多个用逗号分隔                  |

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
  "images": "http://localhost:8080/images/20250420/image1.jpg,http://localhost:8080/images/20250420/image2.jpg"
}'
```

## 业务规则说明
1. 图片上传：
   - 支持jpg、jpeg、png等常见图片格式
   - 图片会按日期存储在服务器指定目录
   - 返回的图片URL可直接访问

2. 报修申请：
   - 报修类型为1（宿舍物品）时，必须填写roomId，不能填写publicArea
   - 报修类型为2（公共物品）时，必须填写publicArea，不能填写roomId
   - 系统会自动根据学生ID查询对应的宿舍楼ID和宿管ID
   - 图片地址支持多张图片，使用逗号分隔 
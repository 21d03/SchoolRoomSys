# 教师报修管理查询接口

## 请求URL
- `/teacher/repair/query`

## 请求方式
- POST

## 请求参数
```json
{
  "pageNum": 1,          // 页码，默认为1
  "pageSize": 10,        // 每页数量，默认为10
  "teacherId": "123456", // 教师ID（必填）
  "stuName": "",         // 学生姓名（可选，支持模糊查询）
  "repairType": "",      // 报修类型（可选，1-宿舍物品 2-公共物品）
  "roomId": "",          // 宿舍ID（可选，支持模糊查询）
  "itemName": ""         // 物品名称（可选，支持模糊查询）
}
```

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [
      {
        "id": "1001",                     // 报修申请单ID
        "studentId": "S20240001",         // 学生ID
        "stuName": "张三",                // 学生姓名
        "className": "计算机科学与技术1班", // 班级
        "repairType": "1",                // 报修类型（1-宿舍物品 2-公共物品）
        "repairItem": "101 水龙头",        // 报修物品
        "createTime": "2025-03-30 12:00:00" // 创建时间
      },
      {
        "id": "1002",
        "studentId": "S20240002",
        "stuName": "李四",
        "className": "计算机科学与技术1班",
        "repairType": "2",
        "repairItem": "一楼公共洗漱间 灯管",
        "createTime": "2025-03-29 10:30:00"
      }
    ],
    "total": 2,         // 总记录数
    "size": 10,         // 每页数量
    "current": 1,       // 当前页码
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "pages": 1          // 总页数
  }
}
```

## CURL示例
```bash
curl -X POST \
  http://localhost:8080/teacher/repair/query \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -d '{
  "pageNum": 1,
  "pageSize": 10,
  "teacherId": "123456",
  "stuName": "",
  "repairType": "",
  "roomId": "",
  "itemName": ""
}'
``` 
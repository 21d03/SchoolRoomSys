# 学生宿舍信息查询接口

## 请求URL
- `/student/dorm/info`

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
    "buildId": "1",           // 宿舍楼ID，学生所在宿舍楼ID
    "roomId": "101",          // 房间号，学生所在房间号
    "bedNo": "01",            // 床位号，学生所在床位号
    "hmName": "张宿管",       // 宿管姓名，宿舍楼管理员姓名
    "hmPhone": "13800138000", // 宿管电话，宿舍楼管理员联系电话
    "roommates": [            // 室友信息，同宿舍的其他学生信息
      {
        "studentId": "S20240002",  // 学生ID，室友的学生ID
        "stuName": "李四",         // 学生姓名，室友姓名
        "bedNo": "02",             // 床位号，室友的床位号
        "className": "计算机科学与技术1班", // 班级，室友所在专业和班级
        "phone": "13900139000"     // 手机号，室友的联系电话
      },
      {
        "studentId": "S20240003",
        "stuName": "王五",
        "bedNo": "03",
        "className": "软件工程2班",
        "phone": "13700137000"
      }
    ]
  }
}
```

## 返回参数说明
| 参数名      | 类型    | 说明                                  |
|------------|--------|--------------------------------------|
| buildId    | String | 宿舍楼ID，学生所在宿舍楼ID              |
| roomId     | String | 房间号，学生所在房间号                   |
| bedNo      | String | 床位号，学生所在床位号                   |
| hmName     | String | 宿管姓名，宿舍楼管理员姓名               |
| hmPhone    | String | 宿管电话，宿舍楼管理员联系电话            |
| roommates  | Array  | 室友信息，同宿舍的其他学生信息            |

### roommates对象
| 参数名      | 类型    | 说明                                  |
|------------|--------|--------------------------------------|
| studentId  | String | 学生ID，室友的学生ID                    |
| stuName    | String | 学生姓名，室友姓名                      |
| bedNo      | String | 床位号，室友的床位号                     |
| className  | String | 班级，室友所在专业和班级                 |
| phone      | String | 手机号，室友的联系电话                   |

## CURL示例
```bash
curl -X GET \
  'http://localhost:8080/student/dorm/info?studentId=S20240001' \
  -H 'Authorization: Bearer 您的TOKEN' \
  -H 'Content-Type: application/json'
``` 
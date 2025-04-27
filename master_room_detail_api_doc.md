# 宿管宿舍详情查询接口文档

## 接口说明
该接口用于宿管查询特定宿舍的详细信息，包括入住学生的详细信息、专业班级、联系方式、辅导员信息等。

## 接口信息
- **请求URL**: `/hm/room/detail`
- **请求方式**: GET
- **Content-Type**: application/x-www-form-urlencoded

## 请求参数
| 参数名 | 类型 | 是否必须 | 说明 |
| ---- | ---- | ---- | ---- |
| buildId | String | 是 | 宿舍楼ID |
| roomId | String | 是 | 宿舍号 |

## 返回结果

### 成功返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": {
        "roomId": "101",
        "buildId": "1",
        "roomType": "4",
        "occupiedCount": 3,
        "students": [
            {
                "stuId": "2023001001",
                "stuName": "张三",
                "bedNo": "1",
                "majorClass": "计算机学院 | 软件工程 | 软工2301",
                "phone": "13800138001",
                "teacherName": "王老师",
                "teacherId": "001002",
                "teacherPhone": "13900139000"
            },
            {
                "stuId": "2023001002",
                "stuName": "李四",
                "bedNo": "2",
                "majorClass": "计算机学院 | 软件工程 | 软工2301",
                "phone": "13800138002",
                "teacherName": "王老师",
                "teacherId": "001002",
                "teacherPhone": "13900139000"
            },
            {
                "stuId": "2023001003",
                "stuName": "王五",
                "bedNo": "3",
                "majorClass": "计算机学院 | 软件工程 | 软工2301",
                "phone": "13800138003",
                "teacherName": "王老师",
                "teacherId": "001002",
                "teacherPhone": "13900139000"
            }
        ]
    }
}
```

### 宿舍不存在返回示例
```json
{
    "code": 1,
    "msg": "宿舍不存在",
    "data": null
}
```

### 系统错误返回示例
```json
{
    "code": 1,
    "msg": "查询宿舍详细信息失败",
    "data": null
}
```

## curl请求示例
```bash
curl -X GET "http://localhost:8080/SchoolRoomSys/hm/room/detail?buildId=1&roomId=101" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

## 字段说明
| 字段名 | 说明 |
| ---- | ---- |
| roomId | 宿舍号 |
| buildId | 宿舍楼ID |
| roomType | 几人寝，来自room_build_details表的room_type字段 |
| occupiedCount | 已入住人数，根据room_info表中相同roomId和buildId的记录数计算 |
| students | 入住学生信息列表 |
| stuId | 学生学号，来自room_info表的stu_id字段 |
| stuName | 学生姓名，来自room_info表的stu_name字段 |
| bedNo | 床位号，来自room_info表的bed_no字段 |
| majorClass | 专业班级信息，根据student_info表的college、profession、class_room字段拼接而成 |
| phone | 学生联系电话，来自student_user表的phone字段 |
| teacherName | 辅导员姓名，来自student_info表的teacher_name字段 |
| teacherId | 辅导员ID，来自student_info表的teacher_id字段 |
| teacherPhone | 辅导员电话，来自school_user表的phone字段 |

## 注意事项
1. buildId和roomId参数都必须传递，否则会返回参数校验错误
2. 请确保buildId和roomId对应的宿舍存在，否则会返回"宿舍不存在"的错误信息
3. 学生信息按照床位号（bedNo）升序排列
4. 专业班级信息格式为："学院 | 专业 | 班级" 
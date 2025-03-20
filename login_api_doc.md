# 登录接口文档

## 接口说明
用于处理用户登录请求，支持学生和教师（包括普通教师和学校管理员）登录。

## 接口信息
- 请求URL: `/api/login`
- 请求方式: POST
- Content-Type: application/json

## 请求参数
```json
{
    "userId": "string",     // 用户ID，必填
    "password": "string",   // 密码，必填
    "userType": "string"    // 用户类型，必填，可选值：1-普通教师，2-学校管理员，3-学生
}
```

## 返回结果

### 1. 学生登录成功返回示例（userType=3）
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "userId": "2023001",           // 学生ID
        "userName": "zhangsan",        // 用户名
        "name": "张三",                // 姓名
        "phone": "13800138000",        // 联系方式
        "sex": "男",                   // 性别
        "collegeName": "信息技术学院",  // 所属学院
        "profession": "计算机科学与技术", // 专业
        "classRoom": "计科2301",       // 班级
        "teacherId": "0500001",        // 辅导员ID
        "teacherName": "彭会锋",       // 辅导员姓名
        "teacherPhone": "13900139000", // 辅导员联系方式
        "buildId": "1",               // 宿舍楼ID
        "roomId": "101",              // 房间号
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...", // JWT token
        "userType": "3"              // 用户类型
    }
}
```

### 2. 教师登录成功返回示例（userType=1或2）
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "userId": "0500001",           // 教师ID
        "userName": "penghuifeng",     // 用户名
        "name": "彭会锋",              // 姓名
        "phone": "13900139000",        // 联系方式
        "level": "1",                  // 用户级别 0-学校管理员 1-普通教师
        "buildId": "1",                // 所管理的宿舍楼ID（如果有）
        "campus": "1",                 // 所在校区 1梁园 2睢阳
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...", // JWT token
        "userType": "1"               // 用户类型 1-普通教师 2-学校管理员
    }
}
```

### 3. 登录失败返回示例
```json
{
    "code": 500,
    "message": "用户名或密码错误",
    "data": null
}
```

## CURL示例

### 1. 学生登录
```bash
curl -X POST 'http://localhost:8080/api/login' \
-H 'Content-Type: application/json' \
-d '{
    "userId": "2023001",
    "password": "123456",
    "userType": "3"
}'
```

### 2. 普通教师登录
```bash
curl -X POST 'http://localhost:8080/api/login' \
-H 'Content-Type: application/json' \
-d '{
    "userId": "0500001",
    "password": "123456",
    "userType": "1"
}'
```

### 3. 学校管理员登录
```bash
curl -X POST 'http://localhost:8080/api/login' \
-H 'Content-Type: application/json' \
-d '{
    "userId": "0000001",
    "password": "123456",
    "userType": "2"
}'
```

## 错误码说明
| 错误码 | 说明 |
|--------|------|
| 200    | 成功 |
| 400    | 请求参数错误 |
| 401    | 未授权（用户名或密码错误）|
| 500    | 服务器内部错误 |

## 注意事项
1. 所有请求必须包含Content-Type: application/json头
2. 登录成功后返回的token需要在后续请求中通过Authorization头传递
3. 密码传输使用明文，建议在生产环境使用HTTPS
4. userType必须使用正确的数字代码：1-普通教师，2-学校管理员，3-学生 
# 宿管端登录接口文档

## 接口说明
该接口用于宿管用户登录系统，验证用户的身份并提供访问权限。

## 接口信息
- **请求URL**: `/master/login`
- **请求方式**: POST
- **Content-Type**: application/json

## 请求参数
```json
{
    "userId": "string",    // 宿管ID，必填，对应house_master表的hm_id
    "password": "string"   // 密码，必填
}
```

## 返回结果

### 登录成功返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": {
        "userId": "SG001",           // 宿管ID，与house_master表的hm_id一致
        "userName": "张宿管",         // 宿管姓名，来自house_master表的hm_name
        "name": "张宿管",            // 宿管姓名，来自house_master表的hm_name
        "sex": "男",                // 性别，来自house_master表的hm_sex
        "phone": "13800138000",     // 联系方式，来自house_master表的hm_phone
        "buildId": "1",            // 管理的宿舍楼ID，来自house_master表的build_id
        "userType": "4",           // 用户类型：固定为"4"表示宿管
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9..." // JWT token
    }
}
```

### 登录失败返回示例
```json
{
    "code": 1,
    "msg": "用户名或密码错误",
    "data": null
}
```

```json
{
    "code": 1,
    "msg": "账户已被禁用",
    "data": null
}
```

```json
{
    "code": 1,
    "msg": "宿管信息不存在",
    "data": null
}
```

## curl请求示例
```bash
curl -X POST "http://localhost:8080/SchoolRoomSys/master/login" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "SG001",
    "password": "123456"
  }'
```

## 注意事项
1. 宿管账户必须处于启用状态(is_used=1)才能登录
2. 用户类型必须为宿管(type=1)
3. 登录时使用house_master表的hm_id作为登录账号
4. 返回的信息主要来自house_master表，通过hm_id关联查询

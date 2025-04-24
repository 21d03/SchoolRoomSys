# 宿管宿舍查询接口文档

## 接口说明
该接口用于宿管查询宿舍信息，支持按宿舍号查询或查询全部宿舍。

## 接口信息
- **请求URL**: `/hm/room/query`
- **请求方式**: GET
- **Content-Type**: application/x-www-form-urlencoded

## 请求参数
| 参数名 | 类型 | 是否必须 | 说明 |
| ---- | ---- | ---- | ---- |
| buildId | String | 是 | 宿舍楼ID |
| roomId | String | 否 | 宿舍号，不传则查询宿舍楼下所有宿舍 |

## 返回结果

### 成功返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": [
        {
            "roomId": "101",
            "roomType": "4",
            "occupiedCount": 3,
            "availableBeds": 1
        },
        {
            "roomId": "102",
            "roomType": "6",
            "occupiedCount": 5,
            "availableBeds": 1
        },
        {
            "roomId": "103",
            "roomType": "4",
            "occupiedCount": 4,
            "availableBeds": 0
        }
    ]
}
```

### 指定宿舍号返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": [
        {
            "roomId": "101",
            "roomType": "4",
            "occupiedCount": 3,
            "availableBeds": 1
        }
    ]
}
```

### 错误返回示例
```json
{
    "code": 1,
    "msg": "查询宿舍信息失败",
    "data": null
}
```

## curl请求示例
### 查询所有宿舍
```bash
curl -X GET "http://localhost:8080/SchoolRoomSys/hm/room/query?buildId=1" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

### 查询指定宿舍
```bash
curl -X GET "http://localhost:8080/SchoolRoomSys/hm/room/query?buildId=1&roomId=101" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

## 字段说明
| 字段名 | 说明 |
| ---- | ---- |
| roomId | 宿舍号 |
| roomType | 几人寝，来自room_build_details表的room_type字段 |
| occupiedCount | 已入住人数，根据room_info表中相同roomId和buildId的记录数计算 |
| availableBeds | 空余床位数，计算方式为：roomType - occupiedCount |

## 注意事项
1. buildId参数必须传递，否则会返回参数校验错误
2. 如果不传roomId参数，将返回该宿舍楼下所有宿舍的信息
3. 如果传递了roomId参数，将只返回指定宿舍的信息
4. 如果roomType不是数字或为空，将默认按4人寝计算 
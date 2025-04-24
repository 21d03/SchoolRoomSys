# 宿管首页接口文档

## 获取宿管首页统计数据

### 接口说明
获取宿管首页的统计数据，包括待处理报修数和房间总数。

### 请求URL
```
GET /hm/index/data
```

### 请求参数
| 参数名 | 类型 | 是否必须 | 说明 |
|--------|------|----------|------|
| hmId | String | 是 | 宿管ID |
| buildId | String | 是 | 宿舍楼ID |

### 返回结果
```json
{
    "code": 1,
    "msg": "success",
    "data": {
        "pendingRepairCount": 5,  // 待处理报修数
        "totalRoomCount": 100     // 房间总数
    }
}
```

### 返回参数说明
| 参数名 | 类型 | 说明 |
|--------|------|------|
| pendingRepairCount | Integer | 待处理报修数 |
| totalRoomCount | Integer | 房间总数 |

### curl示例
```bash
curl -X GET "http://localhost:8080/hm/index/data?hmId=HM001&buildId=B001"
```
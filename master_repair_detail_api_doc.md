# 宿管报修详情查询接口文档

## 接口说明
该接口用于宿管查询特定报修单的详细信息，包括报修内容、处理状态、图片等信息。图片会以Base64编码返回。

## 接口信息
- **请求URL**: `/hm/repair/detail`
- **请求方式**: GET
- **Content-Type**: application/x-www-form-urlencoded

## 请求参数
| 参数名 | 类型 | 是否必须 | 说明 |
| ---- | ---- | ---- | ---- |
| repairId | String | 是 | 报修单ID |

## 返回结果

### 成功返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": {
        "id": "RP20250401001",
        "studentId": "2023001001",
        "studentName": "张三",
        "repairType": "1",
        "roomId": "101",
        "publicArea": null,
        "itemName": "灯泡",
        "description": "寝室灯泡坏了，需要更换",
        "images": "repair/20250401/img1.jpg,repair/20250401/img2.jpg",
        "imageBase64List": [
            "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD...(省略Base64编码)",
            "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD...(省略Base64编码)"
        ],
        "hmId": "SG001",
        "hmName": "王宿管",
        "hmStatus": "0",
        "hmOpinion": null,
        "hmTime": null,
        "rpId": null,
        "rpName": null,
        "rpStatus": null,
        "rpOpinion": null,
        "rpTime": null,
        "createTime": "2025-04-01 08:30:00",
        "updateTime": "2025-04-01 08:30:00"
    }
}
```

### 报修单不存在返回示例
```json
{
    "code": 1,
    "msg": "报修单不存在",
    "data": null
}
```

### 系统错误返回示例
```json
{
    "code": 1,
    "msg": "查询报修单详情失败",
    "data": null
}
```

## curl请求示例
```bash
curl -X GET "http://localhost:8080/SchoolRoomSys/hm/repair/detail?repairId=RP20250401001" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

## 字段说明
| 字段名 | 说明 |
| ---- | ---- |
| id | 报修单号 |
| studentId | 学生ID |
| studentName | 学生姓名 |
| repairType | 报修类型：1-宿舍物品 2-公共物品 |
| roomId | 宿舍ID，宿舍物品报修时必填 |
| publicArea | 公共区域，公共物品报修时必填 |
| itemName | 物品名称 |
| description | 问题描述 |
| images | 图片路径字符串，多个路径以逗号分隔 |
| imageBase64List | 图片Base64编码列表，可直接用于前端显示 |
| hmId | 宿管ID |
| hmName | 宿管姓名 |
| hmStatus | 宿管审批状态：0-待审批 1-已通过 2-已拒绝 |
| hmOpinion | 宿管审批意见 |
| hmTime | 宿管审批时间 |
| rpId | 维修人员ID |
| rpName | 维修人员姓名 |
| rpStatus | 维修状态：0-待维修 1-已完成 2-已拒绝 |
| rpOpinion | 维修意见 |
| rpTime | 维修完成时间 |
| createTime | 创建时间 |
| updateTime | 更新时间 |

## 注意事项
1. 图片会以Base64格式编码并添加相应的MIME类型前缀，如 "data:image/jpeg;base64,..."，可直接在HTML的img标签src属性中使用
2. 根据报修类型不同，roomId或publicArea字段可能为null
3. 根据处理状态不同，hmOpinion、hmTime、rpId、rpName、rpStatus、rpOpinion、rpTime等字段可能为null 
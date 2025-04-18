# 报修审批单详情接口

## 请求URL
- `/api/repair/detail/{approvalId}`

## 请求方式
- GET

## 请求参数
- approvalId: 报修审批单ID（必填，路径参数）

## 返回示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": "RA20250416001",        // 主键ID，报修审批单唯一标识
    "studentId": "S20240001",     // 申请学生ID，提交报修申请的学生ID
    "studentName": "张三",        // 学生姓名，提交报修申请的学生姓名
    "repairType": "1",            // 报修类型，1-宿舍物品 2-公共物品
    "roomId": "101",              // 宿舍ID，宿舍物品报修时的宿舍ID
    "publicArea": null,           // 公共区域，公共物品报修时的区域描述
    "itemName": "水龙头",         // 物品名称，报修的物品名称
    "description": "水龙头漏水",  // 问题描述，报修物品的问题描述
    "images": "image1.jpg,image2.jpg", // 图片地址，报修上传的图片地址，多个用逗号分隔
    "hmId": "HM001",              // 宿管ID，处理该报修的宿管ID
    "hmName": "李宿管",           // 宿管姓名，处理该报修的宿管姓名
    "hmStatus": "1",              // 宿管审批状态，0-待审批 1-已通过 2-已驳回
    "hmOpinion": "已通过，请维修", // 宿管审批意见，宿管对报修的处理意见
    "hmTime": "2025-04-16 10:30:00", // 宿管审批时间，宿管处理报修的时间
    "rpId": "RP001",              // 维修人员ID，负责维修的人员ID
    "rpName": "王维修",           // 维修人员姓名，负责维修的人员姓名
    "rpStatus": "1",              // 维修状态，0-待维修 1-已完成 2-已驳回
    "rpOpinion": "已更换水龙头",   // 维修意见，维修人员的处理意见
    "rpTime": "2025-04-16 14:30:00", // 维修完成时间，维修完成的时间
    "createTime": "2025-04-16 09:00:00", // 创建时间，报修申请创建的时间
    "updateTime": "2025-04-16 14:30:00"  // 更新时间，报修申请最后更新的时间
  }
}
```

## 字段说明
| 字段名      | 类型            | 描述                                  |
|------------|----------------|-----------------------------------------|
| id         | String         | 主键ID，报修审批单唯一标识               |
| studentId  | String         | 申请学生ID，提交报修申请的学生ID         |
| studentName| String         | 学生姓名，提交报修申请的学生姓名         |
| repairType | String         | 报修类型，1-宿舍物品 2-公共物品          |
| roomId     | String         | 宿舍ID，宿舍物品报修时的宿舍ID           |
| publicArea | String         | 公共区域，公共物品报修时的区域描述        |
| itemName   | String         | 物品名称，报修的物品名称                 |
| description| String         | 问题描述，报修物品的问题描述             |
| images     | String         | 图片地址，报修上传的图片地址，多个用逗号分隔 |
| hmId       | String         | 宿管ID，处理该报修的宿管ID              |
| hmName     | String         | 宿管姓名，处理该报修的宿管姓名           |
| hmStatus   | String         | 宿管审批状态，0-待审批 1-已通过 2-已驳回  |
| hmOpinion  | String         | 宿管审批意见，宿管对报修的处理意见        |
| hmTime     | String         | 宿管审批时间，宿管处理报修的时间，格式：yyyy-MM-dd HH:mm:ss |
| rpId       | String         | 维修人员ID，负责维修的人员ID             |
| rpName     | String         | 维修人员姓名，负责维修的人员姓名          |
| rpStatus   | String         | 维修状态，0-待维修 1-已完成 2-已驳回      |
| rpOpinion  | String         | 维修意见，维修人员的处理意见              |
| rpTime     | String         | 维修完成时间，维修完成的时间，格式：yyyy-MM-dd HH:mm:ss |
| createTime | String         | 创建时间，报修申请创建的时间，格式：yyyy-MM-dd HH:mm:ss |
| updateTime | String         | 更新时间，报修申请最后更新的时间，格式：yyyy-MM-dd HH:mm:ss |

## CURL示例
```bash
curl -X GET \
  http://localhost:8080/api/repair/detail/RA20250416001 \
  -H 'Authorization: Bearer 您的TOKEN' \
  -H 'Content-Type: application/json'
``` 
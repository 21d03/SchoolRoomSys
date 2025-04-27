# 宿管报修单查询接口文档

## 接口说明
该接口用于宿管查询自己负责的报修单列表，包括待审批、已通过、已拒绝等各种状态的报修单。

## 接口信息
- **请求URL**: `/hm/repair/list`
- **请求方式**: GET
- **Content-Type**: application/x-www-form-urlencoded

## 请求参数
| 参数名 | 类型 | 是否必须 | 说明 |
| ---- | ---- | ---- | ---- |
| hmId | String | 是 | 宿管ID |

## 返回结果

### 成功返回示例
```json
{
    "code": 0,
    "msg": null,
    "data": [
        {
            "id": "RP20250401001",
            "studentId": "2023001001",
            "studentName": "张三",
            "content": "寝室灯泡坏了",
            "createTime": "2025-04-01 08:30:00",
            "hmStatus": "0",
            "rpStatus": null,
            "approverId": "SG001",
            "approverName": "王宿管",
            "hasUrge": true
        },
        {
            "id": "RP20250401002",
            "studentId": "2023001002",
            "studentName": "李四",
            "content": "浴室水管漏水",
            "createTime": "2025-04-01 09:15:00",
            "hmStatus": "0",
            "rpStatus": null,
            "approverId": "SG001",
            "approverName": "王宿管",
            "hasUrge": false
        },
        {
            "id": "RP20250331001",
            "studentId": "2023001003",
            "studentName": "王五",
            "content": "空调不制冷",
            "createTime": "2025-03-31 16:20:00",
            "hmStatus": "1",
            "rpStatus": "0",
            "approverId": "RP001",
            "approverName": "李维修",
            "hasUrge": false
        },
        {
            "id": "RP20250330001",
            "studentId": "2023001004",
            "studentName": "赵六",
            "content": "门锁坏了",
            "createTime": "2025-03-30 14:10:00",
            "hmStatus": "1",
            "rpStatus": "1",
            "approverId": "RP001",
            "approverName": "李维修",
            "hasUrge": false
        },
        {
            "id": "RP20250329001",
            "studentId": "2023001005",
            "studentName": "孙七",
            "content": "窗户无法关闭",
            "createTime": "2025-03-29 10:45:00",
            "hmStatus": "1",
            "rpStatus": "2",
            "approverId": "RP001",
            "approverName": "李维修",
            "hasUrge": false
        },
        {
            "id": "RP20250328001",
            "studentId": "2023001006",
            "studentName": "周八",
            "content": "报修申请不合理",
            "createTime": "2025-03-28 09:30:00",
            "hmStatus": "2",
            "rpStatus": null,
            "approverId": "SG001",
            "approverName": "王宿管",
            "hasUrge": false
        }
    ]
}
```

### 错误返回示例
```json
{
    "code": 1,
    "msg": "查询报修单列表失败",
    "data": null
}
```

## curl请求示例
```bash
curl -X GET "http://localhost:8080/SchoolRoomSys/hm/repair/list?hmId=SG001" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

## 字段说明
| 字段名 | 说明 |
| ---- | ---- |
| id | 报修单号，来自repair_approval表的id字段 |
| studentId | 提交人学号，来自repair_approval表的student_id字段 |
| studentName | 提交人姓名，来自student_info表的stu_name字段 |
| content | 报修描述，来自repair_approval表的description字段 |
| createTime | 报修时间，来自repair_approval表的create_time字段 |
| hmStatus | 宿管审批状态：0-待审批 1-已通过 2-已拒绝，来自repair_approval表的hm_status字段 |
| rpStatus | 维修人员审批状态：0-待审批 1-已完成 2-拒绝，来自repair_approval表的rp_status字段 |
| approverId | 当前审批人ID，根据hmStatus决定：<br>- hmStatus为0或2时，为宿管ID(hm_id)<br>- hmStatus为1时，为维修人员ID(rp_id) |
| approverName | 当前审批人姓名，根据hmStatus决定：<br>- hmStatus为0或2时，为宿管姓名(hm_name)<br>- hmStatus为1时，为维修人员姓名(rp_name) |
| hasUrge | 是否有催单，通过查询urge_record表中是否存在对应approval_id的记录来判断 |

## 排序规则
报修单列表按照以下优先级排序：
1. hm_status=0且有催单记录的报修单优先显示
2. hm_status=0（宿管待审批）的报修单
3. hm_status=1且rp_status=0（维修人员待审批）的报修单
4. hm_status=1且rp_status=1（维修人员已完成）的报修单
5. hm_status=1且rp_status=2（维修人员已拒绝）的报修单
6. hm_status=2（宿管已拒绝）的报修单
7. 同等优先级的报修单按报修时间降序排列（新的在前） 
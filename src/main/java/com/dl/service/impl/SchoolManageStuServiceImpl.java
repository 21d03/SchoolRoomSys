package com.dl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.context.BaseContext;
import com.dl.entity.dto.StudentInfoDTO;
import com.dl.entity.pojo.*;
import com.dl.mapper.RoomInfoMapper;
import com.dl.mapper.SchoolManageStuMapper;
import com.dl.mapper.SchoolUserMapper;
import com.dl.mapper.StudentUserMapper;
import com.dl.result.Result;
import com.dl.service.SchoolManageStuService;
import com.dl.utils.SpellUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dongliang
 * @date 2024/09/23 21:42:42
 * @description
 **/
@Service
@Slf4j
public class SchoolManageStuServiceImpl implements SchoolManageStuService {

    @Resource
    private SchoolManageStuMapper schoolManageStuMapper;

    @Resource
    private SchoolUserMapper schoolUserMapper;

    @Resource
    private StudentUserMapper studentUserMapper;

    @Resource
    private RoomInfoMapper roomInfoMapper;

    // 在类中添加一个BCryptPasswordEncoder实例 用于新增用户是默认密码为123456的加密
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 执行分页查询的方法
     *
     * @param pageIndex 期望查询的页码索引
     * @param pageSize  每页期望包含的记录数
     * @return 返回一个Result对象，其中包含分页查询的结果
     * <p>
     * 该方法实现了两种分页查询的机制：PageHelper和mybatis-plus
     * 当前使用的是mybatis-plus分页方式
     */
    @Override
    public Result<Page> pageQuery(Integer pageIndex, Integer pageSize) {
        // 默认页面索引为1，如果传入的pageIndex为null，则使用默认值1
        pageIndex = pageIndex == null ? 1 : pageIndex;
        // 默认页面大小为10，如果传入的pageSize为null，则使用默认值10
        pageSize = pageSize == null ? 10 : pageSize;
        log.info("分页查询学生{}，{}", pageIndex, pageSize);
        // 使用PageHelper进行分页查询的代码示例（已注释）
        // PageHelper.startPage(pageIndex,pageSize);
        // Page<StudentInfo> studentInfoPage = schoolManageStuMapper.pageQuery();
        // return new PageResult(studentInfoPage.getTotal(),studentInfoPage.getResult());

        // 使用mybatis-plus进行分页查询
        // 创建一个Page对象，传入当前页码和每页大小
        Page<StudentInfo> page = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<StudentInfo> wrapperByStudentInfo = new LambdaQueryWrapper<>();

        //根据用户id获取用户级别 是学校还是学院老师
        LambdaQueryWrapper<SchoolUser> wrapperBySchoolUser = new LambdaQueryWrapper<>();
        wrapperBySchoolUser.select(SchoolUser::getLevel).eq(SchoolUser::getUserId, BaseContext.getCurrentId());
        log.info("用户id{}", BaseContext.getCurrentId());
        SchoolUser schoolUser = schoolUserMapper.selectOne(wrapperBySchoolUser);
        if (!schoolUser.getLevel().equals("0")) {
            wrapperByStudentInfo.eq(StudentInfo::getTeacherId, BaseContext.getCurrentId());
        }

        // 调用mapper的分页查询方法，并传入分页对象
        Page<StudentInfo> studentInfoPage = schoolManageStuMapper.selectPage(page, wrapperByStudentInfo);
        // 返回分页查询结果，封装在Result对象中表示成功
        return Result.success(studentInfoPage);
    }

    @Override
    public Result saveOne(StudentInfoDTO studentInfoDto) {
        log.info("新增学生信息:{}", studentInfoDto);
        StudentInfo studentInfo = BeanUtil.copyProperties(studentInfoDto, StudentInfo.class);

        //用于接收插入操作的返回值
        int studentInfoInsert = 0;
        int studentUserInsert = 0;
        int roomInfoInsert = 0;

        try {
            //先判断各个需要insert的表中是否有信息 如果为空进行插入  不为空就跳过   如果几个表都不为空  返回错误信息
            if (schoolManageStuMapper.selectOne(new LambdaQueryWrapper<StudentInfo>().select(StudentInfo::getStuId).eq(StudentInfo::getStuId, studentInfo.getStuId())) != null) {
                log.info("学号重复");
                return Result.error("学号重复");
            } else {
                // 保存到student_info表
                studentInfoInsert = schoolManageStuMapper.insert(studentInfo);
                if (studentInfoInsert == 1) {
                    log.info("学生信息插入成功:{}", studentInfo);
                }
            }

            if (studentUserMapper.selectOne(new LambdaQueryWrapper<StudentUser>().select(StudentUser::getUserId).eq(StudentUser::getUserId, studentInfo.getStuId())) == null) {
                //保存信息到student_user表  如果学号信息重复，那么在上一步就会退出，不再进入到这个表，所有这里不再对重复做出操作，跳过
                StudentUser studentUser = BeanUtil.copyProperties(studentInfoDto, StudentUser.class);
                studentUser.setUserId(studentInfo.getStuId());
                studentUser.setUserName(SpellUtil.toPinyin(studentInfo.getStuName()));
                studentUser.setPassWord(passwordEncoder.encode("123456"));
                studentUser.setName(studentInfo.getStuName());
                studentUserInsert = studentUserMapper.insert(studentUser);
                if (studentUserInsert == 1) {
                    log.info("学生用户信息插入成功:{}", studentUser);
                }
            }

            // 检查指定宿舍楼的指定房间的指定床位是否已经有人
            RoomInfo existingRoomInfo = roomInfoMapper.selectOne(new LambdaQueryWrapper<RoomInfo>()
                    .eq(RoomInfo::getBuildId, studentInfo.getBuildId())
                    .eq(RoomInfo::getRoomId, studentInfo.getRoomId())
                    .eq(RoomInfo::getBedNo, studentInfoDto.getBedNo()));
            
            if (existingRoomInfo != null) {
                log.info("该床位已经被占用: 宿舍楼{}, 房间号{}, 床位号{}", studentInfo.getBuildId(), studentInfo.getRoomId(), studentInfoDto.getBedNo());
                //删除上边执行过的插入操作
                schoolManageStuMapper.deleteById(studentInfo.getStuId());
                studentUserMapper.deleteById(studentInfo.getStuId());
                return Result.error("该床位已经被占用，请选择其他床位");
            }

            if (roomInfoMapper.selectById(studentInfo.getStuId()) != null) {
                log.info("该学生已经分配了宿舍");
                //删除上边执行过的插入操作
                schoolManageStuMapper.deleteById(studentInfo.getStuId());
                studentUserMapper.deleteById(studentInfo.getStuId());
                return Result.error("该学生已经分配了宿舍");
            } else {
                //保存信息到room_info表
                roomInfoInsert = roomInfoMapper.insert(BeanUtil.copyProperties(studentInfoDto, RoomInfo.class));
                if (roomInfoInsert == 1) {
                    log.info("学生宿舍信息插入成功:{}", studentInfo);
                }
            }

            return Result.success("新增成功");
        } catch (Exception e) {
            log.info("新增学生信息失败:{}", e.getMessage());
            // 报错异常 删除上边执行过的插入操作
            schoolManageStuMapper.deleteById(studentInfo.getStuId());
            studentUserMapper.deleteById(studentInfo.getStuId());
            roomInfoMapper.deleteById(studentInfo.getStuId());
            log.info("新增失败，删除信息");
            return Result.error(e.getMessage());
        }
    }
}

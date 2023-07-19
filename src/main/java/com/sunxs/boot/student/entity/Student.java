package com.sunxs.boot.student.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;

import com.sunxs.boot.framework.annotation.EnumValues;
import com.sunxs.boot.framework.annotation.Excel;
import com.sunxs.boot.framework.enums.GenderEnum;
import lombok.Data;

/**
 * (Student)表实体类
 *
 * @author 孙先生
 * @since 2023-07-05 17:28:39
 */
@Data
public class Student extends Model<Student> {
    //学号
    @Excel(name = "序号",type = Excel.Type.ALL,isIndex = true)
    private Integer id;
    //姓名
    @Excel(name = "姓名")
    private String name;
    //联系方式
    @Excel(name = "联系方式")
    private String phone;
    //班级
    @Excel(name = "班级")
    private String clazz;
    //学院
    @Excel(name = "学院")
    private String college;
    // 性别
    @Excel(name = "性别",readConverterExp = "0=男,1=女,2=未知")
    @EnumValues
    private GenderEnum gender;
    //有效标志1-有效，0-无效
    @Excel(name = "有效标志1",readConverterExp = "0=有效,1=无效")
    private Integer delFlag;
    //添加时间
    @Excel(name = "添加时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //修改时间
    @Excel(name = "修改时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}


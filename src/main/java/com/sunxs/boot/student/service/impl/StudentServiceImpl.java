package com.sunxs.boot.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunxs.boot.student.entity.Student;
import com.sunxs.boot.student.mapper.StudentMapper;
import com.sunxs.boot.student.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * (Student)表服务实现类
 *
 * @author 孙先生
 * @since 2023-07-05 17:28:39
 */
@Service("studentService" )
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}


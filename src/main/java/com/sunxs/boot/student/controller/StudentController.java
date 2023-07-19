package com.sunxs.boot.student.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunxs.boot.framework.enums.SysLogEnum;
import com.sunxs.boot.student.entity.Student;
import com.sunxs.boot.framework.annotation.SysLog;
import com.sunxs.boot.framework.util.poi.ExcelUtil;
import com.sunxs.boot.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sunxs.boot.framework.response.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;


/**
 * (Student)表控制层
 *
 * @author 孙先生
 * @since 2023-07-05 17:28:39
 */
@RestController
@RequestMapping("student")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class StudentController {
    /**
     * 服务对象
     */
    private final StudentService studentService;

    /**
     * 通过分页查询数据
     *
     * @param page
     * @param student 实体对象
     * @return 分页数据
     */
    @GetMapping("page")
    @SysLog(value = "学生列表查询",type = SysLogEnum.LIST)
    public Result<Page<Student>> page(Page<Student> page, Student student) {
        return Result.success(this.studentService.page(page, new QueryWrapper<>(student)));
    }


    /**
     * 通过导出数据
     */
    @GetMapping("export")
    @SysLog(value = "学生信息导出",type = SysLogEnum.EXPORT)
    public void export(HttpServletResponse response, HttpServletRequest request) {
        List<Student> list = this.studentService.list();
        ExcelUtil<Student> util = new ExcelUtil<>(Student.class);
        util.exportExcel(response,list, "学生信息表", "学生表");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @SysLog(value = "学生查询",type = SysLogEnum.INFO)
    public Result<Student> info(@PathVariable Serializable id) {
        return Result.success(this.studentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping
    @SysLog(value = "学生信息新增",type = SysLogEnum.ADD)
    public Result<Boolean> insert(@RequestBody Student student) {
        return Result.success(this.studentService.save(student));
    }

    /**
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping
    @SysLog(value = "学生信息修改",type = SysLogEnum.UPDATE)
    public Result<Boolean> update(@RequestBody Student student) {
        return Result.success(this.studentService.updateById(student));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @SysLog(value = " 删除学生信息",type = SysLogEnum.DELETE)
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.studentService.removeByIds(idList));
    }
}


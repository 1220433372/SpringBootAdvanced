package com.sunxs;

import com.sunxs.boot.student.entity.Student;
import com.sunxs.boot.framework.util.PropertyColumnUtil;
import com.sunxs.boot.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
@Slf4j
class SpringBootAdvancedApplicationTests {
    @Autowired
    StudentService studentService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        Map<String, String> propertyColumnMap = PropertyColumnUtil.getPropertyColumnMap(Student.class);
        System.out.println(propertyColumnMap);
        //stringRedisTemplate.opsForValue().setIfAbsent("sunxs", "孙先生",5,  TimeUnit.SECONDS);
        //log.info("hello ...");
        //System.out.println(winPerson);
        //System.out.println(linuxPerson);
//        Page<Student> page = studentService.page(new Page<>(1,2), null);
//        page.getRecords().forEach(System.out::println);

    }

}

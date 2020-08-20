package com.example.ehcachedemo;

import com.example.ehcachedemo.dao.UserRepository;
import com.example.ehcachedemo.pojo.Student;
import com.example.ehcachedemo.pojo.User;
import com.example.ehcachedemo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EhcacheDemoApplication.class)
public class TestDemo {
    @Autowired
    private StudentService studentService;

    @Test
    public void test() throws Exception {
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }
}

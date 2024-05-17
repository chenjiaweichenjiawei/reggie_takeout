package com.cjw.reggie_takeout;

import com.cjw.reggie_takeout.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class ReggieTakeoutApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}

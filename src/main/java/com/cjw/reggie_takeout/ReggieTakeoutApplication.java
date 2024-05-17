package com.cjw.reggie_takeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author CJW
 * @date 2023/3/28
 */
@EnableTransactionManagement
@SpringBootApplication
public class ReggieTakeoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeoutApplication.class, args);
    }

}

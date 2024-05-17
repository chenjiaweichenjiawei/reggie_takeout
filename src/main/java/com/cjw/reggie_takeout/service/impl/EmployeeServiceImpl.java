package com.cjw.reggie_takeout.service.impl;

import com.cjw.reggie_takeout.pojo.Employee;
import com.cjw.reggie_takeout.mapper.EmployeeMapper;
import com.cjw.reggie_takeout.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author CJW
 * @date 2023/3/28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

package com.cjw.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.reggie_takeout.pojo.Employee;
import com.cjw.reggie_takeout.service.EmployeeService;
import com.cjw.reggie_takeout.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author CJW
 * @date 2023/3/28
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param employee
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        //根据用户名查询对象
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", employee.getUsername());
        Employee foundEmployee = employeeService.getOne(queryWrapper);
        //如果没有查询到返回登陆失败结果
        if (foundEmployee == null) {
            return Result.error("登录失败");
        }
        //验证密码
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        if (!foundEmployee.getPassword().equals(password)) {
            return Result.error("登陆失败");
        }
        //判断用户状态
        if (foundEmployee.getStatus() == 0) {
            return Result.error("用户已被禁用");
        }
        //登陆成功
        HttpSession session = request.getSession();
        session.setAttribute("employee", employee.getId());
        return Result.success(foundEmployee);
    }

    /**
     * 员工退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        //清理session中的employee值
        HttpSession session = request.getSession();
        session.removeAttribute("employee");
        return Result.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Employee employee) {
        //设置初始密码为123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return Result.success("新增员工成功");
    }

    /**
     * 分页查询员工信息
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page<Employee>> getPage(int page, int pageSize, String name) {
        //构造分页构造器
        Page<Employee> employeePage = new Page<>(page, pageSize);
        //构造条件构造器
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(name != null, "name", name);
        queryWrapper.orderByDesc("update_time");
        //执行查询
        employeeService.page(employeePage, queryWrapper);
        return Result.success(employeePage);
    }

    /**
     * 根据id修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Employee employee) {
        System.out.println(employee);
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", employee.getId());
        employeeService.update(employee, updateWrapper);
        return Result.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        }
        return Result.error("没有查询到员工信息");
    }
}




























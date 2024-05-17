package com.cjw.reggie_takeout.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjw.reggie_takeout.pojo.User;
import com.cjw.reggie_takeout.service.UserService;
import com.cjw.reggie_takeout.util.Result;
import com.cjw.reggie_takeout.util.SMSUtils;
import com.cjw.reggie_takeout.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author CJW
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sengMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();
        if(phone!=null){
            //生成随即四位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用短信服务API
            //SMSUtils.sendMessage();
            System.out.println(code);
            session.setAttribute(phone,code);
            return Result.success("手机验证码短信发送成功");
        }
        return Result.error("验证码发送失败");
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map,HttpSession session){
        //获取手机号
        String phone=map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        Object sessionCode = session.getAttribute(phone);
        if(sessionCode!=null&&sessionCode.equals(code)){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.eq("phone",phone);
            User user = userService.getOne(userQueryWrapper);
            if(user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            return Result.success(user);
        }
        return Result.error("登陆失败");
    }
}

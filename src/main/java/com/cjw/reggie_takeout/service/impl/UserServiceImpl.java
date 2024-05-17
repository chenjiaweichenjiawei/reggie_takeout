package com.cjw.reggie_takeout.service.impl;

import com.cjw.reggie_takeout.pojo.User;
import com.cjw.reggie_takeout.mapper.UserMapper;
import com.cjw.reggie_takeout.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author CJW
 * @since 2023-04-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

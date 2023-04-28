package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

/**
 * @author xzwnp
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-11-08 19:41:14
 */
public interface UserService extends IService<User> {
    User findUserByUsername(String username);

    String login(String username, String password);

}

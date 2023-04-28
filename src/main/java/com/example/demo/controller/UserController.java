package com.example.demo.controller;

import com.example.demo.entity.JwtUser;
import com.example.demo.service.UserService;
import com.example.demo.util.R;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * com.example.demo.controller
 *
 * @author xiaozhiwei
 * 2022/11/1
 * 19:38
 */
@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public R login(String username, String password) {
        System.out.println("收到登录请求");
        //获取当前user
        String token = userService.login(username, password);
        return R.success(token);
    }

    @GetMapping("checkLogin")
    public R<String> hello2() {
        System.out.println("您已经登录!");
        JwtUser jwtUser = (JwtUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return R.success("您已经登录!您好," + jwtUser.getNickname());
    }

    @GetMapping("add")
    @PreAuthorize("hasAuthority('add')")
    public R<String> addPermission() {
        return R.success("拥有添加权限!");
    }

    @GetMapping("select")
    @Secured(value = "select")
    @PreAuthorize("hasAuthority('select')")
    public R<String> selectPermission() {
        return R.success("拥有查询权限!");
    }
}

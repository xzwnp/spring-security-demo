package com.example.demo.config;

import com.example.demo.entity.JwtUser;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * com.example.demo.config
 * 没啥用其实 凑数的
 *
 * @author xzwnp
 * 2022/12/14
 * 17:03
 */
@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {


    /**
     * 根据用户名,查数据库,生成用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailService:加载用户信息中");
        throw new RuntimeException("不支持");
    }
}

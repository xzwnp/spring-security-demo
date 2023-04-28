package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtEntity;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * com.example.shirojwtdemo.service.impl
 *
 * @author xiaozhiwei
 * 2022/11/28
 * 15:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, IService<User> {
    private RoleService roleService;
    private PermissionService permissionService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleService roleService, PermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User findUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 网上的教程都是调UserDetailService进行认证,个人认为没必要,自己认证就行了,能更细粒度地处理错误信息
     * 此外,微服务环境下UserDao只在用户模块,UserDetailService是要放Common模块的
     */
    @Override
    public String login(String username, String password) {
        //1.用户名密码检验
        //username和password如果为空,controller背锅
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        //bcypt每次加密生成的密码都不一样,需要使用专门的match方法来比对
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码错误!");
        }
        //2.登录成功,查用户权限,生成token
        JwtEntity jwtEntity = new JwtEntity();
        jwtEntity.setUserId(user.getId());
        jwtEntity.setUserName(user.getUsername());
        jwtEntity.setNickName(user.getNickname());
        //2.1 查角色
        List<Role> roles = roleService.findRoleByUserId(user.getId());
        List<Integer> roleIdList = roles.stream().map(Role::getId).collect(Collectors.toList());
        //2.2 查权限
        List<String> permissionList = permissionService.findPermissionsByRoleIds(roleIdList);
        jwtEntity.setPermissions(permissionList);

        return JwtUtil.TOKEN_PREFIX + JwtUtil.createJwtToken(jwtEntity);
    }
}

package com.example.demo.entity;

import com.example.demo.util.JwtEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring security使用的Subject类,实现了UserDetails接口
 */
public class JwtUser implements UserDetails {

    private User user;


    private List<SimpleGrantedAuthority> permissionList;

    public JwtUser(User user, List<String> permissionList) {
        this.user = user;
        this.permissionList = permissionList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public JwtUser(JwtEntity jwtEntity) {
        user = new User();
        user.setId(jwtEntity.getUserId());
        user.setNickname(jwtEntity.getNickName());
        user.setUsername(jwtEntity.getUserName());
        this.permissionList = jwtEntity.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public String getNickname() {
        return user.getNickname();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期
     * todo
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * todo
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * todo
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     * todo
     **/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
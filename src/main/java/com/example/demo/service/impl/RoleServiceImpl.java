package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzwnp
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2022-11-08 19:41:14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        return baseMapper.findRoleByUserId(userId);
    }
}





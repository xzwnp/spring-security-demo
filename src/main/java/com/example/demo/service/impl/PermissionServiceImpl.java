package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzwnp
 * @description 针对表【permission】的数据库操作Service实现
 * @createDate 2022-11-08 19:41:14
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Override
    public List<String> findPermissionsByRoleIds(List<Integer> roleIds) {
        return baseMapper.findbyRoleIds(roleIds);
    }

}





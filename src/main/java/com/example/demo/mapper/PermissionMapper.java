package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xzwnp
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2022-11-08 19:41:14
* @Entity com.example.demo.entity.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> findbyRoleIds(@Param("roleIds") List<Integer> roleIds);

}





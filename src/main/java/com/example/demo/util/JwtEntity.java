package com.example.demo.util;

import com.example.demo.entity.JwtUser;
import lombok.Data;

import java.util.List;

/**
 * com.example.shirojwtdemo.util
 *  jwtEntity不要和JwtUser混用,JwtEntity应该尽可能小,不要有多余字段
 * @author xiaozhiwei
 * 2022/11/28
 * 15:38
 */
@Data
public class JwtEntity {
    private Integer userId;
    private String userName;
    private String nickName;
    private List<String> permissions;

}

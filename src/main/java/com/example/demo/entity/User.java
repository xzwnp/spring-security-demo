package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId
    private Integer id;

    /**
     *
     */
    private String nickname;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String username;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
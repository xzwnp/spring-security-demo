package com.example.demo.controller;

import com.example.demo.util.R;
import com.example.demo.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * com.example.demo.controller
 *
 * @author xiaozhiwei
 * 2022/11/28
 * 17:14
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {


    //权限验证错误
    @ExceptionHandler(AccessDeniedException.class)
    public R authenticationExceptionHandler(AccessDeniedException e) {
        log.error("没有权限");
        log.error(e.getLocalizedMessage());

        return R.error(ResponseEnum.UNAUTHORIZED, "您没有权限访问!");
    }

    //对应路径不存在
    @ExceptionHandler(NoHandlerFoundException.class)
    public R noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.error("请求路径错误");
        log.error(e.getLocalizedMessage());
        return R.error(ResponseEnum.PATH_NOT_EXIST);
    }

    //其他错误
    @ExceptionHandler(Exception.class)
    public R defaultExceptionHandler(Exception e) {
        log.error("", e);
        return R.error(ResponseEnum.ERROR);
    }
}



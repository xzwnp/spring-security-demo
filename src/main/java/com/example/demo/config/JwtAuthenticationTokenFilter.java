package com.example.demo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.entity.JwtUser;
import com.example.demo.util.JwtEntity;
import com.example.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 仅根据token设置用户信息,无论是否有token,token是否有效,都应该放行(因为有的请求不需要登录)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //请求头的key为Authorization,value前缀为Bearer
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);
        if (!(StringUtils.isNotBlank(authHeader) && authHeader.startsWith(JwtUtil.TOKEN_PREFIX))) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String authToken = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
        //校验token 无效直接结束,让用户早点重新登录
        if (!JwtUtil.checkToken(authToken)) {
            return;
        }

        JwtEntity jwtEntity = JwtUtil.getUserInfo(authToken);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(new JwtUser(jwtEntity), null, null);
        //关键 设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //方向
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
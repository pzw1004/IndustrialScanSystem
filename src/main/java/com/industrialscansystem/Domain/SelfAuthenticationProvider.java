package com.industrialscansystem.Domain;

import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    SelfUserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userName = (String) authentication.getPrincipal(); //此处调用userDetailsService接口的loadUserByUsername方法获取UserDetails用户信息，一定注意定义的账号密码写法
        // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials();
        // 这个是表单中输入的密码；


        //userDetailsService.loadUserByUsername(userName);

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encodePwd = md5PasswordEncoder.encodePassword(password, userName);
        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
        if (!userInfo.getPassword().equals(encodePwd)) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }
        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}

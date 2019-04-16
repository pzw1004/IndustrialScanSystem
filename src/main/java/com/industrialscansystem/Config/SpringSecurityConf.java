
package com.industrialscansystem.Config;

import com.industrialscansystem.Domain.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){//注册UserDetailsService的bean
        return  new CustomUserService();
    }

   @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint; //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

   @Autowired
   AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    //@Autowired
   //AjaxLogoutSuccessHandler  logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）


//    @Autowired
//    SelfAuthenticationProvider provider; // 自定义安全认证
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        // 加入自定义的安全认证
//        auth.authenticationProvider(provider);
//
//
//
//    }
//  @Override  //这是custom之后加入的内容
//  protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//     auth.userDetailsService(customUserService()); ////user Details Service验证
//    }


    @Override
  //  @CrossOrigin(origins = "*", maxAge = 3600)
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                //.antMatchers("/Directories", "/welcome/employees", "/welcome/employee/find").access("hasRole('ROLE_pingpianyuan')")
                .antMatchers("/memberList").access("hasRole('ROLE_admin')")
                .anyRequest()
                .authenticated()//其他url需要身份认证
                .and()
                .formLogin() //开启登录
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler) //登录成功
                //.loginProcessingUrl("/main")
                .defaultSuccessUrl("/main")
                .failureHandler(authenticationFailureHandler) //登录失败

                .permitAll() //打开注释允许所有
                ;




        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        http.logout()
                  //        触发注销操作的URL  可以参考https://blog.csdn.net/zhong_csdn/article/details/79448313


        .logoutUrl("/user/logout")
                  //        注销成功后跳转的URL
        .logoutSuccessUrl("/login")

                  //       指定是否在注销时让httpsession无效
        .invalidateHttpSession(true);



    }



}

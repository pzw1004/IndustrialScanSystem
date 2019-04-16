package com.industrialscansystem.Domain;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Bean.Role;
import com.industrialscansystem.Bean.Usrole;
import com.industrialscansystem.respository.MemberRespository;
import com.industrialscansystem.respository.RoleRespository;
import com.industrialscansystem.respository.UsroleRespository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SelfUserDetailsService implements UserDetailsService {


    @Autowired
    MemberRespository memberRespository;

    @Autowired
    UsroleRespository usroleRespository;

    @Autowired
    RoleRespository roleRespository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //构建用户信息的逻辑(取数据库/LDAP等用户信息)
        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username);
        // 任意用户名登录

            Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

        Member member = memberRespository.findMemberByMember_username(username);
        Usrole usrole = usroleRespository.findUsroleIdByMember_role_id(member.getMember_role());
        Role role = roleRespository.findRoleNameByRole_role_id(usrole.getUsrole_role_id());


        if(member == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
            //String encodePassword = md5PasswordEncoder.encodePassword("123", username);
        String encodePassword = md5PasswordEncoder.encodePassword(member.getMember_password(), username);
            // 模拟从数据库中获取的密码原为 123
            userInfo.setPassword(encodePassword);
            Set authoritiesSet = new HashSet();
           // GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
         GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole_name());
            // 模拟从数据库中获取用户角色
            authoritiesSet.add(authority);

            member.getRoles();


            userInfo.setAuthorities(authoritiesSet);



            return userInfo;



    }

}




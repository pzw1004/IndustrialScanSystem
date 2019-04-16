package com.industrialscansystem.Domain;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Bean.Role;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {//自定义UserDetailsService 接口

    @Autowired
    MemberRespository memberRespository;



 @Override
    public UserDetails loadUserByUsername(String member_name){  //重写loadUserByUsername 方法获得 userdetails 类型用户

     Member member = memberRespository.findByUserName(member_name);

     if(member == null){
         throw new UsernameNotFoundException("用户名不存在");
     }

     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
     //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。

     Role role = new Role();
     authorities.add(new SimpleGrantedAuthority(role.getRole_name()));


     return  new org.springframework.security.core.userdetails.User(member.getMember_username(),
             member.getMember_password(),authorities);

 }

}



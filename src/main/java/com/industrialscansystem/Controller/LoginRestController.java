package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {


     @Autowired
    private MemberRespository memberRespository;

     @RequestMapping(value = "/login")
    public Member getLoginMember(@RequestBody Member member){

         Member userInfo = memberRespository.findMemberUserMsg(member.getMember_username(),member.getMember_password());
         return userInfo;
     }

}

package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalPageRestController {

    @Autowired
    private MemberRespository memberRespository;

    @RequestMapping(value = "/getPersonalInfo")
    public Member getPersonalInfo(@RequestParam("member_id")Integer member_id){
        Member member = memberRespository.getMemberById(member_id);
        return member;
    }

    @RequestMapping(value = "/updatePersonalInfo")
    public Member updatePersonalInfo(@RequestBody Member member,
                                     @RequestParam("member_id")Integer member_id){
        member.setMember_id(member_id);
        memberRespository.save(member);
        return member;
    }


}

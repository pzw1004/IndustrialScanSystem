package com.industrialscansystem.Controller;


import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.MemberRespository;
import com.industrialscansystem.util.LogUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@RestController
public class MemberManageRestController {

    @Autowired
    private MemberRespository memberRespository;

    @GetMapping(value = "/getMemberList")
    public Object getMemberList( ){

        List<Member> memberList = memberRespository.findAll();

        return memberList;
    }

    @RequestMapping(value = "/addMember" ,method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object addMemberInfo(@RequestBody Member member){
        Member tempmember2 = memberRespository.findMemberByusername(member.getMember_username());
        if(tempmember2==null) {
            long l = System.currentTimeMillis();
            Date time = new Date(l);

            Member tempMember = new Member();
            tempMember.setMember_name(member.getMember_name());
            tempMember.setMember_phone(member.getMember_phone());
            tempMember.setMember_sex(member.getMember_sex());
            tempMember.setMember_jointime(time);
            tempMember.setMember_role(member.getMember_role());
            tempMember.setMember_number(member.getMember_number());
            tempMember.setMember_username(member.getMember_username());
            tempMember.setMember_password(member.getMember_password());
            tempMember.setMember_address(member.getMember_address());

            memberRespository.save(tempMember);

            List<Member> memberList = memberRespository.findAll();

            return memberList;

        }else {
            return null;
        }

    }

    @RequestMapping(value = "/deleteMember")
    public Object deleteMember(@RequestParam("member_id") Integer member_id){


        memberRespository.deleteMemberById(member_id);
        List<Member> memberList = memberRespository.findAll();
        //根据网页端操作，在log日志中存入自定义的信息
//        String operationLog = "删除了成员"+member.getMember_name()+"的信息，成员用户名是"+member.getMember_username();
//        logSaveService.LogInfoSave(operationLog);
//
//        Logger log = LogUtils.getDBLogger();

        return memberList;
    }

    @RequestMapping(value = "/getMemberNameById")
    public String getMemberNameById(@RequestParam("member_id") Integer member_id){
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvv");
        System.out.println("!!!!!!!!" + member_id + "!!!!!!!!!");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
        Member member = memberRespository.getMemberById(member_id);
        if(member==null){
            return "";
        }else {
            return member.getMember_name();
        }
    }


    @RequestMapping(value = "/getMemberById")
    public Member getMemberById(@RequestParam("member_id") Integer member_id){

        Member member = memberRespository.getMemberById(member_id);

        return member;
    }


    @RequestMapping(value = "/updateMemberById" ,method = RequestMethod.POST)
    public String updateMemberInfo(@RequestBody Member member){

        memberRespository.save(member);

        //根据网页端操作，在log日志中存入自定义的信息
//        String operationLog = "更新了成员"+member.getMember_name()+"的信息，成员用户名是"+member.getMember_username();
//        logSaveService.LogInfoSave(operationLog);
//
//        Logger log = LogUtils.getDBLogger();

        return "updateOk";
    }


}

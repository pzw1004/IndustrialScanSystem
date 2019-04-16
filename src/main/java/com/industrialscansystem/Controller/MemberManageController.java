package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Service.LogSaveService;
import com.industrialscansystem.respository.MemberRespository;
import com.industrialscansystem.util.LogUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class MemberManageController {

    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @Autowired
    private MemberRespository memberRespository;

    @RequestMapping(value = "/memberList")
    public Object getMemberList(Model model){

        String operationLog = "查看了成员列表";
        logSaveService.LogInfoSave(operationLog);

        List<Member> memberList = memberRespository.findAll();

        model.addAttribute("memberList",memberList);
        Logger log = LogUtils.getBussinessLogger();

    return "memberlist";
    }

//    @PostMapping("/MembersSearch")
//
//    public Object getMembersUp9(@RequestParam("page") Integer page,
//                                          @RequestParam("key")String key) {
//        Integer num = 6;
//        Integer tiao = (page - 1) * num;
//        List<Member> memberList = memberRespository.getByMemberBefore(key, tiao, num);
//
//        return memberList;
//
//
//    }

    @RequestMapping(value = "/memberlistAdd" )
    public String addMemberPage(){

        String operationLog = "前往了添加新成员列表信息";
        logSaveService.LogInfoSave(operationLog);

        return "memberlist_add";
    }

    @RequestMapping(value = "/memberlistAddInfo" ,method = RequestMethod.POST)
    public String addMemberInfo(
                                @RequestParam("member_name") String member_name,
                                @RequestParam("member_phone") String member_phone,
                                @RequestParam("member_sex") String member_sex,
                                @RequestParam("member_address") String member_address,
                               // @RequestParam("member_jointime") String member_jointime,
                                @RequestParam("member_role") Integer member_role,
                                @RequestParam("member_number") String member_number,
                                @RequestParam("member_username") String member_username,
                                @RequestParam("member_password") String member_password
    ){

        long l = System.currentTimeMillis();
        Date time=new Date(l);
        Member member = new Member();
        member.setMember_name(member_name);
        member.setMember_phone(member_phone);
        member.setMember_sex(member_sex);
        member.setMember_jointime(time);
        member.setMember_role(member_role);
        member.setMember_number(member_number);
        member.setMember_username(member_username);
        member.setMember_password(member_password);
        member.setMember_address(member_address);

        memberRespository.save(member);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "添加了新成员"+member_name+"，新成员用户名是"+member_username;
        logSaveService.LogInfoSave(operationLog);
        Logger log = LogUtils.getDBLogger();

        return "redirect:/memberList";
    }

    @RequestMapping(value = "/memberlistUpdate")
    public String updateMember(@RequestParam ("member_id") Integer member_id,
                               Model model){

        Member member = new Member();
        member = memberRespository.findOne(member_id);
        model.addAttribute("member",member);
        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了成员"+member.getMember_name()+"的信息，成员用户名是"+member.getMember_username();
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "memberlist_update";
    }


    @RequestMapping(value = "/memberListDelte")
    public String deleteMember(@RequestParam("member_id") Integer member_id){

        Member member = new Member();
        member = memberRespository.findOne(member_id);
        memberRespository.deleteMemberById(member_id);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "删除了成员"+member.getMember_name()+"的信息，成员用户名是"+member.getMember_username();
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getDBLogger();

        return "redirect:/memberList";
    }

    @RequestMapping(value = "/memberlistUpdateInfo" ,method = RequestMethod.POST)
    public String updateMemberInfo(
            @RequestParam("member_id") Integer member_id,
            @RequestParam("member_name") String member_name,
            @RequestParam("member_phone") String member_phone,
            @RequestParam("member_sex") String member_sex,
            @RequestParam("member_address") String member_address,
            @RequestParam("member_jointime") Date member_jointime,
            @RequestParam("member_role") Integer member_role,
            @RequestParam("member_number") String member_number,
            @RequestParam("member_username") String member_username,
            @RequestParam("member_password") String member_password
    ){

        Member member = new Member();
        member.setMember_id(member_id);
        member.setMember_name(member_name);
        member.setMember_phone(member_phone);
        member.setMember_sex(member_sex);
        member.setMember_jointime(member_jointime);
        member.setMember_role(member_role);
        member.setMember_number(member_number);
        member.setMember_username(member_username);
        member.setMember_password(member_password);
        member.setMember_address(member_address);

        memberRespository.save(member);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "更新了成员"+member.getMember_name()+"的信息，成员用户名是"+member.getMember_username();
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getDBLogger();

        return "redirect:/memberList";
    }




//    @PostMapping("/MembersTotalPage")
//
//    public  Integer getMembersTotalPage(@RequestParam("key")String key) {
//
//        Integer num = 6;
//        Integer totalpages = memberRespository.getByMemberBeforezys(key);
//        return totalpages;
//
//    }




}

package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Service.LogSaveService;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class PersonalPageController {

    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @Autowired
    private MemberRespository memberRespository;

    @RequestMapping(value="/personalPage")
    public String  getPersonalPageInfo(Model model){

        List<Member> memberList = memberRespository.findAll();
        model.addAttribute("memberList",memberList);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了成员个人页面信息";
        logSaveService.LogInfoSave(operationLog);

        return "personalpage";
    }

    @RequestMapping(value = "/personalpageUpdate" ,method = RequestMethod.POST)
    public String personalPageUpdate(@RequestParam("member_name") String member_name,
                                     @RequestParam("member_phone") String member_phone,
                                     @RequestParam("member_sex") String member_sex,
                                     @RequestParam("member_address") String member_address,
                                     @RequestParam("member_id") Integer member_id,
                                     @RequestParam("member_role") Integer member_role,
                                     @RequestParam("member_number") String member_number,
                                     @RequestParam("member_username") String member_username,
                                     @RequestParam("member_password") String member_password,
                                     @RequestParam("member_jointime")Date member_jointime){

        Member member = new Member();
        member.setMember_name(member_name);
        member.setMember_phone(member_phone);
        member.setMember_sex(member_sex);
        member.setMember_id(member_id);
        member.setMember_role(member_role);
        member.setMember_number(member_number);
        member.setMember_username(member_username);
        member.setMember_password(member_password);
        member.setMember_address(member_address);
        member.setMember_jointime(member_jointime);

        memberRespository.save(member);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "更新了个人信息，成员"+member.getMember_name()+"，成员用户名是"+member.getMember_username();
        logSaveService.LogInfoSave(operationLog);

        return "redirect:/requisition";
    }

}

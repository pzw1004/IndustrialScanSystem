package com.industrialscansystem.Controller;

import com.industrialscansystem.Service.LogSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @RequestMapping(value = "/login")
    public  String LoginControl(Model model){


        return "login";
    }

//    @RequestMapping(value = "/user/logout")
//    public String LogoutControl(){
//
//        return "login";
//    }

    @RequestMapping(value = "/main")
    public  String MainControl(Model model){


        String operationLog = "登录访问了主页";
        logSaveService.LogInfoSave(operationLog);

        return "redirect:/requisition";
    }

//    @RequestMapping(value = "/top")
//    public  String TopControl(Model model){
//
//
//        return "top";
//    }
//
//    @RequestMapping(value ="/left")
//    public String LeftControl(Model model){
//
//        return "left";
//    }



}

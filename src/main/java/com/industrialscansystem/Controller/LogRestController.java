package com.industrialscansystem.Controller;

import com.industrialscansystem.Service.LogSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogRestController {

    //配置存储log日志服务getLogList
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @RequestMapping(value = "/saveLogInfo")
    public String saveLogInfo(@RequestParam("member_role") String member_role,
                              @RequestParam("member_name") String member_name,
                              @RequestParam("operation") String operation
                              ){

        logSaveService.LogInfoSave(member_role, member_name, operation);

        return "saveLogInfo sucess!";
    }


    @PostMapping(value = "/getConnectionState")
    public String getConnectionState(){

        return "getConnectionState!!!";
    }

}

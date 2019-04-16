package com.industrialscansystem.Controller;

import com.industrialscansystem.respository.LogRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoggingController {

    @Autowired
    private LogRespository logRespository;

    @RequestMapping(value = "/logtest")
    public String testlogtest(){

        return "loglist";
    }
}

package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.Log;
import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.Service.LogSaveService;
import com.industrialscansystem.respository.LogRespository;
import com.industrialscansystem.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@RestController
//@RequestMapping("test")
public class TestController {
    //protected static final Logger logger = LoggerFactory.getLogger(TestController.class);
    //记得加上这个，这是告诉全世界，你要开始在这类里面使用日志



    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @GetMapping(value = "/testlog")
    public String helloword(){ //需要用系统日志直接调用以下三种测试log即可

        String operationLog = "测试了当前用户存储日志信息";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getExceptionLogger();
        Logger log1 = LogUtils.getBussinessLogger();
        Logger log2 = LogUtils.getDBLogger();
        // 以下方法可以根据自定义信息存入log日志
        log.error("getExceptionLogger===日志测试十点半");//错误日志
        log1.info("getBussinessLogger===日志测试");//日常业务日志
        log2.debug("getDBLogger===日志测试");//数据库操作日志
       // String aaa = logSaveService.LogInfoSave();
        //String ss = log1.getName();//


        return "Hello world!";
    }


    @PostMapping(value = "/getConnectionState")
    public String getConnectionState(){

        return "getConnectionState!!!";
    }
}

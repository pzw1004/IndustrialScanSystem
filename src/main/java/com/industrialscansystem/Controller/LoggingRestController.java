package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.Log;
import com.industrialscansystem.respository.LogRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoggingRestController {

    @Autowired
    private LogRespository logRespository;
    @PostMapping (value = "/loglist")
    public Object getLogList(@RequestParam("page") int page,
                             @RequestParam("key") String key,
                             Model model){

       // int page = 2;
        Integer num = 30;
        Integer tiao = (page-1)*num;
      //  String key = "";
        List<Log> logList = logRespository.findLogListByKeyAndPageNumAndNum(key,tiao,num);


        return logList;
    }

    @PostMapping(value = "/loglistZys")
    public int getLogTotalPageNum(@RequestParam("key") String key){
        int num = 30;
        int TotalPageNum = logRespository.getLogTotalPageNumByKey(key);

        return TotalPageNum;
    }
}

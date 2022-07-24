package com.industrialscansystem.Controller;



import com.industrialscansystem.Bean.Requisition;
import com.industrialscansystem.respository.RequisitionRespository;
//import net.sf.json.JSONArray;
import com.industrialscansystem.Bean.Log;
import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.LogRespository;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoggingRestController {

    @Autowired
    private LogRespository logRespository;

    @Autowired
    private MemberRespository memberRespository;

    @Autowired
    private RequisitionRespository requisitionRespository;

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



    @GetMapping(value = "/getLogList")
    public List<Log> getLogList(){
        return  logRespository.getLogList();
    }




}

package com.industrialscansystem.respository.Service;

import com.industrialscansystem.Bean.Log;
import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.LogRespository;
import com.industrialscansystem.respository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class LogSaveService {

    @Autowired
    private LogRespository logRespository;

    @Autowired
    private MemberRespository memberRespository;


    public void LogInfoSave(String memberRole,String memberName,String operation){ //记录存储log用户信息服务


        //取得现在的操作时间
        Timestamp timestamp=new Timestamp(new Date().getTime());

        Log log = new Log();
        log.setLog_role(memberRole);
        log.setLog_operation(operation);
        log.setLog_operator(memberName);
        log.setLog_time(timestamp);

        logRespository.save(log);



    }
}

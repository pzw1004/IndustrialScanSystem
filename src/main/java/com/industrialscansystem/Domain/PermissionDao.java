package com.industrialscansystem.Domain;

import com.industrialscansystem.Bean.Member;
import com.industrialscansystem.respository.MemberRespository;
import com.industrialscansystem.respository.PermissionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component  //https://blog.csdn.net/u012373815/article/details/54633046?utm_source=blogxgwz0
public class PermissionDao {

    @Autowired
    private PermissionRespository permissionRespository;

    @Autowired
    public  Object getMemberAuth(){
        List<Permission> permissionList =permissionRespository.findAll();
    return  permissionList;
    }

//    @Autowired
//    public Object getMemberAuthById(int id){
//
//        List<Permission> permissionList = permissionRespository.findOne(id);
//        return permissionList;
//    }
}

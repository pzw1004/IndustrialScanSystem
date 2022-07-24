package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.Role;
import com.industrialscansystem.respository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleRestController {

    @Autowired
    private RoleRespository roleRespository;

    @RequestMapping(value = "/getRoleList")
    public List<Role> getRoleList(){
        return roleRespository.findAll();
    }
}

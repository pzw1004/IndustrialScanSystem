package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {  //初始化用户权限表

    @Id
    @GeneratedValue
    private Integer role_id;
    private String role_name;
    private  String role_nameHY;
    private Integer role_role_id;

    public Integer getRole_role_id() {
        return role_role_id;
    }

    public void setRole_role_id(Integer role_role_id) {
        this.role_role_id = role_role_id;
    }



    public String getRole_nameHY() {
        return role_nameHY;
    }

    public void setRole_nameHY(String role_nameHY) {
        this.role_nameHY = role_nameHY;
    }



    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }





}

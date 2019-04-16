package com.industrialscansystem.Bean;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Authority {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer role;
    private Integer role_requisition_id;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole_requisition_id() {
        return role_requisition_id;
    }

    public void setRole_requisition_id(Integer role_requisition_id) {
        this.role_requisition_id = role_requisition_id;
    }


}

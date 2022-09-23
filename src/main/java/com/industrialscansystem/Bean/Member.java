package com.industrialscansystem.Bean;

//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Member  {
    @Id
    @GeneratedValue
    private Integer member_id;
    private String member_name;
    private String member_phone;
    private String member_address;
    private String member_sex;
    private String member_number;
    private Date member_jointime;
    private  Integer member_role;
    private  String member_username;
    private String member_password;
    private String signature;
    public Date getMember_jointime() {
        return member_jointime;
    }

    public void setMember_jointime(Date member_jointime) {
        this.member_jointime = member_jointime;
    }

    public String getMember_username() {
        return member_username;
    }

    public void setMember_username(String member_username) {
        this.member_username = member_username;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }

    public Integer getMember_role() {
        return member_role;
    }

    public void setMember_role(Integer member_role) {
        this.member_role = member_role;
    }


    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_address() {
        return member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}

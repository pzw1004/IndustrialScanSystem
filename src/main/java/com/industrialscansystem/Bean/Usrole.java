package com.industrialscansystem.Bean;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usrole {

    @Id
    @GeneratedValue
    private Integer usrole_id;
    private Integer usrole_user_id;
    private Integer usrole_role_id;

    public Integer getUsrole_id() {
        return usrole_id;
    }

    public void setUsrole_id(Integer usrole_id) {
        this.usrole_id = usrole_id;
    }

    public Integer getUsrole_user_id() {
        return usrole_user_id;
    }

    public void setUsrole_user_id(Integer usrole_user_id) {
        this.usrole_user_id = usrole_user_id;
    }

    public Integer getUsrole_role_id() {
        return usrole_role_id;
    }

    public void setUsrole_role_id(Integer usrole_role_id) {
        this.usrole_role_id = usrole_role_id;
    }


}

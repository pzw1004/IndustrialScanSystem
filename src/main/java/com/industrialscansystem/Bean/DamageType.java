package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DamageType {
    //初始化损伤信息

    @Id
    @GeneratedValue

    private Integer damagetype_id;
    private String damagetype_name;
    private Integer flag;

    public String getDamagetype_name() {
        return damagetype_name;
    }

    public void setDamagetype_name(String damagetype_name) {
        this.damagetype_name = damagetype_name;
    }



    public Integer getDamagetype_id() {
        return damagetype_id;
    }

    public void setDamagetype_id(Integer damagetype_id) {
        this.damagetype_id = damagetype_id;
    }


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}

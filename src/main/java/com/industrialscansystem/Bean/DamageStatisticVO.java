package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DamageStatisticVO {
    //初始化损伤信息

    private Integer damagetype_id;
    private String damagetype_name;
    private Integer count;
    private Double frequency;  // 无用，重新赋值后为百分比
    private Long id;  // 无用

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


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}

package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 用于picture与polygon表的联表查询的实体接收
 */
public class PictureAndPolygon {
    private Picture picture;
    private Polygon polygon;
    private DamageType damageType;

    public PictureAndPolygon(Picture picture, Polygon polygon, DamageType damageType) {
        this.picture = picture;
        this.polygon = polygon;
        this.damageType = damageType;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}

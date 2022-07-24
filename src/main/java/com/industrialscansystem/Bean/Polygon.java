package com.industrialscansystem.Bean;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Polygon
{
    @Id
    @GeneratedValue
    private Integer polygon_id;
    private String polygon_pt;
    private String polygon_author;
    private Integer polygon_picture_id;
    private Integer polygon_damage_type;
    private Integer polygon_text_x;
    private Integer polygon_text_y;
    private Float polygon_belief;


    public Integer getPolygon_text_x() {
        return polygon_text_x;
    }

    public Integer getPolygon_text_y() {
        return polygon_text_y;
    }

    public void setPolygon_text_y(Integer polygon_text_y) {
        this.polygon_text_y = polygon_text_y;
    }

    public void setPolygon_text_x(Integer polygon_text_x) {
        this.polygon_text_x = polygon_text_x;
    }

    public Polygon(){

    }

    public Polygon(JSONObject jsonObject, int picture_id){
        //{"author":"Algorithm","x":121,"y":774,"timestamp":1551081508015}
//        int rectSize = 64;
        this.polygon_author = jsonObject.getString("author");
        this.polygon_pt = jsonObject.getString("points");
        this.polygon_picture_id = picture_id;
        this.polygon_damage_type = 1;
        this.polygon_text_x = jsonObject.getInteger("text_x");
        this.polygon_text_y = jsonObject.getInteger("text_y");
        this.polygon_belief = jsonObject.getFloat("belief");
    }

    public Integer getPolygon_id() {
        return polygon_id;
    }

    public void setPolygon_id(Integer polygon_id) {
        this.polygon_id = polygon_id;
    }

    public String getPolygon_pt() {
        return polygon_pt;
    }

    public void setPolygon_pt(String polygon_pt) {
        this.polygon_pt = polygon_pt;
    }

    public String getPolygon_author() {
        return polygon_author;
    }

    public void setPolygon_author(String polygon_author) {
        this.polygon_author = polygon_author;
    }

    public Integer getPolygon_picture_id() {
        return polygon_picture_id;
    }

    public void setPolygon_picture_id(Integer polygon_picture_id) {
        this.polygon_picture_id = polygon_picture_id;
    }

    public Integer getPolygon_damage_type() {
        return polygon_damage_type;
    }

    public void setPolygon_damage_type(Integer polygon_damage_type) {
        this.polygon_damage_type = polygon_damage_type;
    }

    public Float getPolygon_belief() {
        return polygon_belief;
    }

    public void setPolygon_belief(Float polygon_belief) {
        this.polygon_belief = polygon_belief;
    }
}

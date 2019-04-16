package com.industrialscansystem.Bean;


import com.alibaba.fastjson.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Retangle {
 //初始化矩形框标记信息

    @Id
    @GeneratedValue
    private Integer retangle_id;
    private Integer retangle_x1;
    private Integer retangle_y1;
    private Integer retangle_x2;
    private Integer retangle_y2;
    private String retangle_author;
    private String retangle_date;
    private Integer retangle_picture_id;
    private Integer retangle_damage_type;
    private String retangle_rect_id;


    /**
     * @author:宋宗垚
     * @return
     */
    public Retangle(){

    }

    /**
     * @author:宋宗垚
     * @return
     */
    public Retangle(JSONObject jsonObject, int picture_id){
        //{"author":"Algorithm","x":121,"y":774,"timestamp":1551081508015}
//        int rectSize = 64;
        this.retangle_author = jsonObject.getString("author");
        this.retangle_x1 = jsonObject.getInteger("x_min");
        this.retangle_y1 = jsonObject.getInteger("y_min");

        this.retangle_x2 = jsonObject.getInteger("x_max") ;//+ rectSize;
        this.retangle_y2= jsonObject.getInteger("y_max");// + rectSize;

        this.retangle_date = jsonObject.getString("timestamp");
        this.retangle_picture_id = picture_id;

        this.retangle_damage_type = 0;
        this.retangle_rect_id = "";
    }

    public String getRetangle_rect_id() {
        return retangle_rect_id;
    }

    public void setRetangle_rect_id(String retangle_rect_id) {
        this.retangle_rect_id = retangle_rect_id;
    }
    public Integer getRetangle_damage_type() {
        return retangle_damage_type;
    }

    public void setRetangle_damage_type(Integer retangle_damage_type) {
        this.retangle_damage_type = retangle_damage_type;
    }


    public String getRetangle_author() {
        return retangle_author;
    }

    public void setRetangle_author(String retangle_author) {
        this.retangle_author = retangle_author;
    }




    public Integer getRetangle_picture_id() {
        return retangle_picture_id;
    }

    public void setRetangle_picture_id(Integer retangle_picture_id) {
        this.retangle_picture_id = retangle_picture_id;
    }



    public Integer getRetangle_id() {
        return retangle_id;
    }

    public void setRetangle_id(Integer retangle_id) {
        this.retangle_id = retangle_id;
    }

    public Integer getRetangle_x1() {
        return retangle_x1;
    }

    public void setRetangle_x1(Integer retangle_x1) {
        this.retangle_x1 = retangle_x1;
    }

    public Integer getRetangle_y1() {
        return retangle_y1;
    }

    public void setRetangle_y1(Integer retangle_y1) {
        this.retangle_y1 = retangle_y1;
    }

    public Integer getRetangle_x2() {
        return retangle_x2;
    }

    public void setRetangle_x2(Integer retangle_x2) {
        this.retangle_x2 = retangle_x2;
    }

    public Integer getRetangle_y2() {
        return retangle_y2;
    }

    public void setRetangle_y2(Integer retangle_y2) {
        this.retangle_y2 = retangle_y2;
    }



    public String getRetangle_date() {
        return retangle_date;
    }

    public void setRetangle_date(String retangle_date) {
        this.retangle_date = retangle_date;
    }




}

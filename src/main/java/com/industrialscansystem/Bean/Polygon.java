package com.industrialscansystem.Bean;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Polygon implements Comparable<Polygon>
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
    private String damagetype_name;

    public String getDamagetype_name() {
        return damagetype_name;
    }

    public void setDamagetype_name(String damagetype_name) {
        this.damagetype_name = damagetype_name;
    }

    private Float polygon_flaw_length;
    private Integer polygon_flaw_position_x;

    private Integer polygon_flaw_position_y;

    public Integer getPolygon_flaw_position_x() {
        return polygon_flaw_position_x;
    }

    public void setPolygon_flaw_position_x(int polygon_flaw_position_x) {
        this.polygon_flaw_position_x = polygon_flaw_position_x;
    }

    public Integer getPolygon_flaw_position_y() {
        return polygon_flaw_position_y;
    }

    public void setPolygon_flaw_position_y(int polygon_flaw_position_y) {
        this.polygon_flaw_position_y = polygon_flaw_position_y;
    }

    public Float getPolygon_flaw_length() {
        return polygon_flaw_length;
    }

    public void setPolygon_flaw_length(Float polygon_flaw_length) {
        this.polygon_flaw_length = polygon_flaw_length;
    }

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
//        this.damagetype_name = jsonObject.getString("damagetype_name");
        this.polygon_text_x = jsonObject.getInteger("text_x");
        this.polygon_text_y = jsonObject.getInteger("text_y");
//        this.polygon_flaw_length = jsonObject.getFloat("polygon_flawLength");
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

    @Override
    public int compareTo(Polygon o) {
        int y = 150;
        if(this.getPolygon_damage_type()!=6 && o.getPolygon_damage_type()!=6){
            if(this.getPolygon_text_x()>o.getPolygon_text_x()){
                if(this.getPolygon_text_y()<=150 && o.getPolygon_text_y()>=150){//不用换位
                    return -1;
                }
                else{
                    return 1; //换位
                }
            }
            else if(this.getPolygon_text_x()<o.getPolygon_text_x()){
                if(this.getPolygon_text_y()>=150 &&  o.getPolygon_text_y()<=150){//换位
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else{
                return 0;
            }
        }
      else if(this.getPolygon_damage_type()==6&&o.getPolygon_damage_type()!=6){
          return 1;
        }
      else{
          return -1;
        }
    }
}

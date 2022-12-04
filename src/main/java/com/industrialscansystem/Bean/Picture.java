package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Picture {

    @Id
    @GeneratedValue
    private Integer picture_id;
    //id
    private String picture_conclusion;
    //结论
    private  String picture_welding_operator;
    //焊接人
    private String picture_density;
    //影像底片黑度
    private String picture_quality;
    //像质指数
    private String picture_flaw_position;
    //缺陷位置 手动输入
    private String picture_flaw_type;
    //缺陷类型 手动输入
    private String picture_flaw_description;
    //缺陷描述 手动输入
    private String picture_number;
    //影像图编号
    private String picture_parts_Introductions;
    private String picture_dir;
    //影像图存储位置
    private String picture_ps;
    //影像图备注
    private String picture_transpath;

    private int picture_AIresult;



    private Date picture_entrytime;
    private Integer picture_requisition_id;
    private Integer picture_thickness;
    private String picture_teststandard;
    private String picture_qualifylevel;
    private String picture_firstexamresult;
    private String picture_secondexamresult;
    private String picture_thirdexamresult;
    private Integer picture_firstexammember;
    private Integer picture_secondexammember;
    private Integer picture_thirdexammember;
    private Integer picture_width;
    private Integer picture_height;
    private String picture_testmethod;
    private String picture_level;
    private String picture_jointform;
    private String picture_cross_point;
    private Integer picture_real_width;
    private Integer picture_real_height;
    private String picture_hanfeng_name;
    private String picture_hanfeng_number;
    private String picture_hanfeng_method;
    private String picture_bevel_form;
    private String picture_material_number;
    private String picture_hanfeng_length;
    private Float picture_testing_rate;
    private String picture_hanfeng_testlength;
    private String picture_woliu_dir;
    private String picture_chaosheng_dir;

    public String getPicture_woliu_dir() {
        return picture_woliu_dir;
    }

    public void setPicture_woliu_dir(String picture_woliu_dir) {
        this.picture_woliu_dir = picture_woliu_dir;
    }

    public String getPicture_chaosheng_dir() {
        return picture_chaosheng_dir;
    }

    public void setPicture_chaosheng_dir(String picture_chaosheng_dir) {
        this.picture_chaosheng_dir = picture_chaosheng_dir;
    }

    public void setPicture_real_width(Integer picture_real_width) {
        this.picture_real_width = picture_real_width;
    }

    public void setPicture_real_height(Integer picture_real_height) {
        this.picture_real_height = picture_real_height;
    }

    public String getPicture_hanfeng_name() {
        return picture_hanfeng_name;
    }

    public void setPicture_hanfeng_name(String picture_hanfeng_name) {
        this.picture_hanfeng_name = picture_hanfeng_name;
    }

    public String getPicture_hanfeng_number() {
        return picture_hanfeng_number;
    }

    public void setPicture_hanfeng_number(String picture_hanfeng_number) {
        this.picture_hanfeng_number = picture_hanfeng_number;
    }

    public String getPicture_hanfeng_method() {
        return picture_hanfeng_method;
    }

    public void setPicture_hanfeng_method(String picture_hanfeng_method) {
        this.picture_hanfeng_method = picture_hanfeng_method;
    }

    public String getPicture_bevel_form() {
        return picture_bevel_form;
    }

    public void setPicture_bevel_form(String picture_bevel_form) {
        this.picture_bevel_form = picture_bevel_form;
    }

    public String getPicture_material_number() {
        return picture_material_number;
    }

    public void setPicture_material_number(String picture_material_number) {
        this.picture_material_number = picture_material_number;
    }

    public String getPicture_hanfeng_length() {
        return picture_hanfeng_length;
    }

    public void setPicture_hanfeng_length(String picture_hanfeng_length) {
        this.picture_hanfeng_length = picture_hanfeng_length;
    }

    public Float getPicture_testing_rate() {
        return picture_testing_rate;
    }

    public void setPicture_testing_rate(Float picture_testing_rate) {
        this.picture_testing_rate = picture_testing_rate;
    }

    public String getPicture_hanfeng_testlength() {
        return picture_hanfeng_testlength;
    }

    public void setPicture_hanfeng_testlength(String picture_hanfeng_testlength) {
        this.picture_hanfeng_testlength = picture_hanfeng_testlength;
    }

    public Integer getPicture_real_width() {
        return picture_real_width;
    }

    public void setPicture_real_width(int picture_real_width) {
        this.picture_real_width = picture_real_width;
    }

    public Integer getPicture_real_height() {
        return picture_real_height;
    }

    public void setPicture_real_height(int picture_real_height) {
        this.picture_real_height = picture_real_height;
    }

    public String getPicture_cross_point() {
        return picture_cross_point;
    }

    public void setPicture_cross_point(String picture_cross_point) {
        this.picture_cross_point = picture_cross_point;
    }

    public String getPicture_density() {
        return picture_density;
    }

    public void setPicture_density(String picture_density) {
        this.picture_density = picture_density;
    }

    public String getPicture_quality() {
        return picture_quality;
    }

    public void setPicture_quality(String picture_quality) {
        this.picture_quality = picture_quality;
    }

    public Date getPicture_entrytime() {
        return picture_entrytime;
    }

    public void setPicture_entrytime(Date picture_entrytime) {
        this.picture_entrytime = picture_entrytime;
    }


    public String getPicture_testmethod() {
        return picture_testmethod;
    }

    public void setPicture_testmethod(String picture_testmethod) {
        this.picture_testmethod = picture_testmethod;
    }

    public String getPicture_level() {
        return picture_level;
    }

    public void setPicture_level(String picture_level) {
        this.picture_level = picture_level;
    }

    public String getPicture_jointform() {
        return picture_jointform;
    }

    public void setPicture_jointform(String picture_jointform) {
        this.picture_jointform = picture_jointform;
    }

    public String getPicture_flaw_position() {
        return picture_flaw_position;
    }

    public void setPicture_flaw_position(String picture_flaw_position) {
        this.picture_flaw_position = picture_flaw_position;
    }

    public String getPicture_flaw_type() {
        return picture_flaw_type;
    }

    public void setPicture_flaw_type(String picture_flaw_type) {
        this.picture_flaw_type = picture_flaw_type;
    }

    public String getPicture_flaw_description() {
        return picture_flaw_description;
    }

    public void setPicture_flaw_description(String picture_flaw_description) {
        this.picture_flaw_description = picture_flaw_description;
    }

    public Integer getPicture_firstexammember() {
        return picture_firstexammember;
    }

    public void setPicture_firstexammember(Integer picture_firstexammember) {
        this.picture_firstexammember = picture_firstexammember;
    }

    public Integer getPicture_secondexammember() {
        return picture_secondexammember;
    }

    public void setPicture_secondexammember(Integer picture_secondexammember) {
        this.picture_secondexammember = picture_secondexammember;
    }

    public Integer getPicture_thirdexammember() {
        return picture_thirdexammember;
    }

    public void setPicture_thirdexammember(Integer picture_thirdexammember) {
        this.picture_thirdexammember = picture_thirdexammember;
    }


    public String getPicture_parts_Introductions() {
        return picture_parts_Introductions;
    }

    public void setPicture_parts_Introductions(String picture_parts_Introductions) {
        this.picture_parts_Introductions = picture_parts_Introductions;
    }

    public Integer getPicture_width() {
        return picture_width;
    }

    public void setPicture_width(Integer picture_width) {
        this.picture_width = picture_width;
    }

    public Integer getPicture_height() {
        return picture_height;
    }

    public void setPicture_height(Integer picture_height) {
        this.picture_height = picture_height;
    }


    public String getPicture_conclusion() {
        return picture_conclusion;
    }

    public void setPicture_conclusion(String picture_conclusion) {
        this.picture_conclusion = picture_conclusion;
    }

    public String getPicture_welding_operator() {
        return picture_welding_operator;
    }

    public void setPicture_welding_operator(String picture_welding_operator) {
        this.picture_welding_operator = picture_welding_operator;
    }

    public String getPicture_transpath() {
        return picture_transpath;
    }

    public void setPicture_transpath(String picture_transpath) {
        this.picture_transpath = picture_transpath;
    }



    public int getPicture_AIresult() {
        return picture_AIresult;
    }

    public void setPicture_AIresult(int picture_AIresult) {
        this.picture_AIresult = picture_AIresult;
    }



    public String getPicture_firstexamresult() {
        return picture_firstexamresult;
    }

    public void setPicture_firstexamresult(String picture_firstexamresult) {
        this.picture_firstexamresult = picture_firstexamresult;
    }

    public String getPicture_secondexamresult() {
        return picture_secondexamresult;
    }

    public void setPicture_secondexamresult(String picture_secondexamresult) {
        this.picture_secondexamresult = picture_secondexamresult;
    }

    public String getPicture_thirdexamresult() {
        return picture_thirdexamresult;
    }

    public void setPicture_thirdexamresult(String picture_thirdexamresult) {
        this.picture_thirdexamresult = picture_thirdexamresult;
    }




    public Integer getPicture_thickness() {
        return picture_thickness;
    }

    public void setPicture_thickness(Integer picture_thickness) {
        this.picture_thickness = picture_thickness;
    }



    public String getPicture_teststandard() {
        return picture_teststandard;
    }

    public void setPicture_teststandard(String picture_teststandard) {
        this.picture_teststandard = picture_teststandard;
    }

    public String getPicture_qualifylevel() {
        return picture_qualifylevel;
    }

    public void setPicture_qualifylevel(String picture_qualifylevel) {
        this.picture_qualifylevel = picture_qualifylevel;
    }



    public Integer getPicture_requisition_id() {
        return picture_requisition_id;
    }

    public void setPicture_requisition_id(Integer picture_requisition_id) {
        this.picture_requisition_id = picture_requisition_id;
    }









    public Integer getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(Integer picture_id) {
        this.picture_id = picture_id;
    }

    public String getPicture_number() {
        return picture_number;
    }

    public void setPicture_number(String picture_number) {
        this.picture_number = picture_number;
    }



    public String getPicture_dir() {
        return picture_dir;
    }

    public void setPicture_dir(String picture_dir) {
        this.picture_dir = picture_dir;
    }

    public String getPicture_ps() {
        return picture_ps;
    }

    public void setPicture_ps(String picture_ps) {
        this.picture_ps = picture_ps;
    }


}

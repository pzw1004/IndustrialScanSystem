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
    private String picture_number;
    //影像图编号
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

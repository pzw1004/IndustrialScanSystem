package com.industrialscansystem.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Requisition {
    //初始化申请单信息表
    @Id
    @GeneratedValue
    private Integer requisition_id;
    //申请单id
    private String requisition_number;
    //申请单工程编号
    private String requisition_name;
    //申请单工程名称
    private String requisition_structurename;
    //申请单结构名称
    private String requisition_constructunit;
    //申请单施工单位

    private String requisition_weldingmethod;

    private String requisition_steelnumber;
    //钢号
    private String requisition_firstexam;

    //是否一级审查
    private Integer requisition_firstexam_member;
    //一级审查人员名称
    private String requisition_secondexam;
    //是否二级审查
    private Integer requisition_secondexam_member;
    //二级审查人员名称
    private String requisition_thirdexam;
    //是否三级审查
    private Integer requisition_thirdexam_member;

    public Integer getRequisition_state() {
        return requisition_state;
    }

    public void setRequisition_state(Integer requisition_state) {
        this.requisition_state = requisition_state;
    }

    //三级审查人员名称
    private Integer requisition_state;
    //申请单状态，被一级、二级、三级驳回状态或空

    private String requisition_saveplace;

    private String requisition_samplenumber;

    private String requisition_density;

    private String requisition_reportnumber;

    private String requisition_filmtype;

    private String requisition_testingstandard;

    private String requisition_qualificationlevel;

    private String requisition_intensifyscreen;

    private Integer requisition_tubecurrent;

    private Integer requisition_tubevoltage;

    private Integer requisition_exposuretime;

    private String requisition_sensitivity;

    private Integer requisition_focus;

    private Integer requisition_developmenttime;

    private Integer requisition_developertemperature;


    private Integer requisition_reworktimes;

    private String requisition_jointform;

    private String requisition_ps;

    private String requisition_instrumenttype;

    private Integer requisition_totalnumber;

    private Integer requisition_submit;

    private Date requisition_entrytime;


    private String requisition_firstopinion;

    private String requisition_secondopinion;

    private String requisition_thirdopinion;


    public Integer getRequisition_id() {
        return requisition_id;
    }

    public void setRequisition_id(Integer requisition_id) {
        this.requisition_id = requisition_id;
    }

    public String getRequisition_number() {
        return requisition_number;
    }

    public void setRequisition_number(String requisition_number) {
        this.requisition_number = requisition_number;
    }

    public String getRequisition_name() {
        return requisition_name;
    }

    public void setRequisition_name(String requisition_name) {
        this.requisition_name = requisition_name;
    }

    public String getRequisition_structurename() {
        return requisition_structurename;
    }

    public void setRequisition_structurename(String requisition_structurename) {
        this.requisition_structurename = requisition_structurename;
    }

    public String getRequisition_constructunit() {
        return requisition_constructunit;
    }

    public void setRequisition_constructunit(String requisition_constructunit) {
        this.requisition_constructunit = requisition_constructunit;
    }

    public String getRequisition_weldingmethod() {
        return requisition_weldingmethod;
    }

    public void setRequisition_weldingmethod(String requisition_weldingmethod) {
        this.requisition_weldingmethod = requisition_weldingmethod;
    }

    public String getRequisition_steelnumber() {
        return requisition_steelnumber;
    }

    public void setRequisition_steelnumber(String requisition_steelnumber) {
        this.requisition_steelnumber = requisition_steelnumber;
    }

    public String getRequisition_firstexam() {
        return requisition_firstexam;
    }

    public void setRequisition_firstexam(String requisition_firstexam) {
        this.requisition_firstexam = requisition_firstexam;
    }

    public Integer getRequisition_firstexam_member() {
        return requisition_firstexam_member;
    }

    public void setRequisition_firstexam_member(Integer requisition_firstexam_member) {
        this.requisition_firstexam_member = requisition_firstexam_member;
    }

    public String getRequisition_secondexam() {
        return requisition_secondexam;
    }

    public void setRequisition_secondexam(String requisition_secondexam) {
        this.requisition_secondexam = requisition_secondexam;
    }

    public Integer getRequisition_secondexam_member() {
        return requisition_secondexam_member;
    }

    public void setRequisition_secondexam_member(Integer requisition_secondexam_member) {
        this.requisition_secondexam_member = requisition_secondexam_member;
    }

    public String getRequisition_thirdexam() {
        return requisition_thirdexam;
    }

    public void setRequisition_thirdexam(String requisition_thirdexam) {
        this.requisition_thirdexam = requisition_thirdexam;
    }

    public Integer getRequisition_thirdexam_member() {
        return requisition_thirdexam_member;
    }

    public void setRequisition_thirdexam_member(Integer requisition_thirdexam_member) {
        this.requisition_thirdexam_member = requisition_thirdexam_member;
    }



    public String getRequisition_saveplace() {
        return requisition_saveplace;
    }

    public void setRequisition_saveplace(String requisition_saveplace) {
        this.requisition_saveplace = requisition_saveplace;
    }

    public String getRequisition_samplenumber() {
        return requisition_samplenumber;
    }

    public void setRequisition_samplenumber(String requisition_samplenumber) {
        this.requisition_samplenumber = requisition_samplenumber;
    }

    public String getRequisition_density() {
        return requisition_density;
    }

    public void setRequisition_density(String requisition_density) {
        this.requisition_density = requisition_density;
    }

    public String getRequisition_reportnumber() {
        return requisition_reportnumber;
    }

    public void setRequisition_reportnumber(String requisition_reportnumber) {
        this.requisition_reportnumber = requisition_reportnumber;
    }

    public String getRequisition_filmtype() {
        return requisition_filmtype;
    }

    public void setRequisition_filmtype(String requisition_filmtype) {
        this.requisition_filmtype = requisition_filmtype;
    }

    public String getRequisition_testingstandard() {
        return requisition_testingstandard;
    }

    public void setRequisition_testingstandard(String requisition_testingstandard) {
        this.requisition_testingstandard = requisition_testingstandard;
    }

    public String getRequisition_qualificationlevel() {
        return requisition_qualificationlevel;
    }

    public void setRequisition_qualificationlevel(String requisition_qualificationlevel) {
        this.requisition_qualificationlevel = requisition_qualificationlevel;
    }

    public String getRequisition_intensifyscreen() {
        return requisition_intensifyscreen;
    }

    public void setRequisition_intensifyscreen(String requisition_intensifyscreen) {
        this.requisition_intensifyscreen = requisition_intensifyscreen;
    }

    public Integer getRequisition_tubecurrent() {
        return requisition_tubecurrent;
    }

    public void setRequisition_tubecurrent(Integer requisition_tubecurrent) {
        this.requisition_tubecurrent = requisition_tubecurrent;
    }

    public Integer getRequisition_tubevoltage() {
        return requisition_tubevoltage;
    }

    public void setRequisition_tubevoltage(Integer requisition_tubevoltage) {
        this.requisition_tubevoltage = requisition_tubevoltage;
    }

    public Integer getRequisition_exposuretime() {
        return requisition_exposuretime;
    }

    public void setRequisition_exposuretime(Integer requisition_exposuretime) {
        this.requisition_exposuretime = requisition_exposuretime;
    }

    public String getRequisition_sensitivity() {
        return requisition_sensitivity;
    }

    public void setRequisition_sensitivity(String requisition_sensitivity) {
        this.requisition_sensitivity = requisition_sensitivity;
    }

    public Integer getRequisition_focus() {
        return requisition_focus;
    }

    public void setRequisition_focus(Integer requisition_focus) {
        this.requisition_focus = requisition_focus;
    }

    public Integer getRequisition_developmenttime() {
        return requisition_developmenttime;
    }

    public void setRequisition_developmenttime(Integer requisition_developmenttime) {
        this.requisition_developmenttime = requisition_developmenttime;
    }

    public Integer getRequisition_developertemperature() {
        return requisition_developertemperature;
    }

    public void setRequisition_developertemperature(Integer requisition_developertemperature) {
        this.requisition_developertemperature = requisition_developertemperature;
    }

    public Integer getRequisition_reworktimes() {
        return requisition_reworktimes;
    }

    public void setRequisition_reworktimes(Integer requisition_reworktimes) {
        this.requisition_reworktimes = requisition_reworktimes;
    }

    public String getRequisition_jointform() {
        return requisition_jointform;
    }

    public void setRequisition_jointform(String requisition_jointform) {
        this.requisition_jointform = requisition_jointform;
    }

    public String getRequisition_ps() {
        return requisition_ps;
    }

    public void setRequisition_ps(String requisition_ps) {
        this.requisition_ps = requisition_ps;
    }

    public String getRequisition_instrumenttype() {
        return requisition_instrumenttype;
    }

    public void setRequisition_instrumenttype(String requisition_instrumenttype) {
        this.requisition_instrumenttype = requisition_instrumenttype;
    }

    public Integer getRequisition_totalnumber() {
        return requisition_totalnumber;
    }

    public void setRequisition_totalnumber(Integer requisition_totalnumber) {
        this.requisition_totalnumber = requisition_totalnumber;
    }

    public Integer getRequisition_submit() {
        return requisition_submit;
    }

    public void setRequisition_submit(Integer requisition_submit) {
        this.requisition_submit = requisition_submit;
    }

    public Date getRequisition_entrytime() {
        return requisition_entrytime;
    }

    public void setRequisition_entrytime(Date requisition_entrytime) {
        this.requisition_entrytime = requisition_entrytime;
    }

    public String getRequisition_firstopinion() {
        return requisition_firstopinion;
    }

    public void setRequisition_firstopinion(String requisition_firstopinion) {
        this.requisition_firstopinion = requisition_firstopinion;
    }

    public String getRequisition_secondopinion() {
        return requisition_secondopinion;
    }

    public void setRequisition_secondopinion(String requisition_secondopinion) {
        this.requisition_secondopinion = requisition_secondopinion;
    }

    public String getRequisition_thirdopinion() {
        return requisition_thirdopinion;
    }

    public void setRequisition_thirdopinion(String requisition_thirdopinion) {
        this.requisition_thirdopinion = requisition_thirdopinion;
    }
}

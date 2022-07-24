package com.industrialscansystem.Mapper;

import com.industrialscansystem.MybatisBean.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Select("SELECT picture_id,picture_number FROM `picture` WHERE picture_requisition_id = #{requisition_id}")
    List<Picture> findPicturesByRequisitionId(int requisition_id);


    //通过mybatis查询不重复自动list
    @Select("select distinct picture_testmethod from picture WHERE picture_testmethod IS NOT NULL ")
     List<com.industrialscansystem.Bean.Picture> getPictureListByDistinctTestmethod();

    @Select("select distinct picture_thickness from picture WHERE picture_thickness IS NOT NULL ")
    List<com.industrialscansystem.Bean.Picture> getPictureListByDistinctThickness();

    @Select("select distinct picture_teststandard from picture WHERE picture_teststandard IS NOT NULL ")
    List<com.industrialscansystem.Bean.Picture> getPictureListByDistinctTeststandard();

    @Select("select distinct picture_qualifylevel from picture WHERE picture_qualifylevel IS NOT NULL ")
    List<com.industrialscansystem.Bean.Picture> getPictureListByDistinctQualifylevel();

    @Select("select distinct picture_jointform from picture WHERE picture_jointform IS NOT NULL ")
    List<com.industrialscansystem.Bean.Picture> getPictureListByDistinctJointform();




}

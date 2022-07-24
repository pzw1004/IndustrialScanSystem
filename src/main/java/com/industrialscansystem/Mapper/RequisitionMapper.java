package com.industrialscansystem.Mapper;

import com.industrialscansystem.MybatisBean.Requisition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequisitionMapper {
    @Select("SELECT * FROM `requisition` WHERE requisition_id = #{requisition_id}")
    @Results({
            @Result(property = "pictures",column = "requisition_id",
                    many = @Many(select = "com.industrialscansystem.Mapper.PictureMapper.findPicturesByRequisitionId"))
    })
    Requisition getPictures(int requisition_id);


    @Select("SELECT * FROM `requisition` ")
    @Results({
            @Result(property = "pictures",column = "requisition_id",
                    many = @Many(select = "com.industrialscansystem.Mapper.PictureMapper.findPicturesByRequisitionId"))
    })
    List<Requisition> getRequisitionListPictures();

    @Select("SELECT * FROM `requisition`")
    List<Requisition> getRequisitionFile();

    //通过mybatis查询不重复自动list requisition_samplenumberList
    @Select("select distinct requisition_samplenumber from requisition WHERE requisition_samplenumber IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctSamplenumber();

    @Select("select distinct requisition_structurename from requisition WHERE requisition_structurename IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctStructurename();

    @Select("select distinct requisition_number from requisition WHERE requisition_number IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctNumber();

    @Select("select distinct requisition_constructunit from requisition WHERE requisition_constructunit IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctConstructunit();

    @Select("select distinct requisition_weldingmethod from requisition WHERE requisition_weldingmethod IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctWeldingmethod();

    @Select("select distinct requisition_steelnumber from requisition WHERE requisition_steelnumber IS NOT NULL ")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionListByDistinctSteelnumber();


}

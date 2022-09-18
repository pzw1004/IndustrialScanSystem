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
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFile();

    @Select("SELECT * FROM `requisition` where requisition_testing_rate is not null group by requisition_testing_rate")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTestingRate();

    @Select("SELECT * FROM `requisition` where requisition_bevel_form is not null  group by requisition_bevel_form")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByBevelForm();

    @Select("SELECT * FROM `requisition` where requisition_filmtype is not null  group by requisition_filmtype")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFilmtype();

    @Select("SELECT * FROM `requisition` where requisition_transillumination is not null  group by requisition_transillumination")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTransillumination();

    @Select("SELECT * FROM `requisition` where requisition_focus_size is not null  group by requisition_focus_size")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFocusSize();

    @Select("SELECT * FROM `requisition` where requisition_sensitization_method is not null  group by requisition_sensitization_method")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctBySensitizationMethod();

    @Select("SELECT * FROM `requisition` where requisition_film_processing_method is not null  group by requisition_film_processing_method")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFilmProcessingMethod();

    @Select("SELECT * FROM `requisition` where requisition_qualificationlevel is not null group by requisition_qualificationlevel")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByQualificationLevel();

    @Select("SELECT * FROM `requisition` where requisition_testing_instrument is not null group by requisition_testing_instrument")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTestingInstrument();

    @Select("SELECT * FROM `requisition` where requisition_weldingmethod is not null group by requisition_weldingmethod")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByWeldingMethod();

    @Select("SELECT * FROM `requisition` where requisition_intensifyscreen_front is not null group by requisition_intensifyscreen_front")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenFront();

    @Select("SELECT * FROM `requisition` where requisition_intensifyscreen_middle is not null group by requisition_intensifyscreen_middle")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenMiddle();

    @Select("SELECT * FROM `requisition` where requisition_intensifyscreen_behind is not null group by requisition_intensifyscreen_behind")
    List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenBehind();

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

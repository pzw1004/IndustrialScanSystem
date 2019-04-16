package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RequisitionRespository extends JpaRepository<Requisition,Integer> {

    @Modifying
    @Query(value = "update requisition set requisition_firstexam=?1 where requisition_id=?2",nativeQuery = true)
    public void  updateRequisitionFirstExamByShenfenAndId(String firstexam ,Integer id);


    @Modifying
    @Query(value = "update requisition set requisition_secondexam=?1 where requisition_id=?2",nativeQuery = true)
    public void  updateRequisitionSecondExamByShenfenAndId(String secondexam ,Integer id);

    @Modifying
    @Query(value = "update requisition set requisition_thirdexam=?1 where requisition_id=?2",nativeQuery = true)
    public void  updateRequisitionThirdExamByShenfenAndId(String thirdexam ,Integer id);

    //根据参数requisition_number查询结果
    @Query(value = "select *  from requisition where requisition_number like CONCAT('%',?1,'%')",nativeQuery = true)
    public List<Requisition> getRequisitionListByRequsitionNumber(String requisition_number);

    //根据选择页数与关键字进行模糊搜索
    @Query(value = "select * from requisition where requisition_number like CONCAT('%',?1,'%')  order by requisition_id desc limit ?2,?3 ",nativeQuery = true)
    public List<Requisition> findRequisitionListByKeyAndPageNumAndNum(String key, Integer tiaozhuanPageNum, Integer num);

    @Query(value = "select  COUNT(*) from requisition  where requisition_number like CONCAT('%',?1,'%')",nativeQuery = true)
    public Integer getRequisitionTotalPageNumByKey(String key);
}

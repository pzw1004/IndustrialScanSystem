package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AuthorityRespository extends JpaRepository<Authority,Integer > {

    @Query(value = "select * from authority where role_requisition_id = ?1 ",nativeQuery = true)
    public Authority getAuthorityByRequisitionId(Integer requisition_id);

    @Query(value = "select * from authority where role_requisition_id = ?1 ",nativeQuery = true)
    public Boolean getAuthorityExsitByRequisitionId(Integer requisition_id);

    @Modifying
    @Query(value = "update authority set role=?1 where role_requisition_id=?2",nativeQuery = true)
    public void  updateRequisitionShenPiZhuangTaiByShenfenAndId(Integer role ,Integer requisition_id);


}

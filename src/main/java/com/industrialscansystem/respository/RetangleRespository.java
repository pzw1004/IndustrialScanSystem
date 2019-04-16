package com.industrialscansystem.respository;


import com.industrialscansystem.Bean.Retangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Transactional
@Repository
public interface RetangleRespository extends JpaRepository<Retangle,Integer> {


    //@Modifying
    @Query(value = "select * from retangle where retangle_picture_id=?1 ",nativeQuery = true)
    public List<Retangle>  selectRetangleListByPictureId(Integer retangle_picture_id);

    @Modifying
    @Query(value = "delete  from  retangle   where retangle_picture_id=?1 ",nativeQuery = true)
    public void deleteRetangleListByPictureId(Integer retangle_picture_id);

    @Query(value = "select * from retangle where retangle_rect_id=?1 ",nativeQuery = true)
    public Retangle getRetangleByRectId(String rect_id);

    @Modifying
    @Query(value = "insert into retangle(retangle_damage_type,retangle_rect_id) values(?1,?2)",nativeQuery = true)
    public void addRetangleDamageTypeAndRectId(Integer retangle_damage_type,String retangle_rect_id);

//    @Modifying
//    @Query(value = "insert into t_sys_org_user(org_id,user_id) values(?1,?2)",nativeQuery = true)
//    int addUserToOrg(Long orgId,Long userId);
}

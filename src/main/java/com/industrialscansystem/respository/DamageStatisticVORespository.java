package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.DamageStatisticVO;
import com.industrialscansystem.Bean.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DamageStatisticVORespository extends JpaRepository<DamageStatisticVO,Integer> {


    @Query(value = "select damagetype_id, damagetype_name, count(*) as count, count(*) as frequency, count(*) as id from polygon, damage_type where flag=1 and " +
            "polygon_picture_id=?1 and damagetype_id=polygon_damage_type group by polygon_damage_type",nativeQuery = true)
    public List<DamageStatisticVO> getDamageTypeStatisticByPictureId(Integer polygon_picture_id);

    @Query(value = "select damagetype_id, damagetype_name, count(*) as count, count(*) as frequency, count(*) as id from polygon, damage_type where flag=1 and " +
            "polygon_picture_id=?1 and damagetype_id=polygon_damage_type and polygon_author=?2 group by polygon_damage_type",nativeQuery = true)
    public List<DamageStatisticVO> getDamageTypeStatisticByPictureIdAndAuthor(Integer polygon_picture_id, String author);

    @Query(value = "select damagetype_id, damagetype_name, count(*) as count, count(*) as frequency, count(*) as id from polygon, damage_type, picture where flag=1 and " +
            "picture_requisition_id=?1 and damagetype_id=polygon_damage_type and picture_id=polygon_picture_id group by polygon_damage_type",nativeQuery = true)
    public List<DamageStatisticVO> getDamageTypeStatisticByRequisitionId(Integer polygon_picture_id);
}

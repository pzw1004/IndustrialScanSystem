package com.industrialscansystem.respository;
import com.industrialscansystem.Bean.DamageStatisticVO;
import com.industrialscansystem.Bean.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Transactional
@Repository
public interface PolygonRespository extends JpaRepository<Polygon,Integer>
{
    //@Modifying
    @Query(value = "select * from polygon, damage_type where flag=1 and polygon_picture_id=?1 and polygon.polygon_damage_type = damage_type.damagetype_id",nativeQuery = true)
    public List<Polygon>  selectPolygonListByPictureId(Integer polygon_picture_id);

    @Modifying
    @Query(value = "delete polygon from  polygon where polygon_picture_id=?1 ",nativeQuery = true)
    public void deletePolygonListByPictureId(Integer polygon_picture_id);

    @Modifying
    @Query(value = "delete polygon from  polygon where polygon_id=?1 ",nativeQuery = true)
    void deletePolygonById(Integer polygon_id);

    @Modifying
    @Query(value = "delete polygon from  polygon where polygon_picture_id=?1 and polygon_author = 'Algorithm'",nativeQuery = true)
    public void deleteAIPolygonListByPictureId(Integer polygon_picture_id);

//    @Query(value = "select * from polygon, damage_type where flag=1 and polygon_id=?1 ",nativeQuery = true)
//    public Polygon getPolygonByPolygonId(String polygon_id);

    @Query(value = "select * from polygon, damage_type where flag=1 and polygon_id=?1 ",nativeQuery = true)
    public Polygon getPolygonByPolygonId(Integer polygon_id);


}

package com.industrialscansystem.respository;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.PictureAndPolygon;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;


@Transactional

@Repository
public interface PictureRespository extends JpaRepository<Picture,Integer> {

    //模板内写Dao包内需要方法，主要是自定义Query数据库处理语句


    //根据参数picture_ps查询结果
    @Query(value = "select *  from picture where picture_ps = ?1",nativeQuery = true)
    public List<Picture> getByPicture_AIresult(String picture_ps);

    @Query(value = "select * from picture where (picture_id = ?1 )",nativeQuery = true)
    public Picture getPictureById(Integer picture_id);

    @Query(value = "select * from picture where (picture_dir = ?1 )",nativeQuery = true)
    Picture getPictureByDir(String picture_dir);

    @Query(value = "select *  from picture where picture_requisition_id = ?1",nativeQuery = true)
    public List<Picture> getByPictureRequisitionId(Integer requisition_id);

    @Query(value = "select *  from picture where picture_requisition_id = ?1 limit ?2,?3",nativeQuery = true)
    public List<Picture> getByPictureRequisitionIdWithPageInfo(Integer requisition_id, Integer tiao, Integer num);

    @Query(value = "select * from picture where picture_ps like CONCAT('%',?1,'%') limit ?2,?3 ",nativeQuery = true)
    public List<Picture> getByPicture_AIresultBefore6(String key, Integer tiao,Integer num);

    @Query(value = "select count(*) from picture where picture_ps like CONCAT('%',?1,'%')",nativeQuery = true)
    public Integer getByPicture_AIresultBeforezys(String key);

    @Query(value = "select * from picture where picture_AIresult = ?1 limit ?2,?3 ",nativeQuery = true)
    public List<Picture> getByPicture_AIresultBefore(String key, Integer tiao, Integer num);

    @Modifying
    @Query(value = "delete  from picture where picture_id=?1",nativeQuery = true)
     void  deletePictureByPicture_id(int picture_id);

    @Modifying
    @Query(value = "delete  from picture where picture_requisition_id=?1",nativeQuery = true)
    void  deletePictureByRequisitionId(int picture_requisition_id);

    @Modifying
    @Query(value = "update picture set picture_firstexamresult=?1 where picture_id=?2",nativeQuery = true)
    public void  updatePictureByIdAndExamFirstresult(String examresult ,Integer id);

    @Modifying
    @Query(value = "update picture set picture_secondexamresult=?1 where picture_id=?2",nativeQuery = true)
    public void  updatePictureByIdAndExamSecondresult(String examresult ,Integer id);


    @Modifying
    @Query(value = "update picture set picture_thickness=?1 where picture_id=?2",nativeQuery = true)
    public void  updatePicture_thicknessById(int examresult ,Integer id);

    @Modifying
    @Query(value = "update picture set picture_thirdexamresult=?1 where picture_id=?2",nativeQuery = true)
    public void  updatePictureByIdAndExamThirdresult(int thickness ,Integer id);

    @Modifying
    @Query(value = "update picture set picture_width=?1 , picture_height=?2 ,picture_transpath=?3 where picture_id=?4",nativeQuery = true)
    public void  updatePictureByIdAndPictureWidthAndHeight(Integer picture_width ,Integer picture_height,String picture_transpath,Integer id);

    //统计单个申请单下所有的影像图个数
    @Query(value = "select count(*) from picture where (picture_requisition_id = ?1 )",nativeQuery = true)
     int countRequisitionPictureNumber(int requisition_id);

    @Query(value = "select count(*) from picture where (picture_thickness = ?1 )",nativeQuery = true)
     int countPictureThicknessNumber(int picture_thickness);

    @Query(value = "select count(*) from picture where (picture_teststandard = ?1 )",nativeQuery = true)
     int countPictureTeststandardNumber(String picture_teststandard);

    @Query(value = "select count(*) from picture where (picture_qualifylevel = ?1 )",nativeQuery = true)
     int countPictureQualifylevelNumber(String picture_qualifylevel);

    @Query(value = "select count(*) from picture where (picture_testmethod = ?1 )",nativeQuery = true)
     int countPictureTestmethodNumber(String picture_testmethod);




    /**
     * 根据当前页影像图id获取上一页影像图信息
     * @return
     */
    @Query(value = "select * from picture where picture_id = (select picture_id from picture where picture_id < ?1 and picture_requisition_id = ?2 order by picture_id desc limit 1) ;",nativeQuery = true)
    public Picture getUpPagePicture(Integer picture_id,Integer requisition_id);

    /**
     * 根据当前页影像图id获取下一页影像图信息
     * @return
     */
    @Query(value = "select * from picture where picture_id = (select picture_id from picture where picture_id > ?1 and picture_requisition_id = ?2 order by picture_id asc limit 1) ;",nativeQuery = true)
    public Picture getNextPagePicture(Integer picture_id,Integer requisition_id);

    /**
     * 查询没有检测结果的图片
     * @return
     */
    @Query(value = "select * from picture where picture_AIresult = 0 ",nativeQuery = true)
    List<Picture> getPictureWithOutDetection();

    @Query(value = "SELECT new com.industrialscansystem.Bean.PictureAndPolygon(pic,pol,da) from Picture pic, Polygon pol, DamageType  da where pic.picture_requisition_id = ?1  AND pol.polygon_picture_id = pic.picture_id AND pol.polygon_damage_type = da.damagetype_id")
    List<PictureAndPolygon> getPicturePolygon(Integer requisition_id);
}

package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.DamageType;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Retangle;
import com.industrialscansystem.Bean.TemRetangle;
import com.industrialscansystem.respository.DamageTypeRespository;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RetangleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RectangleRestController {

    @Autowired
    private RetangleRespository retangleRespository;

    @Autowired
    private PictureRespository pictureRespository;

    @Autowired
    private DamageTypeRespository damageTypeRespository;

    @RequestMapping(value = "/getPictureRect")
    public List<Retangle> getPictureRect(@RequestParam("picture_id") Integer picture_id){

        List<Retangle> rectangleList = retangleRespository.selectRetangleListByPictureId(picture_id);
        return rectangleList;
    }

    @RequestMapping(value = "/getRectangleInfo")
    public Retangle getRectangleInfo(@RequestParam("rectangle_id") Integer rectangle_id){

        return retangleRespository.getRetangleByRetangleId(rectangle_id);
    }

    @RequestMapping(value = "/deleteRectById")
    public List<Retangle> deleteRectById(@RequestParam("rectangle_id") Integer rectangle_id,
                                   @RequestParam("picture_id") Integer picture_id){
        retangleRespository.deleteRetangleById(rectangle_id);
        return retangleRespository.selectRetangleListByPictureId(picture_id);
    }

    @RequestMapping(value = "/getDamageTypeInfo")
    public DamageType getDamageTypeInfo(@RequestParam("damagetype_id") Integer damagetype_id){

        return damageTypeRespository.getDamageTypeNameByDamagetypeId(damagetype_id);
    }

    @RequestMapping(value = "/updateRectangleDamageType")
    public List<Retangle> updateRectangleDamageType(@RequestParam("rectangle_id") Integer rectangle_id,
                                                    @RequestBody DamageType damageType ){

        Retangle retangle = retangleRespository.getRetangleByRetangleId(rectangle_id);
        retangle.setRetangle_damage_type(damageType.getDamagetype_id());
        retangleRespository.save(retangle);

        return retangleRespository.findAll();
    }

    @RequestMapping(value = "/updateRectangleDamageTypeByDamageType")
    public List<Retangle> updateRectangleDamageTypeByDamageType(@RequestParam("rectangle_id") Integer rectangle_id,
                                                    @RequestParam("damagetype_id") Integer damagetype_id ,
                                                    @RequestParam("picture_id") Integer picture_id ){

        Retangle retangle = retangleRespository.getRetangleByRetangleId(rectangle_id);
        retangle.setRetangle_damage_type(damagetype_id);
        retangleRespository.save(retangle);

        return retangleRespository.selectRetangleListByPictureId(picture_id);
    }

    @RequestMapping(value = "/getDamageTypeList")
    public List<DamageType> getRectangleInfo(){

        return damageTypeRespository.getAllVaildDamageTypeName();
    }

    @RequestMapping(value = "/getRectangleArray" )
    public List<Retangle> getRectangleArray(@RequestBody List<TemRetangle> damageJsonList,
                        @RequestParam("picture_id")  Integer picture_id){///

        //List<JSONObject> damageJsonList = JSONObject.parseArray(params, JSONObject.class);//把字符串转换成集合

        retangleRespository.deleteRetangleListByPictureId(picture_id);
        Picture picture = pictureRespository.findOne(picture_id);

        int tempWidth = picture.getPicture_width();
        int tempHeight = picture.getPicture_height();

        for( int i = 0 ; i < damageJsonList.size() ; i++) {
            Retangle retangle = new Retangle();

            int x1 = damageJsonList.get(i).getX1();
            int y1 = damageJsonList.get(i).getY1();
            int x2 = damageJsonList.get(i).getX2();
            int y2 = damageJsonList.get(i).getY2();
            String retangle_author = damageJsonList.get(i).getRetangle_author();
            Integer retangle_damage_type = damageJsonList.get(i).getRetangle_damage_type();
            Double conf = damageJsonList.get(i).getConf();
            Double cls_conf = damageJsonList.get(i).getCls_conf();
            x1 = new Double(x1 * tempWidth/1000.0).intValue();
            y1 = new Double(y1 * tempHeight/300.0).intValue();
            x2 = new Double(x2 * tempWidth/1000.0).intValue();
            y2 = new Double(y2 * tempHeight/300.0).intValue();


            retangle.setRetangle_x1(x1);
            retangle.setRetangle_x2(x2);
            retangle.setRetangle_y1(y1);
            retangle.setRetangle_y2(y2);
            retangle.setRetangle_picture_id(picture_id);
            retangle.setRetangle_author(retangle_author);  //修改为矩形框添加者
            //retangle.setRetangle_damage_type(0);
            retangle.setRetangle_damage_type(retangle_damage_type);
            retangle.setConf(conf);
            retangle.setCls_conf(cls_conf);
            retangleRespository.save(retangle);
        }

        return  retangleRespository.selectRetangleListByPictureId(picture_id);
    }
}

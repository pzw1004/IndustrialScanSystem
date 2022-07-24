package com.industrialscansystem.Controller;

import com.industrialscansystem.Bean.*;
import com.industrialscansystem.respository.DamageStatisticVORespository;
import com.industrialscansystem.respository.DamageTypeRespository;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.PolygonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PolygonRestController {
    @Autowired
    private PolygonRespository polygonRespository;

    @Autowired
    private PictureRespository pictureRespository;

    @Autowired
    private DamageTypeRespository damageTypeRespository;

    @RequestMapping(value = "/getPicturePolygon")
    public List<Polygon> getPicturePolygon(@RequestParam("picture_id") Integer picture_id){

        List<Polygon> polygonList = polygonRespository.selectPolygonListByPictureId(picture_id);
        return polygonList;
    }

    @RequestMapping(value = "/deletePolygonById")
    public List<Polygon> deletePolygonById(@RequestParam("polygon_id") Integer polygon_id,
                                         @RequestParam("picture_id") Integer picture_id){
        polygonRespository.deletePolygonById(polygon_id);
        return polygonRespository.selectPolygonListByPictureId(picture_id);
    }

    @RequestMapping(value = "/getPolygonArray" )
    public List<Polygon> getPolygonArray(@RequestBody List<Polygon> damageJsonList,
                                            @RequestParam("picture_id")  Integer picture_id){///

        //List<JSONObject> damageJsonList = JSONObject.parseArray(params, JSONObject.class);//把字符串转换成集合

        polygonRespository.deletePolygonListByPictureId(picture_id);


        for( int i = 0 ; i < damageJsonList.size() ; i++)
        {
            System.out.println(damageJsonList.get(i).getPolygon_damage_type());
            if(damageJsonList.get(i).getPolygon_damage_type()!=null)
            {
                Polygon polygon = new Polygon();
                //拿出前端polygonArray的信息，和polygon的bean一致
                String polygon_pt = damageJsonList.get(i).getPolygon_pt();
                String polygon_author = damageJsonList.get(i).getPolygon_author();
                Integer polygon_damage_type = damageJsonList.get(i).getPolygon_damage_type();
                Integer polygon_text_x = damageJsonList.get(i).getPolygon_text_x();
                Integer polygon_text_y = damageJsonList.get(i).getPolygon_text_y();

                System.out.println(polygon_pt);
                System.out.println("(x,y)="+polygon_text_x+" "+polygon_text_y);
                polygon.setPolygon_pt(polygon_pt);
                polygon.setPolygon_picture_id(picture_id);
                polygon.setPolygon_author(polygon_author);  //修改为矩形框添加者
                polygon.setPolygon_damage_type(polygon_damage_type);
                polygon.setPolygon_text_x(polygon_text_x);
                polygon.setPolygon_text_y(polygon_text_y);

                polygonRespository.save(polygon);
            }

        }

        return  polygonRespository.selectPolygonListByPictureId(picture_id);
    }


    @RequestMapping(value = "/updatePolygonDamageTypeByDamageType")
    public List<Polygon> updatePolygonDamageTypeByDamageType(@RequestParam("polygon_id") Integer polygon_id,
                                                                @RequestParam("damagetype_id") Integer damagetype_id ,
                                                                @RequestParam("picture_id") Integer picture_id ){

        Polygon polygon = polygonRespository.getPolygonByPolygonId(polygon_id);
        polygon.setPolygon_damage_type(damagetype_id);
        polygonRespository.save(polygon);

        return polygonRespository.selectPolygonListByPictureId(picture_id);
    }

}

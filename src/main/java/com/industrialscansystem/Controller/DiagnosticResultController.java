package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.DamageType;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Retangle;
import com.industrialscansystem.respository.DamageTypeRespository;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RetangleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DiagnosticResultController {

    @Autowired
    private PictureRespository pictureRespository;

    @Autowired
    private DamageTypeRespository damageTypeRespository;

    @Autowired
    private RetangleRespository retangleRespository;



    @PostMapping("/RectangleDamageSave")
    public void saveRectangleDamage(@RequestParam("rect_id") Integer rect_id,
                                    @RequestParam("damagetype_id") Integer damagetype_id,
                                    @RequestParam("x1") Integer x1,
                                    @RequestParam("x2") Integer x2,
                                    @RequestParam("y1") Integer y1,
                                    @RequestParam("y2") Integer y2,
                                    @RequestParam("picture_id") Integer picture_id,
                                    @RequestParam("retangle_author") String retangle_author)
    {
        Picture picture = pictureRespository.findOne(picture_id); //取出真实长度

        int tempWidth = picture.getPicture_width();
        int tempHeight = picture.getPicture_height();

        Retangle tempretangle = retangleRespository.findOne(rect_id);

          //  retangleRespository.addRetangleDamageTypeAndRectId(damagetype_id,rect_id);
            x1 = new Double(x1 * tempWidth/1400.0).intValue();
            y1 = new Double(y1 * tempHeight/400.0).intValue();
            x2 = new Double(x2 * tempWidth/1400.0).intValue();
            y2 = new Double(y2 * tempHeight/400.0).intValue();
           // Retangle tempretangle = new Retangle();
            tempretangle.setRetangle_x1(x1);
            tempretangle.setRetangle_x2(x2);
            tempretangle.setRetangle_y1(y1);
            tempretangle.setRetangle_y2(y2);
            tempretangle.setRetangle_picture_id(picture_id);
            tempretangle.setRetangle_damage_type(damagetype_id);
            //tempretangle.setRetangle_rect_id(rect_id);
            tempretangle.setRetangle_author(retangle_author); //后续填当前操作者

            retangleRespository.save(tempretangle);




    }




//    @PostMapping("/DiagnosticResults")
//
//    public Object getByPicture_AIresult(@RequestParam("picture_AIresult") String picture_AIresult , Model model){
//       List<Picture> pictureList = pictureRespository.getByPicture_AIresult(picture_AIresult);
//        return pictureList;
//    }
//
//    @PostMapping("/DiagnosticResultsSearch")
//
//    public Object getDiagnosticResultsUp9(@RequestParam("page") Integer page,
//                                          @RequestParam("key")String key) {
//        Integer num = 6;
//        Integer tiao = (page - 1) * num;
//        List<Picture> pictureList = pictureRespository.getByPicture_AIresultBefore(key, tiao, num);
//
//        return pictureList;
//
//
//    }
//
//    @PostMapping("/DiagnosticResultsTotalPage")
//
//    public  Integer getDiagnosticResultsTotalPage(@RequestParam("key")String key) {
//
//        Integer num = 6;
//        Integer totalpages = pictureRespository.getByPicture_AIresultBeforezys(key);
//        return totalpages;
//
//    }


}

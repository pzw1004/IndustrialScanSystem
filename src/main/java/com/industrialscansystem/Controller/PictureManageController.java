package com.industrialscansystem.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.DamageDetectMessage;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Retangle;
import com.industrialscansystem.Service.DetectionService;
import com.industrialscansystem.Service.DetectionThread;
import com.industrialscansystem.Service.LogSaveService;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RetangleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PictureManageController {

    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @Autowired
    private PictureRespository pictureRespository;
    @Autowired
    private RetangleRespository retangleRespository;
    @Autowired
    private DetectionService detectionService;

//    @RequestMapping(value = "/Imgtable_show")
//    public Object getPicture(@RequestParam("id") Integer picture_id){
//
//        Picture picture = pictureRespository.findOne(picture_id);
//
//        return picture;
//    }

    /**
     * 需要等待计算结果的url
     * @param picture_id
     * @throws IOException
     */
    @PostMapping("/detectionById_wait")
    @ResponseBody
    public void sendMeaasge(@RequestParam("id") Integer picture_id) throws IOException {
        // 文件sTestsetFile：solr_etl_agent35.json是存有JSON字符串的文件
        Picture picture = pictureRespository.findOne(picture_id);
        picture.setPicture_AIresult(1);
        pictureRespository.save(picture);

        JSONObject jsonObject = detectionService.detectOneImage(picture);
        DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,picture_id);
        List<Retangle> retangleList = detectMessage.getDamageDataList();
        retangleRespository.save(retangleList);
        picture.setPicture_AIresult(2);
        pictureRespository.save(picture);

// {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}

        System.out.println("---------------------------");


    }

    /**
     * 不需要等待结果，接收消息之后，新建一个线程在后台运行。
     * @param picture_id
     */
    @PostMapping("/detectionById_backstage")
    @ResponseBody
    public void detectionByIdBackstage(@RequestParam("id") Integer picture_id) {
        Picture picture = pictureRespository.findOne(picture_id);
        DetectionThread detectionThread = new DetectionThread(picture.getPicture_id());

        detectionThread.start();
//        JSONObject jsonObject = detectionService.detectOneImage(picture);
//        DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,picture_id);
//        List<Retangle> retangleList = detectMessage.getDamageDataList();
//
//// {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}
//        retangleRespository.save(retangleList);
//        System.out.println("---------------------------");


    }

    @PostMapping(value = "startTheard")
    public void startThread(){

    }

    @PostMapping(value = "getstatus")
    public void getStatus(){
        String ss = Thread.currentThread().getName();
        System.out.println(ss);
    }

    @GetMapping(value = "/getRectangleArray" )
    public void fff(@RequestParam("rectangleJSON")  String params,
                    @RequestParam("picture_id")  Integer picture_id

    ){///


        List<JSONObject> damageJsonList = JSONObject.parseArray(params, JSONObject.class);//把字符串转换成集合

           retangleRespository.deleteRetangleListByPictureId(picture_id);

           Picture picture = pictureRespository.findOne(picture_id);

        int tempWidth = picture.getPicture_width();
        int tempHeight = picture.getPicture_height();



        for( int i = 0 ; i < damageJsonList.size() ; i++) {

            int x1 = damageJsonList.get(i).getInteger("x1");
            int y1 = damageJsonList.get(i).getInteger("y1");
            int x2 = damageJsonList.get(i).getInteger("x2");
            int y2 = damageJsonList.get(i).getInteger("y2");
            String retangle_author = damageJsonList.get(i).getString("retangle_author");
            x1 = new Double(x1 * tempWidth/1400.0).intValue();
            y1 = new Double(y1 * tempHeight/400.0).intValue();
            x2 = new Double(x2 * tempWidth/1400.0).intValue();
            y2 = new Double(y2 * tempHeight/400.0).intValue();

            Retangle retangle = new Retangle();
           retangle.setRetangle_x1(x1);
           retangle.setRetangle_x2(x2);
           retangle.setRetangle_y1(y1);
           retangle.setRetangle_y2(y2);
           retangle.setRetangle_picture_id(picture_id);
           retangle.setRetangle_author(retangle_author);  //修改为矩形框添加者
           retangle.setRetangle_damage_type(0);
           retangleRespository.save(retangle);



        }











    }


}

package com.industrialscansystem.Service;

import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.DamageDetectMessage;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Retangle;
import com.industrialscansystem.Config.SpringBeanUtil;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RetangleRespository;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author 宋宗垚
 * @Date 2019/2/26 15:36
 * @Description TODO
 */
//@Service
//@Component
public class DetectionThread extends Thread {

    private PictureRespository pictureRespository;
    private DetectionService detectionService;
    private RetangleRespository retangleRespository;
    private boolean isCyclically;//表示是否循环执行，在守护线程中，传入的是true，在单次执行的时候，传入的是false
    private int detectionId;
    /**
     * 循环执行检测的构造方法
     * @param
     */
    public DetectionThread(){
        this.isCyclically = true;
        this.detectionId = -1;
        this.pictureRespository = SpringBeanUtil.getBean(PictureRespository.class);
        this.detectionService = SpringBeanUtil.getBean(DetectionService.class);
        this.retangleRespository = SpringBeanUtil.getBean(RetangleRespository.class);
        this.setName("DetectionThread_Cyclically");

    }

    /**
     * 只进行单次检测的构造方法
     * @param id
     */
    public DetectionThread(int id){
        this.detectionId = id;
        this.isCyclically = false;
        this.pictureRespository = SpringBeanUtil.getBean(PictureRespository.class);
        this.detectionService = SpringBeanUtil.getBean(DetectionService.class);
        this.retangleRespository = SpringBeanUtil.getBean(RetangleRespository.class);
        this.setName("DetectionThread_Once");

    }

    public void run(){
        System.out.println("开启执行线程"+this.getName());
//        throw new NullPointerException();
        // 如果不循环执行，那么只执行一次

        if(isCyclically){
            // 如果循环执行，那么暂时设置执行100次
            int excuteTime = 100;
            for (int i = 0;i<excuteTime;i++){
                // TODO 这里需要更新为在数据库中查找没有被检测过的图片。如果检测结果为空则break出去
                List<Picture> pictures = new ArrayList<>();
                pictures = pictureRespository.getPictureWithOutDetection();
//                Picture pp = pictureRespository.findOne(35);
//                pictures.add(pp);
                if (pictures.size()!=0){
                    Picture picture =  pictures.get(0);
                    // 1代表正在检测中
                    picture.setPicture_AIresult(1);
                    pictureRespository.save(picture);
                    JSONObject jsonObject = detectionService.detectOneImage(picture);
                    if(null==jsonObject){
                        continue;
                    }
                    DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,picture.getPicture_id());
                    List<Retangle> retangleList = detectMessage.getDamageDataList();
                    // {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}
                    retangleRespository.save(retangleList);
                    // 2代表检测完成
                    picture.setPicture_AIresult(2);
                    pictureRespository.save(picture);

                }else {
                    break;
                }
                System.out.println("-------------线程中--------------" );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }else {
            // 如果只是执行一次。
            Picture pp = pictureRespository.findOne(this.detectionId);
            pp.setPicture_AIresult(1);
            pictureRespository.save(pp);
            JSONObject jsonObject = detectionService.detectOneImage(pp);
            if(null!=jsonObject){
                DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,pp.getPicture_id());
                List<Retangle> retangleList = detectMessage.getDamageDataList();
                // {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}
                retangleRespository.save(retangleList);
                // 2代表检测完成
                pp.setPicture_AIresult(2);
                pictureRespository.save(pp);
                // TODO:向指定用户发送一个消息（弹出窗口或者消息栏等方式，提醒用户这个图片检测完成），没想好怎么做
            }

        }


    }


    public PictureRespository getPictureRespository() {
        return pictureRespository;
    }

    public void setPictureRespository(PictureRespository pictureRespository) {
        this.pictureRespository = pictureRespository;
    }
}

package com.industrialscansystem.Service;

import com.alibaba.fastjson.JSON;
import com.industrialscansystem.Bean.*;
import com.industrialscansystem.Config.SpringBeanUtil;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.PolygonRespository;
import com.industrialscansystem.respository.RetangleRespository;
import com.industrialscansystem.Controller.util.EnvironmentPath;

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
    private int width;
    private PolygonRespository polygonRespository;
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
    public DetectionThread(int id,int width){
        this.detectionId = id;
        this.width = width;
        this.isCyclically = false;
        this.pictureRespository = SpringBeanUtil.getBean(PictureRespository.class);
        this.detectionService = SpringBeanUtil.getBean(DetectionService.class);
        this.retangleRespository = SpringBeanUtil.getBean(RetangleRespository.class);
        this.polygonRespository = SpringBeanUtil.getBean(PolygonRespository.class);
        this.setName("DetectionThread_Once");

    }

    @Override
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
                    System.out.println("\n"+" run():"+picture.getPicture_id());


                    System.out.println("====================if========================");
                    String res = detectionService.detectOneImage_v2(picture,width);
                    System.out.println(res);

//                    DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,picture.getPicture_id());
//                    List<Retangle> retangleList = detectMessage.getDamageDataList();
//                    // {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}
//                    retangleRespository.save(retangleList);
//                    // 2代表检测完成
//                    picture.setPicture_AIresult(2);
//                    pictureRespository.save(picture);

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
            String tempDir = pp.getPicture_dir();

            String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();


            // wpz:图片无法显示的问题所在：
//            pp.setPicture_dir(tomcatPath+pp.getPicture_dir());
            pp.setPicture_dir(pp.getPicture_dir());
            //pp.setPicture_dir("A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+pp.getPicture_dir());

            pp.setPicture_AIresult(1);
//            pictureRespository.save(pp);
            pictureRespository.save(pp);
            System.out.println("=======else=======");
            System.out.println(pp.getPicture_dir());
            String res = detectionService.detectOneImage_v2(pp,width);

            System.out.println(res);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!从这里开始要不一样了");

            ResultFromDetection resultFromDetection = JSON.parseObject(res, ResultFromDetection.class);

//            JSONArray ja = JSONArray.parseArray(res);
            String[][] flawLength = resultFromDetection.getFlawLength();
            String[][] points = resultFromDetection.getPosition();
            String[][] beliefs = resultFromDetection.getBelief();
            String houdu = resultFromDetection.getHoudu();
            String[] edge = resultFromDetection.getEdge();
            for(int i = 0; i < points.length; i++){
//                JSONArray ja_item = JSONArray.parseArray(ja.getString(i));
//                points[i] = new String[ja_item.size()];
                for(int j = 0; j < points[i].length; j++) {
//                    points[i][j] = ja_item.getString(j);
                    System.out.println("=======================" + i + ", " + j + "========================");
                    System.out.println(points[i][j]);
                }
            }
//            JSONArray ja1 = JSONArray.parseArray(ja.getString(0));
//            System.out.println(ja1.getString(0));

//            int beginIndex = 1;
//            int endIndex = res.length()-1;
//            String res_sub = res.substring(beginIndex,endIndex);
//            res_sub = res_sub.substring(1,res_sub.length()-1);
//            System.out.println(res_sub);
//
//            String[] str_arr = res_sub.split("\",");

            try{
                polygonRespository.deletePolygonListByPictureId(pp.getPicture_id());

                Polygon polygon1 = new Polygon();
                polygon1.setPolygon_pt(edge[0]);
                polygon1.setPolygon_picture_id(pp.getPicture_id());
                polygon1.setPolygon_author("Algorithm");
                polygon1.setPolygon_damage_type(6);
                polygonRespository.save(polygon1);
//                pictureRespository.updatePicture_thicknessById(Integer.parseInt(houdu),pp.getPicture_id());
                for(int i = 0; i < points.length; i++){
                    for(int j = 0; j < points[i].length; j++){
                        String detect_list=points[i][j];
                        System.out.println("============chuli========");
                        System.out.println("==============22=========");
                        System.out.println("第"+ (i + 1) + "类， 第" + (j + 1) + "个点");
                        System.out.println(detect_list);
                        if (detect_list == null || "".equals(detect_list)) {
                            break;
                        }
                        int lastIndex = detect_list.lastIndexOf(" ");
                        // 缺陷点
                        String detect_points = detect_list.substring(0,lastIndex);
                        // top点
                        String top_wh = detect_list.substring(lastIndex+1, detect_list.length());
                        int w = Integer.parseInt(top_wh.split(",")[0]);
                        int h = Integer.parseInt(top_wh.split(",")[1]);
                        System.out.println(detect_points);
                        System.out.println(w+" " + h);

                        System.out.println("picture id:" + pp.getPicture_id());
                        // polygon
                        Polygon polygon = new Polygon();
                        polygon.setPolygon_pt(detect_points);
                        polygon.setPolygon_picture_id(pp.getPicture_id());
                        polygon.setPolygon_author("Algorithm");
                        polygon.setPolygon_damage_type(i);
                        polygon.setPolygon_text_x(w);
                        polygon.setPolygon_text_y(h);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + beliefs[i][j] + "!!!!!!!!!!!!!!!!!!!!!!!!");
                        polygon.setPolygon_belief(Float.valueOf(beliefs[i][j]));
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + flawLength[i][j] + "!!!!!!!!!!!!!!!!!!!!!!!!");
                        polygon.setPolygon_flaw_length(Float.parseFloat(flawLength[i][j]));
                        polygonRespository.save(polygon);
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("检测出错");
            }
            finally {
                pp.setPicture_dir(tempDir);
                pictureRespository.save(pp);
                pictureRespository.updatePicture_thicknessById(Integer.parseInt(houdu),pp.getPicture_id());
            }
//            try
//            {
//                for (int i=0;i<str_arr.length;i++)
//                {
//                    String detect_list="";
//                    System.out.println("============chuli========");
//                    System.out.println(str_arr[i]);
//                    System.out.println("==============22=========");
//                    if (i == 0){  //第一个点没有引号，所以要区分
//                        detect_list = str_arr[i];
//                    }else{
//                        detect_list = str_arr[i].substring(1);
//                        // 对字符串进行遍历
//                    }
//                    System.out.println("第"+(i+1)+"个点");
//                    System.out.println(detect_list);
//                    int lastIndex = detect_list.lastIndexOf(" ");
//                    // 缺陷点
//                    String detect_points = detect_list.substring(0,lastIndex);
//                    // top点
//                    String top_wh = detect_list.substring(lastIndex+1,detect_list.length());
//                    int w = Integer.parseInt(top_wh.split(",")[0]);
//                    int h = Integer.parseInt(top_wh.split(",")[1]);
//                    System.out.println(detect_points);
//                    System.out.println(w+" " + h);
//
//                    System.out.println("picture id:" + pp.getPicture_id());
//                    // polygon
//                    Polygon polygon = new Polygon();
//                    polygon.setPolygon_pt(detect_points);
//                    polygon.setPolygon_picture_id(pp.getPicture_id());
//                    polygon.setPolygon_author("Algorithm");
//                    polygon.setPolygon_damage_type(0);
//                    polygon.setPolygon_text_x(w);
//                    polygon.setPolygon_text_y(h);
//                    polygonRespository.save(polygon);
//
//                }
//            }
//            catch (Exception e)
//            {
//                System.out.println("检测出错");
//            }
//            finally {
//                pp.setPicture_dir(tempDir);
//                pictureRespository.save(pp);
//            }


//            System.out.println(pp.getPicture_id());


//
//            if(null!=jsonObject){
//                DamageDetectMessage detectMessage = new DamageDetectMessage(jsonObject,pp.getPicture_id());
//                List<Retangle> retangleList = detectMessage.getDamageDataList();
//                // {"damageDataList":[{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":774},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":121,"y":806},{"author":"Algorithm","timestamp":1551149845732,"x":153,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":153,"y":870},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":806},{"author":"Algorithm","timestamp":1551149845733,"x":185,"y":838},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1126},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1158},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1190},{"author":"Algorithm","timestamp":1551149845733,"x":121,"y":1222}],"timestamp":"2019-02-26 10:54:26.797","status":"SUCCESS","info":"nothing"}
//                retangleRespository.save(retangleList);
//                // 2代表检测完成
//                pp.setPicture_AIresult(2);
//                pictureRespository.save(pp);
//                // TODO:向指定用户发送一个消息（弹出窗口或者消息栏等方式，提醒用户这个图片检测完成），没想好怎么做
//            }

        }


    }


    public PictureRespository getPictureRespository() {
        return pictureRespository;
    }

    public void setPictureRespository(PictureRespository pictureRespository) {
        this.pictureRespository = pictureRespository;
    }
}

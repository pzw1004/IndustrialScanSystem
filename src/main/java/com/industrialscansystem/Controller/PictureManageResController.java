package com.industrialscansystem.Controller;

import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.*;
import com.industrialscansystem.Mapper.PictureMapper;
import com.industrialscansystem.Service.DetectionService;
import com.industrialscansystem.Service.DetectionThread;
import com.industrialscansystem.Service.ImageRotate;
import com.industrialscansystem.respository.DamageStatisticVORespository;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.PolygonRespository;
import com.industrialscansystem.respository.RetangleRespository;
import com.industrialscansystem.util.DeleteFileUtil;
import com.industrialscansystem.util.EnvironmentPath;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
public class PictureManageResController {
    @Autowired
    private PolygonRespository polygonRespository;
    @Autowired
    private PictureRespository pictureRespository;
    @Autowired
    private RetangleRespository retangleRespository;
    @Autowired
    private DetectionService detectionService;

    @Autowired
    private ImageRotate imageRotate;
    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    private DamageStatisticVORespository damageStatisticVORespository;

    @RequestMapping(value = "/sendToApitest/{picture_id}")
    public String sendToApitest(@PathVariable("picture_id") int picture_id) throws InterruptedException {
        List<Polygon> polygonList = polygonRespository.selectPolygonListByPictureId(picture_id);
        Picture picture = pictureRespository.getPictureById(picture_id);
        DetectionThread detectionThread = new DetectionThread(picture.getPicture_id());
        System.out.println(picture.getPicture_id());
        detectionThread.start();
        detectionThread.join();
        return "success";
    }

    @RequestMapping(value = "/horizontaFilpPicture/{picture_id}")
    public String horizontaFilpPicture(@PathVariable("picture_id") int picture_id) throws Exception {
        Picture picture = pictureRespository.getPictureById(picture_id);
        imageRotate.horizontaFilpPicture(picture);
        return "success";
    }

    @RequestMapping(value = "/verticalFilpPicture/{picture_id}")
    public String verticalFilpPicture(@PathVariable("picture_id") int picture_id) throws Exception {
        Picture picture = pictureRespository.getPictureById(picture_id);
        imageRotate.horizontaFilpPicture(picture);
        return "success";
    }
    @RequestMapping(value = "/deletePictureById/{picture_id}")
    public List<Picture>  deletePictureById(@PathVariable("picture_id") int picture_id){
        Picture picture = pictureRespository.getPictureById(picture_id);
        pictureRespository.deletePictureByPicture_id(picture_id);
        retangleRespository.deleteRetangleListByPictureId(picture_id);
        DeleteFileUtil deleteFileUtil = new DeleteFileUtil();

        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        deleteFileUtil.deleteFile(tomcatPath+picture.getPicture_dir());
        //deleteFileUtil.deleteFile("A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+picture.getPicture_dir());
        return pictureRespository.findAll();
    }

    @RequestMapping(value = "/updatePicture")
    public Picture updatePicture(@RequestBody Picture picture)
    {

        return pictureRespository.save(picture);
    }


    @RequestMapping(value = "/getPictureListByDistinctTestmethod")
    public List<Picture> getPictureListByDistinctTestmethod(){
        return pictureMapper.getPictureListByDistinctTestmethod();
    }

    @RequestMapping(value = "/getPictureListByDistinctJointform")
    public List<Picture> getPictureListByDistinctJointform(){
        return pictureMapper.getPictureListByDistinctJointform();
    }

    @RequestMapping(value = "/getPictureListByDistinctQualifylevel")
    public List<Picture> getPictureListByDistinctQualifylevel(){
        return pictureMapper.getPictureListByDistinctQualifylevel();
    }

    @RequestMapping(value = "/getPictureListByDistinctTeststandard")
    public List<Picture> getPictureListByDistinctTeststandard(){
        return pictureMapper.getPictureListByDistinctTeststandard();
    }

    @RequestMapping(value = "/getPictureListByDistinctThickness")
    public List<Picture> getPictureListByDistinctThickness(){
        return pictureMapper.getPictureListByDistinctThickness();
    }


    @RequestMapping(value = "/countPictureThicknessNumber/{picture_thickness}")
    public int countPictureThicknessNumber(@PathVariable("picture_thickness") int picture_thickness){
        return pictureRespository.countPictureThicknessNumber(picture_thickness);
    }

    @RequestMapping(value = "/countPictureQualifylevelNumber/{picture_qualifylevel}")
    public int countPictureQualifylevelNumber(@PathVariable("picture_qualifylevel") String picture_qualifylevel){
        System.out.println("picture_qualifylevel: "+picture_qualifylevel);
        return pictureRespository.countPictureQualifylevelNumber(picture_qualifylevel);
    }

    @RequestMapping(value = "/countPictureTestmethodNumber/{picture_testmethod}")
    public int countPictureTestmethodNumber(@PathVariable("picture_testmethod") String picture_testmethod){
        return pictureRespository.countPictureTestmethodNumber(picture_testmethod);
    }

//    @RequestMapping(value = "/countPictureTeststandardNumber/{picture_teststandard}")
//    public int countPictureTeststandardNumber(@PathVariable("picture_teststandard") String picture_teststandard){
//        System.out.println("picture_teststandard： "+picture_teststandard);
//        String picture_teststandard_tmp = picture_teststandard;
//        if(picture_teststandard.contains("%2F"))
//            picture_teststandard.replace("%2F","/");
//        return pictureRespository.countPictureTeststandardNumber(picture_teststandard);
//    }
    @RequestMapping(value = "/countPictureTeststandardNumber")
    public int countPictureTeststandardNumber(@RequestParam("picture_teststandard") String picture_teststandard){
        System.out.println("picture_teststandard： "+picture_teststandard);

        if(picture_teststandard.contains("%2F"))//因为检测标准可能包含 /，传输时为转为%2F，查询数据库要再转回来
            picture_teststandard.replace("%2F","/");
        return pictureRespository.countPictureTeststandardNumber(picture_teststandard);
    }

    @RequestMapping(value = "/getUpPagePicture/{picture_id}")
    public int getUpPagePicture(@PathVariable("picture_id") Integer picture_id){
        Picture picture = pictureRespository.getPictureById(picture_id);
        Picture temPic = pictureRespository.getUpPagePicture(picture_id,picture.getPicture_requisition_id());
        if(temPic==null){
            return picture.getPicture_id();
        }else {
            return temPic.getPicture_id();
        }

    }

    @RequestMapping(value = "/getNextPagePicture/{picture_id}")
    public int getNextPagePicture(@PathVariable("picture_id") Integer picture_id){
        Picture picture = pictureRespository.getPictureById(picture_id);
        Picture temPic = pictureRespository.getNextPagePicture(picture_id,picture.getPicture_requisition_id());
        if(temPic==null){
            return picture.getPicture_id();
        }else {
            return temPic.getPicture_id();
        }
    }


    @GetMapping(value = "/getPictureList")
    public Object getPictureList( ){

        List<Picture> pictureList = pictureRespository.findAll();
        // JSONArray jsonArray = JSONArray.fromObject(memberList);
        return pictureList;
    }

    @RequestMapping(value = "/getPictureListByReqId")
    public List<Picture> getPictureListByReqId(@PathParam("requisition_id") Integer requisition_id){
        return pictureRespository.getByPictureRequisitionId(requisition_id);
    }

    @RequestMapping(value = "/getPictureListByReqIdWithPageInfo")
    public List<Picture> getPictureListByReqIdWithPageInfo(@PathParam("requisition_id") Integer requisition_id,
                                                           @PathParam("pageIndex") Integer pageIndex,
                                                           @PathParam("pageSize") Integer pageSize){
        return pictureRespository.getByPictureRequisitionIdWithPageInfo(requisition_id,
                pageIndex * pageSize, pageSize);
    }

    @RequestMapping(value = "/getPicture")
    public Picture getPicture(@RequestParam("picture_id") Integer picture_id){
        Picture picture = pictureRespository.getPictureById(picture_id);

        return picture;
    }


    @RequestMapping(value = "/getPictureDamageTypeStatistic")
    public List<DamageStatisticVO> getPictureDamageTypeStatistic(@RequestParam("picture_id") Integer picture_id){

        List<DamageStatisticVO> damageStatisticVOList = damageStatisticVORespository.getDamageTypeStatisticByPictureId(picture_id);
        Integer total = 0;
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            total += damageStatisticVO.getCount();
        }
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            damageStatisticVO.setFrequency(Double.valueOf(damageStatisticVO.getCount().toString()) / total);
        }
        return damageStatisticVOList;
    }

    @RequestMapping(value = "/getPictureDamageTypeStatisticWithSpecificAuthor")
    public List<DamageStatisticVO> getPictureDamageTypeStatisticWithSpecificAuthor(@RequestParam("picture_id") Integer picture_id,
                                                                                   @RequestParam("author") String author){

        List<DamageStatisticVO> damageStatisticVOList = damageStatisticVORespository.getDamageTypeStatisticByPictureIdAndAuthor(picture_id, author);
        Integer total = 0;
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            total += damageStatisticVO.getCount();
        }
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            damageStatisticVO.setFrequency(Double.valueOf(damageStatisticVO.getCount().toString()) / total);
        }
        return damageStatisticVOList;
    }

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
    public  List<Retangle> detectionByIdBackstage(@RequestParam("id") Integer picture_id) throws InterruptedException {
        Picture picture = pictureRespository.findOne(picture_id);


        retangleRespository.deleteAIRetangleListByPictureId(picture_id);
        DetectionThread detectionThread = new DetectionThread(picture.getPicture_id());
        System.out.println(picture.getPicture_id());
        detectionThread.start();
        detectionThread.join();

        List<Retangle> rectangleList = retangleRespository.selectRetangleListByPictureId(picture_id);
        return rectangleList;
    }

    @PostMapping(value = "startTheard")
    public void startThread(){

    }

    @PostMapping(value = "getstatus")
    public void getStatus(){
        String ss = Thread.currentThread().getName();
        System.out.println(ss);
    }


}

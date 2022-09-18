package com.industrialscansystem.Controller;

import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.*;
import com.industrialscansystem.Config.RequiredTypes;
import com.industrialscansystem.Mapper.RequisitionMapper;
import com.industrialscansystem.Service.RequisitionService;
import com.industrialscansystem.respository.*;
import com.industrialscansystem.util.DeleteFileUtil;
import com.industrialscansystem.util.ImageTools;
import com.industrialscansystem.util.ImageTransUtil;
import com.industrialscansystem.util.SaveUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.industrialscansystem.util.EnvironmentPath;


import javax.imageio.ImageIO;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.Driver;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;




@RestController
public class RequisitionRestContorller {

    @Autowired
    private RequiredTypes requiredTypes;

    @Autowired
    private RequisitionRespository requisitionRespository;

    @Autowired
    private PictureRespository pictureRespository;

    @Autowired
    private RequisitionMapper requisitionMapper;

    @Autowired
    private RetangleRespository retangleRespository;

    @Autowired
    RequisitionService requisitionService;

    @Autowired
    DamageStatisticVORespository damageStatisticVORespository;

    @Autowired
    ProductRespository productRespository;
//    @RequestMapping(value = "/uploadFileList/{requisition_id}")
//    public List<Requisition> getRequisitionByProductId(@RequestParam("requisition_id") Integer requisition_id,@RequestParam("product_id") Integer product_id){
//        List<Requisition> lr = requisitionRespository.selectRequisitionByproductId(requisition_id,product_id);
//        return lr;
//    }



    @RequestMapping(value = "/uploadFileList/{requisition_id}")
    public void uploadFileList(@PathParam("uploadFile") MultipartFile[] uploadFile,
                               @PathVariable("requisition_id") int requisition_id
                               ) throws IllegalStateException, IOException {

        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        System.out.println("tomcatPath:"+tomcatPath);//设置的全局变量，省的每次都改，嘻嘻

        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
        SaveUploadFile saveUploadFile = new SaveUploadFile();
        String destination = tomcatPath + requisition.getRequisition_number();
        //String destination = "A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\" + requisition.getRequisition_number();
        for (MultipartFile multipartFile:uploadFile){
            saveUploadFile.saveFile(multipartFile,destination);

            //////////////////////////////
            String filepath = multipartFile.getOriginalFilename();
            File temp = new File(filepath);
            System.out.println("temp"+temp);

            // System.out.println("sourceImg-Height"+sourceImg1.getHeight());

            System.out.println("=============================");
            System.out.println("uploadFile"+uploadFile);
            String tempTifName = multipartFile.getOriginalFilename();
            //可以去掉后缀名
            System.out.println("tempTifName"+tempTifName);
            String tempTifFileName = tempTifName.trim();
            System.out.println("tempTifFileName"+tempTifFileName);
            String tifFileName = tempTifFileName.substring(tempTifFileName.lastIndexOf("\\")+1);
            System.out.println("tifFileName"+tifFileName);
            String tifName = tifFileName.substring(0,tifFileName.lastIndexOf("."));
            System.out.println("tifName"+tifName);
            Date date = new Date(System.currentTimeMillis());
            String dir = requisition.getRequisition_number()+"//"+tifFileName;
            System.out.println("dir"+dir);
            String houzui = multipartFile.getContentType();
            System.out.println("houzui"+houzui);
            Picture ttemppicture = pictureRespository.getPictureByDir(dir);
            if(ttemppicture!=null) {
                pictureRespository.deletePictureByPicture_id(ttemppicture.getPicture_id());
                retangleRespository.deleteAIRetangleListByPictureId(ttemppicture.getPicture_id());
            }

            String filePath=tomcatPath+dir;
            //String filePath="A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+dir;
            //     String tempPath = "D:\\tomcat\\apache-tomcat-8.5.43-windows-x64\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\AJ0006AJ\\VIDARImage18.tif";


            if(houzui.equals("image/tiff"))
            {

                try//GDAL提速
                {
                    String fileName_tif = filePath;
                    gdal.AllRegister();
                    System.out.println("fileName_tif"+fileName_tif);
                    Dataset hDataset = gdal.Open(fileName_tif, gdalconstConstants.GA_ReadOnly);
                    if (hDataset == null)
                    {
                        System.err.println("GDALOpen failed - " + gdal.GetLastErrorNo());
                        System.err.println(gdal.GetLastErrorMsg());
                        System.exit(1);
                    }
                    Driver hDriver = hDataset.GetDriver();
                    System.out.println("Driver: " + hDriver.getShortName() + "/" + hDriver.getLongName());
                    int iXSize = hDataset.getRasterXSize();
                    int iYSize = hDataset.getRasterYSize();
                    System.out.println("Size is " + iXSize + ", " + iYSize);

                    Picture pictureP = new Picture();
                    pictureP.setPicture_requisition_id(requisition_id);
                    pictureP.setPicture_dir(dir);
                    pictureP.setPicture_number(tifName);
                    pictureP.setPicture_entrytime(date);
                    pictureP.setPicture_height(iYSize);
                    pictureP.setPicture_width(iXSize);
                    pictureRespository.save(pictureP);
                    System.out.println("提速成功！");
                }
                catch (Exception e)//慢方法，以防jdk出问题
                {
                    String transPath;
                try
                {
                    transPath = ImageTransUtil.convertImage(filePath);
                    System.out.println("transPath"+transPath);
                    File temppicture = new File(transPath);
                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(temppicture));

                    Picture picture = new Picture();
                    picture.setPicture_requisition_id(requisition_id);
                    picture.setPicture_dir(dir);
                    picture.setPicture_number(tifName);
                    picture.setPicture_entrytime(date);
                    picture.setPicture_height(sourceImg.getHeight());
                    System.out.println("height:"+sourceImg.getHeight());
                    picture.setPicture_width(sourceImg.getWidth());
                    System.out.println("weight:"+sourceImg.getWidth());
                    pictureRespository.save(picture);
                    System.gc();
                    temppicture.delete();
                    File deleteFile = new File(transPath);
                    deleteFile.delete();
                    System.out.println("GDAL失败，转格式后上传。。。");
                }
                catch (Exception e1)
                {
                    System.out.println("转格式异常");
                }
                }


            }
            else//非TIFF格式图片
            {
                BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath));
                Picture pictureP = new Picture();
                pictureP.setPicture_requisition_id(requisition_id);
                pictureP.setPicture_dir(dir);
                pictureP.setPicture_number(tifName);
                pictureP.setPicture_entrytime(date);
                pictureP.setPicture_height(sourceImg.getHeight());
                pictureP.setPicture_width(sourceImg.getWidth());
                pictureRespository.save(pictureP);
            }



            //亚东学长老方法，先转换为jpg读取宽和高，速度巨慢
//            if(houzui.equals("image/tiff"))
//            {
//                String transPath;
//                try {
//                    transPath = ImageTransUtil.convertImage(filePath);
//                    System.out.println("transPath"+transPath);
//                    File temppicture = new File(transPath);
//                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(temppicture));
//
//                    Picture picture = new Picture();
//                    picture.setPicture_requisition_id(requisition_id);
//                    picture.setPicture_dir(dir);
//                    picture.setPicture_number(tifName);
//                    picture.setPicture_entrytime(date);
//                    picture.setPicture_height(sourceImg.getHeight());
//                    System.out.println("height:"+sourceImg.getHeight());
//                    picture.setPicture_width(sourceImg.getWidth());
//                    System.out.println("weight:"+sourceImg.getWidth());
//                    pictureRespository.save(picture);
//                    System.gc();
////                    temppicture.delete();
//                    File deleteFile = new File(transPath);
//                    deleteFile.delete();
//
//                } catch (Exception e) {
//                    System.out.println("转格式异常");
//                }
//            }else {
//                BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath));
//                Picture pictureP = new Picture();
//                pictureP.setPicture_requisition_id(requisition_id);
//                pictureP.setPicture_dir(dir);
//                pictureP.setPicture_number(tifName);
//                pictureP.setPicture_entrytime(date);
//                pictureP.setPicture_height(sourceImg.getHeight());
//                pictureP.setPicture_width(sourceImg.getWidth());
//                pictureRespository.save(pictureP);
//
//            }



        }

    }

    @RequestMapping(value = "/getRequisitionDamageTypeStatistic")
    public List<DamageStatisticVO> getRequisitionDamageTypeStatistic(@RequestParam("requisition_id") Integer requisition_id){

        List<DamageStatisticVO> damageStatisticVOList = damageStatisticVORespository.getDamageTypeStatisticByRequisitionId(requisition_id);
        Integer total = 0;
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            total += damageStatisticVO.getCount();
        }
        for (DamageStatisticVO damageStatisticVO : damageStatisticVOList) {
            damageStatisticVO.setFrequency(Double.valueOf(damageStatisticVO.getCount().toString()) / total);
        }
        return damageStatisticVOList;
    }

    @RequestMapping(value = "/deleteRequisitionById/{requisition_id}")
    public List<Requisition>  deleteRequisitionById(@PathVariable("requisition_id") int requisition_id){
        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
        requisitionRespository.deleteRequisitionByRequisitionId(requisition_id);
        List<Picture> pictures = pictureRespository.getByPictureRequisitionId(requisition_id);
        for (Picture picture : pictures){
            retangleRespository.deleteRetangleListByPictureId(picture.getPicture_id());
        }
        pictureRespository.deletePictureByRequisitionId(requisition_id);
        DeleteFileUtil deleteFileUtil = new DeleteFileUtil();

        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        deleteFileUtil.deleteDirectory(tomcatPath+requisition.getRequisition_number());
        //deleteFileUtil.deleteDirectory("A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+requisition.getRequisition_number());
                  //没用//        DeleteFileUtil.deleteDir("D:\\tomcat\\apache-tomcat-8.5.43-windows-x64\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+requisition.getRequisition_number());

        return requisitionRespository.findAll();
    }


    @RequestMapping(value = "/getRequisitionFileList")
    public List<com.industrialscansystem.MybatisBean.Requisition>  getRequisitionFileList(){
        List<com.industrialscansystem.MybatisBean.Requisition> requisitions = requisitionMapper.getRequisitionListPictures();
        return requisitionMapper.getRequisitionListPictures();
    }

    @RequestMapping(value = "/getRequisitionListByDistinctNumber")
    public List<Requisition> getRequisitionListByDistinctNumber(){
        return requisitionMapper.getRequisitionListByDistinctNumber();
    }

    @RequestMapping(value = "/getRequisitionPictureNumByNumber/{requisition_number}")
    public int getRequisitionPictureNumByNumber(@PathVariable("requisition_number") String requisition_number){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListByNumber(requisition_number);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }

    @RequestMapping(value = "/getRequisitionPictureNumBySamplenumber/{requisition_samplenumber}")
    public int getRequisitionPictureNumBySamplenumber(@PathVariable("requisition_samplenumber") String requisition_samplenumber){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListBySamplenumber(requisition_samplenumber);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }

    @RequestMapping(value = "/getRequisitionPictureNumByStructurename/{requisition_structurename}")
    public int getRequisitionPictureNumByStructurename(@PathVariable("requisition_structurename") String requisition_structurename){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListByStructurename(requisition_structurename);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }

    @RequestMapping(value = "/getRequisitionPictureNumByConstructunit/{requisition_constructunit}")
    public int getRequisitionPictureNumByConstructunit(@PathVariable("requisition_constructunit") String requisition_constructunit){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListByConstructunit(requisition_constructunit);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }

    @RequestMapping(value = "/getRequisitionPictureNumByWeldingmethod/{requisition_weldingmethod}")
    public int getRequisitionPictureNumByWeldingmethod(@PathVariable("requisition_weldingmethod") String requisition_weldingmethod){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListByWeldingmethod(requisition_weldingmethod);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }

    @RequestMapping(value = "/getRequisitionPictureNumBySteelnumber/{requisition_steelnumber}")
    public int getRequisitionPictureNumBySteelnumber(@PathVariable("requisition_steelnumber") String requisition_steelnumber){
        List<Requisition> requisitions = requisitionRespository.getRequisitionListBySteelnumber(requisition_steelnumber);
        return requisitionService.sumPictureNumByRequisitionId(requisitions);
    }


    @RequestMapping(value = "/getRequisitionListByDistinctSamplenumber")
    public List<Requisition> getRequisitionListByDistinctSamplenumber(){
        return requisitionMapper.getRequisitionListByDistinctSamplenumber();
    }

    @RequestMapping(value = "/getRequisitionListByDistinctConstructunit")
    public List<Requisition> getRequisitionListByDistinctConstructunit(){
        return requisitionMapper.getRequisitionListByDistinctConstructunit();
    }

    @RequestMapping(value = "/getRequisitionListByDistinctWeldingmethod")
    public List<Requisition> getRequisitionListByDistinctWeldingmethod(){
        return requisitionMapper.getRequisitionListByDistinctWeldingmethod();
    }

    @RequestMapping(value = "/getRequisitionListByDistinctSteelnumber")
    public List<Requisition> getRequisitionListByDistinctSteelnumber(){
        return requisitionMapper.getRequisitionListByDistinctSteelnumber();
    }

    @RequestMapping(value = "/getRequisitionListByDistinctStructurename")
    public List<Requisition> getRequisitionListByDistinctStructurename(){
        return requisitionMapper.getRequisitionListByDistinctStructurename();
    }


    @RequestMapping(value = "/getRequisitionFile")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFile(){
        return requisitionMapper.getRequisitionFile();
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByTestingRate")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTestingRate(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctTestingRate());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByTestingRate();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_testing_rate()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByBevelForm")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByBevelForm(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctBevelForm());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByBevelForm();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_bevel_form()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByFilmtype")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFilmtype(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctFilmtype());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByFilmtype();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_filmtype()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByTransillumination")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTransillumination(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctTransillumination());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByTransillumination();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_transillumination()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByFocusSize")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFocusSize(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctFocusSize());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByFocusSize();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_focus_size()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctBySensitizationMethod")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctBySensitizationMethod(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctSensitizationMethod());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctBySensitizationMethod();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_sensitization_method()))), ArrayList::new));
        return tmp;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByFilmProcessingMethod")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByFilmProcessingMethod(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctFilmProcessingMethod());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByFilmProcessingMethod();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_film_processing_method()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByQualificationLevel")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByQualificationLevel(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctQualificationLevel());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByQualificationLevel();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_qualificationlevel()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByTestingInstrument")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByTestingInstrument(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctTestingInstrument());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByTestingInstrument();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_testing_instrument()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByWeldingMethod")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByWeldingMethod(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctWeldingMethod());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByWeldingMethod();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_weldingmethod()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByIntensifyScreenFront")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenFront(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctIntensifyScreenFront());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByIntensifyScreenFront();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_intensifyscreen_front()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByIntensifyScreenMiddle")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenMiddle(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctIntensifyScreenMiddle());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByIntensifyScreenMiddle();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_intensifyscreen_middle()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionFileDistinctByIntensifyScreenBehind")
    public List<com.industrialscansystem.Bean.Requisition> getRequisitionFileDistinctByIntensifyScreenBehind(){
        List<com.industrialscansystem.Bean.Requisition> tmp = new ArrayList<>(requiredTypes.getDistinctIntensifyScreenBehind());
        List<com.industrialscansystem.Bean.Requisition> list = requisitionMapper.getRequisitionFileDistinctByIntensifyScreenBehind();
        tmp.addAll(list);
        List<com.industrialscansystem.Bean.Requisition> res = tmp.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        f -> f.getRequisition_intensifyscreen_behind()))), ArrayList::new));
        return res;
    }

    @RequestMapping(value = "/getRequisitionList")
    public Object getRequisitionList( ){
        List<Requisition> requisitionList = requisitionRespository.findAll();
        List<RequisitionVO> requisitionVOList = new ArrayList<>();
        for (Requisition requisition: requisitionList) {
            RequisitionVO tmp = requisition.transfer2RequisitionVO();
            String productName = productRespository.getProductByProductId(requisition.getRequisition_product_id()).getProduct_name();
            tmp.setRequisition_product_name(productName);
            requisitionVOList.add(tmp);
        }
        return requisitionVOList;
    }

    //由于统计的前几个字段都等于申请单内的影像图个数，统一建方法直接统计
    @RequestMapping(value = "/countRequisitionPictureNumber/{requisition_id}")
    public int countRequisitionPictureNumber(@PathVariable("requisition_id") int requisition_id){
        return pictureRespository.countRequisitionPictureNumber(requisition_id);
    }

    @RequestMapping(value = "/addRequisition")
    public String addRequisition(@RequestBody RequisitionVO requisition){
        Requisition tempRequisition = requisitionRespository.getRequisitionnumber(requisition.getRequisition_number());
        if(tempRequisition == null) {
            Requisition req = requisition.transfer2Requisition();
            req.setRequisition_firstexam("未审批");
            req.setRequisition_secondexam("未审批");
            req.setRequisition_thirdexam("未审批");
            req.setRequisition_state(2);
            Date date = new Date(System.currentTimeMillis());
            req.setRequisition_entrytime(date);
            Product tempProduct = productRespository.getProductByProductName(requisition.getRequisition_product_name());
            if (tempProduct == null) {
                Product product = new Product();
                product.setProduct_name(requisition.getRequisition_product_name());
                productRespository.save(product);
                tempProduct = productRespository.getProductByProductName(requisition.getRequisition_product_name());
            }
            Integer productId = tempProduct.getProduct_id();
            System.out.println(productId + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            req.setRequisition_product_id(productId);
//            requisition.setRequisition_firstexam("未审批");
//            requisition.setRequisition_secondexam("未审批");
//            requisition.setRequisition_thirdexam("未审批");
//            requisition.setRequisition_state(2);
//            Date date = new Date(System.currentTimeMillis());
//            requisition.setRequisition_entrytime(date);

            requisitionRespository.save(req);
            return "申请单已添加成功！请前往导入影像图";
        }
        return "已有同编号申请单，请勿重复添加";
    }



    @RequestMapping(value = "/updateRequisition")
    public List<Requisition> updateRequisition(@RequestBody Requisition requisition){
        System.out.println("updateRequisition..");
        requisitionRespository.save(requisition);
        return requisitionRespository.findAll();
    }

    @RequestMapping(value = "/getRequisition")
    public RequisitionVO getRequisition(@RequestParam("requisition_id") Integer requisition_id){

        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
        RequisitionVO tmp = requisition.transfer2RequisitionVO();
        String productName = productRespository.getProductByProductId(requisition.getRequisition_product_id()).getProduct_name();
        tmp.setRequisition_product_name(productName);

        return tmp;
    }

    @RequestMapping(value = "/completeApproval")
    public List<Requisition> completeApproval(@RequestParam("requisition_id") Integer requisition_id,
                                        @RequestParam("requisition_state") Integer requisition_state,
                                         @RequestParam("member_id") Integer member_id     ){
        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
        System.out.println("申请通过-------------------------");
        System.out.println(requisition_state);
        if(requisition_state == 2){
            requisition.setRequisition_firstexam_member(member_id);
        }
        if(requisition_state == 3){
            requisition.setRequisition_secondexam_member(member_id);
        }
        if(requisition_state == 4){
            requisition.setRequisition_thirdexam_member(member_id);
        }

        switch (requisition_state - 1){
            case 0:
                requisition.setRequisition_firstexam("未审批"); break;
            case 1:
                requisition.setRequisition_firstexam("审批完成"); break;
            case 2:
                requisition.setRequisition_secondexam("审批完成"); break;
            case 3:
                requisition.setRequisition_thirdexam("审批完成"); break;
            default:
                System.out.println("其他情况默认即可"); break;
        }

        if(requisition_state < 5) {
            requisition.setRequisition_state(requisition_state + 1);
            requisitionRespository.save(requisition);
        }

        return requisitionRespository.findAll();
    }

    @RequestMapping(value = "/rejectRequest")
    public List<Requisition> rejectRequest(@RequestParam("requisition_id") Integer requisition_id,
                                        @RequestParam("requisition_state") Integer requisition_state){
        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
        System.out.println("申请驳回-------------------------");
        System.out.println(requisition_state);
        switch (requisition_state - 2){
            case 0:
                requisition.setRequisition_firstexam("已驳回");
                break;
            case 1:
                requisition.setRequisition_secondexam("已驳回");
                break;
            case 2:
                requisition.setRequisition_thirdexam("已驳回");
                break;
//            case 1:
//                requisition.setRequisition_firstexam("已驳回");
//                break;
//            case 2:
//                requisition.setRequisition_secondexam("已驳回");
//                break;
//            case 3:
//                requisition.setRequisition_thirdexam("已驳回");
//                break;
            default:
                System.out.println("其他情况默认即可");
                break;
        }

        if(requisition_state > 2) {
            requisition.setRequisition_state(requisition_state - 1);
            requisitionRespository.save(requisition);
        }

        return requisitionRespository.findAll();
    }

}





////////////////////////////////////////////////
//用于临时保存，尝试把gdal放到try catch里面
//package com.industrialscansystem.Controller;
//
//        import com.alibaba.fastjson.JSONObject;
//        import com.industrialscansystem.Bean.Picture;
//        import com.industrialscansystem.Bean.Requisition;
//        import com.industrialscansystem.Mapper.RequisitionMapper;
//        import com.industrialscansystem.Service.RequisitionService;
//        import com.industrialscansystem.respository.PictureRespository;
//        import com.industrialscansystem.respository.RequisitionRespository;
//        import com.industrialscansystem.respository.RetangleRespository;
//        import com.industrialscansystem.util.DeleteFileUtil;
//        import com.industrialscansystem.util.ImageTools;
//        import com.industrialscansystem.util.ImageTransUtil;
//        import com.industrialscansystem.util.SaveUploadFile;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.web.bind.annotation.*;
//        import org.springframework.web.multipart.MultipartFile;
//        import com.industrialscansystem.util.EnvironmentPath;
//
//
//        import javax.imageio.ImageIO;
//        import javax.websocket.server.PathParam;
//        import java.awt.image.BufferedImage;
//        import java.io.File;
//        import java.io.FileInputStream;
//        import java.io.IOException;
//        import java.sql.Date;
//        import java.text.SimpleDateFormat;
//        import java.util.List;
//
//        import org.gdal.gdal.Band;
//        import org.gdal.gdal.Dataset;
//        import org.gdal.gdal.Driver;
//        import org.gdal.gdal.gdal;
//        import org.gdal.gdalconst.gdalconstConstants;
//
//
//
//
//@RestController
//public class RequisitionRestContorller {
//
//    @Autowired
//    private RequisitionRespository requisitionRespository;
//
//    @Autowired
//    private PictureRespository pictureRespository;
//
//    @Autowired
//    private RequisitionMapper requisitionMapper;
//
//    @Autowired
//    private RetangleRespository retangleRespository;
//
//    @Autowired
//    RequisitionService requisitionService;
//
//
//
//    @RequestMapping(value = "/uploadFileList/{requisition_id}")
//    public void uploadFileList(@PathParam("uploadFile") MultipartFile[] uploadFile,
//                               @PathVariable("requisition_id") int requisition_id
//    ) throws IllegalStateException, IOException {
//
//        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
//        System.out.println("tomcatPath:"+tomcatPath);//设置的全局变量，省的每次都改，嘻嘻
//
//        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
//        SaveUploadFile saveUploadFile = new SaveUploadFile();
//        String destination = tomcatPath + requisition.getRequisition_number();
//        //String destination = "A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\" + requisition.getRequisition_number();
//        for (MultipartFile multipartFile:uploadFile){
//            saveUploadFile.saveFile(multipartFile,destination);
//
//            //////////////////////////////
//            String filepath = multipartFile.getOriginalFilename();
//            File temp = new File(filepath);
//            System.out.println("temp"+temp);
//
//            // System.out.println("sourceImg-Height"+sourceImg1.getHeight());
//
//            System.out.println("=============================");
//            System.out.println("uploadFile"+uploadFile);
//            String tempTifName = multipartFile.getOriginalFilename();
//            //可以去掉后缀名
//            System.out.println("tempTifName"+tempTifName);
//            String tempTifFileName = tempTifName.trim();
//            System.out.println("tempTifFileName"+tempTifFileName);
//            String tifFileName = tempTifFileName.substring(tempTifFileName.lastIndexOf("\\")+1);
//            System.out.println("tifFileName"+tifFileName);
//            String tifName = tifFileName.substring(0,tifFileName.lastIndexOf("."));
//            System.out.println("tifName"+tifName);
//            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
//            String dir = requisition.getRequisition_number()+"//"+tifFileName;
//            System.out.println("dir"+dir);
//            String houzui = multipartFile.getContentType();
//            System.out.println("houzui"+houzui);
//            Picture ttemppicture = pictureRespository.getPictureByDir(dir);
//            if(ttemppicture!=null) {
//                pictureRespository.deletePictureByPicture_id(ttemppicture.getPicture_id());
//                retangleRespository.deleteAIRetangleListByPictureId(ttemppicture.getPicture_id());
//            }
//
//            String filePath=tomcatPath+dir;
//            //String filePath="A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+dir;
//            //     String tempPath = "D:\\tomcat\\apache-tomcat-8.5.43-windows-x64\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\AJ0006AJ\\VIDARImage18.tif";
//
//            String fileName_tif = filePath;
//            gdal.AllRegister();
//            System.out.println("fileName_tif"+fileName_tif);
//            Dataset hDataset = gdal.Open(fileName_tif, gdalconstConstants.GA_ReadOnly);
//            if (hDataset == null)
//            {
//                System.err.println("GDALOpen failed - " + gdal.GetLastErrorNo());
//                System.err.println(gdal.GetLastErrorMsg());
//                System.exit(1);
//            }
//            Driver hDriver = hDataset.GetDriver();
//            System.out.println("Driver: " + hDriver.getShortName() + "/" + hDriver.getLongName());
//            int iXSize = hDataset.getRasterXSize();
//            int iYSize = hDataset.getRasterYSize();
//            System.out.println("Size is " + iXSize + ", " + iYSize);
//
//            BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath));
//            Picture pictureP = new Picture();
//            pictureP.setPicture_requisition_id(requisition_id);
//            pictureP.setPicture_dir(dir);
//            pictureP.setPicture_number(tifName);
//            pictureP.setPicture_entrytime(date);
//            pictureP.setPicture_height(iYSize);
//            pictureP.setPicture_width(iXSize);
//            pictureRespository.save(pictureP);
//
//
//            //亚东学长老方法，先转换为jpg读取宽和高，速度巨慢
////            if(houzui.equals("image/tiff"))
////            {
////                String transPath;
////                try {
////                    transPath = ImageTransUtil.convertImage(filePath);
////                    System.out.println("transPath"+transPath);
////                    File temppicture = new File(transPath);
////                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(temppicture));
////
////                    Picture picture = new Picture();
////                    picture.setPicture_requisition_id(requisition_id);
////                    picture.setPicture_dir(dir);
////                    picture.setPicture_number(tifName);
////                    picture.setPicture_entrytime(date);
////                    picture.setPicture_height(sourceImg.getHeight());
////                    System.out.println("height:"+sourceImg.getHeight());
////                    picture.setPicture_width(sourceImg.getWidth());
////                    System.out.println("weight:"+sourceImg.getWidth());
////                    pictureRespository.save(picture);
////                    System.gc();
//////                    temppicture.delete();
////                    File deleteFile = new File(transPath);
////                    deleteFile.delete();
////
////                } catch (Exception e) {
////                    System.out.println("转格式异常");
////                }
////            }else {
////                BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath));
////                Picture pictureP = new Picture();
////                pictureP.setPicture_requisition_id(requisition_id);
////                pictureP.setPicture_dir(dir);
////                pictureP.setPicture_number(tifName);
////                pictureP.setPicture_entrytime(date);
////                pictureP.setPicture_height(sourceImg.getHeight());
////                pictureP.setPicture_width(sourceImg.getWidth());
////                pictureRespository.save(pictureP);
////
////            }
//
//
//
//        }
//
//    }
//
//    @RequestMapping(value = "/deleteRequisitionById/{requisition_id}")
//    public List<Requisition>  deleteRequisitionById(@PathVariable("requisition_id") int requisition_id){
//        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
//        requisitionRespository.deleteRequisitionByRequisitionId(requisition_id);
//        List<Picture> pictures = pictureRespository.getByPictureRequisitionId(requisition_id);
//        for (Picture picture : pictures){
//            retangleRespository.deleteRetangleListByPictureId(picture.getPicture_id());
//        }
//        pictureRespository.deletePictureByRequisitionId(requisition_id);
//        DeleteFileUtil deleteFileUtil = new DeleteFileUtil();
//
//        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
//        deleteFileUtil.deleteDirectory(tomcatPath+requisition.getRequisition_number());
//        //deleteFileUtil.deleteDirectory("A:\\documents\\labwork\\weldproject\\code\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+requisition.getRequisition_number());
//        //没用//        DeleteFileUtil.deleteDir("D:\\tomcat\\apache-tomcat-8.5.43-windows-x64\\apache-tomcat-8.5.43\\webapps\\XrayImageDB\\"+requisition.getRequisition_number());
//
//        return requisitionRespository.findAll();
//    }
//
//
//    @RequestMapping(value = "/getRequisitionFileList")
//    public List<com.industrialscansystem.MybatisBean.Requisition>  getRequisitionFileList(){
//        List<com.industrialscansystem.MybatisBean.Requisition> requisitions = requisitionMapper.getRequisitionListPictures();
//        return requisitionMapper.getRequisitionListPictures();
//    }
//
//    @RequestMapping(value = "/getRequisitionListByDistinctNumber")
//    public List<Requisition> getRequisitionListByDistinctNumber(){
//        return requisitionMapper.getRequisitionListByDistinctNumber();
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumByNumber/{requisition_number}")
//    public int getRequisitionPictureNumByNumber(@PathVariable("requisition_number") String requisition_number){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListByNumber(requisition_number);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumBySamplenumber/{requisition_samplenumber}")
//    public int getRequisitionPictureNumBySamplenumber(@PathVariable("requisition_samplenumber") String requisition_samplenumber){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListBySamplenumber(requisition_samplenumber);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumByStructurename/{requisition_structurename}")
//    public int getRequisitionPictureNumByStructurename(@PathVariable("requisition_structurename") String requisition_structurename){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListByStructurename(requisition_structurename);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumByConstructunit/{requisition_constructunit}")
//    public int getRequisitionPictureNumByConstructunit(@PathVariable("requisition_constructunit") String requisition_constructunit){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListByConstructunit(requisition_constructunit);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumByWeldingmethod/{requisition_weldingmethod}")
//    public int getRequisitionPictureNumByWeldingmethod(@PathVariable("requisition_weldingmethod") String requisition_weldingmethod){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListByWeldingmethod(requisition_weldingmethod);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//    @RequestMapping(value = "/getRequisitionPictureNumBySteelnumber/{requisition_steelnumber}")
//    public int getRequisitionPictureNumBySteelnumber(@PathVariable("requisition_steelnumber") String requisition_steelnumber){
//        List<Requisition> requisitions = requisitionRespository.getRequisitionListBySteelnumber(requisition_steelnumber);
//        return requisitionService.sumPictureNumByRequisitionId(requisitions);
//    }
//
//
//    @RequestMapping(value = "/getRequisitionListByDistinctSamplenumber")
//    public List<Requisition> getRequisitionListByDistinctSamplenumber(){
//        return requisitionMapper.getRequisitionListByDistinctSamplenumber();
//    }
//
//    @RequestMapping(value = "/getRequisitionListByDistinctConstructunit")
//    public List<Requisition> getRequisitionListByDistinctConstructunit(){
//        return requisitionMapper.getRequisitionListByDistinctConstructunit();
//    }
//
//    @RequestMapping(value = "/getRequisitionListByDistinctWeldingmethod")
//    public List<Requisition> getRequisitionListByDistinctWeldingmethod(){
//        return requisitionMapper.getRequisitionListByDistinctWeldingmethod();
//    }
//
//    @RequestMapping(value = "/getRequisitionListByDistinctSteelnumber")
//    public List<Requisition> getRequisitionListByDistinctSteelnumber(){
//        return requisitionMapper.getRequisitionListByDistinctSteelnumber();
//    }
//
//    @RequestMapping(value = "/getRequisitionListByDistinctStructurename")
//    public List<Requisition> getRequisitionListByDistinctStructurename(){
//        return requisitionMapper.getRequisitionListByDistinctStructurename();
//    }
//
//
//    @RequestMapping(value = "/getRequisitionFile")
//    public List<com.industrialscansystem.MybatisBean.Requisition> getRequisitionFile(){
//        return requisitionMapper.getRequisitionFile();
//    }
//
//    @RequestMapping(value = "/getRequisitionList")
//    public Object getRequisitionList( ){
//        List<Requisition> requisitionList = requisitionRespository.findAll();
//        return requisitionList;
//    }
//
//    //由于统计的前几个字段都等于申请单内的影像图个数，统一建方法直接统计
//    @RequestMapping(value = "/countRequisitionPictureNumber/{requisition_id}")
//    public int countRequisitionPictureNumber(@PathVariable("requisition_id") int requisition_id){
//        return pictureRespository.countRequisitionPictureNumber(requisition_id);
//    }
//
//    @RequestMapping(value = "/addRequisition")
//    public String addRequisition(@RequestBody Requisition requisition){
//        Requisition tempRequisition = requisitionRespository.getRequisitionnumber(requisition.getRequisition_number());
//        if(tempRequisition==null) {
//            requisition.setRequisition_firstexam("未审批");
//            requisition.setRequisition_secondexam("未审批");
//            requisition.setRequisition_thirdexam("未审批");
//            requisition.setRequisition_state(2);
//            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
//            requisition.setRequisition_entrytime(date);
//            requisitionRespository.save(requisition);
//            return "申请单已添加成功！请前往导入影像图";
//        }
//        return "已有同编号申请单，请勿重复添加";
//    }
//
//
//
//    @RequestMapping(value = "/updateRequisition")
//    public List<Requisition> updateRequisition(@RequestBody Requisition requisition){
//        System.out.println("updateRequisition..");
//        requisitionRespository.save(requisition);
//        return requisitionRespository.findAll();
//    }
//
//    @RequestMapping(value = "/getRequisition")
//    public Requisition getRequisition(@RequestParam("requisition_id") Integer requisition_id){
//
//        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
//
//        return requisition;
//    }
//
//    @RequestMapping(value = "/completeApproval")
//    public List<Requisition> completeApproval(@RequestParam("requisition_id") Integer requisition_id,
//                                              @RequestParam("requisition_state") Integer requisition_state,
//                                              @RequestParam("member_id") Integer member_id     ){
//        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
//        if(requisition_state == 2){
//            requisition.setRequisition_firstexam_member(member_id);
//        }
//        if(requisition_state == 3){
//            requisition.setRequisition_secondexam_member(member_id);
//        }
//        if(requisition_state == 4){
//            requisition.setRequisition_thirdexam_member(member_id);
//        }
//
//        switch (requisition_state - 1){
//            case 0:
//                requisition.setRequisition_firstexam("未审批");
//                break;
//            case 1:
//                requisition.setRequisition_firstexam("审批完成");
//                break;
//            case 2:
//                requisition.setRequisition_secondexam("审批完成");
//                break;
//            case 3:
//                requisition.setRequisition_thirdexam("审批完成");
//                break;
//            default:
//                System.out.println("其他情况默认即可");
//                break;
//        }
//
//        if(requisition_state < 5) {
//            requisition.setRequisition_state(requisition_state + 1);
//            requisitionRespository.save(requisition);
//        }
//
//        return requisitionRespository.findAll();
//    }
//
//    @RequestMapping(value = "/rejectRequest")
//    public List<Requisition> rejectRequest(@RequestParam("requisition_id") Integer requisition_id,
//                                           @RequestParam("requisition_state") Integer requisition_state){
//        Requisition requisition = requisitionRespository.getRequisitionById(requisition_id);
//        switch (requisition_state - 2){
//            case 1:
//                requisition.setRequisition_firstexam("已驳回");
//                break;
//            case 2:
//                requisition.setRequisition_secondexam("已驳回");
//                break;
//            case 3:
//                requisition.setRequisition_thirdexam("已驳回");
//                break;
//            default:
//                System.out.println("其他情况默认即可");
//                break;
//        }
//
//        if(requisition_state > 2) {
//            requisition.setRequisition_state(requisition_state - 1);
//            requisitionRespository.save(requisition);
//        }
//
//        return requisitionRespository.findAll();
//    }
//
//}
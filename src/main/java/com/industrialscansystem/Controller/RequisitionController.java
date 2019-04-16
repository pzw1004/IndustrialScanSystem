package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.*;
import com.industrialscansystem.Service.LogSaveService;
import com.industrialscansystem.respository.*;
import com.industrialscansystem.util.LogUtils;
import com.industrialscansystem.util.SaveUploadFile;
import com.industrialscansystem.util.TifImageTransUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequisitionController {

    //配置存储log日志服务
    @Autowired
    private LogSaveService logSaveService; //配置存储

    @Autowired
    private PictureRespository pictureRespository;

    @Autowired
    private RequisitionRespository requisitionRespository;

    @Autowired
    private RetangleRespository retangleRespository;

    @Autowired
    private MemberRespository memberRespository;

    @Autowired
    private AuthorityRespository authorityRespository;

    @Autowired
    private DamageTypeRespository damageTypeRespository;

    @RequestMapping(value = "/requisition")   //将申请单List列表信息循环展示到前端
    //设置接收参数为非必填
    public String RequisitionControl(@RequestParam(name = "pageNum",required=false,defaultValue="1") Integer pageNum,
                                     @RequestParam(name = "key",required=false,defaultValue="") String key,
            Model model){

        if(pageNum < 1){ //当选择页数小于1时，默认选第一页
            pageNum = 1;
        }

        Integer num = 3;  //设置每页显示的数据条数
        Integer tiao = (pageNum-1)*num;  //设置数据库查询时开始的位置
        Integer totalNum = requisitionRespository.getRequisitionTotalPageNumByKey(key);//由关键字得到相关的所有记录条数

        int pageTotalNum = (int)Math.ceil((double)totalNum/(double)num) ;//获得总页数

        List<Picture> pictureList = pictureRespository.findAll();
        List<Requisition> requisitionList = requisitionRespository.findRequisitionListByKeyAndPageNumAndNum(key,tiao,num);
        List<Member> memberList = memberRespository.findAll();

        String tempUsername = SecurityContextHolder.getContext().getAuthentication().getName();//获取当前登录的用户信息
        Member memberRole = memberRespository.findMemberByMember_username(tempUsername);


        model.addAttribute("memberRole",memberRole);
        model.addAttribute("memberList",memberList);
        model.addAttribute("requisitionList",requisitionList);
        model.addAttribute("pictureList",pictureList);
        model.addAttribute("key",key);
        model.addAttribute("totalNum",totalNum);
        model.addAttribute("pageTotalNum",pageTotalNum);
        model.addAttribute("pageNum",pageNum);

        if(key==""){
            //根据网页端操作，在log日志中存入自定义的信息
            String operationLog = "查看了申请单信息";
            logSaveService.LogInfoSave(operationLog);

        }else {
            //根据网页端操作，在log日志中存入自定义的信息
            String operationLog = "使用关键字["+key+"]查看了申请单信息";
            logSaveService.LogInfoSave(operationLog);

        }

        Logger log = LogUtils.getBussinessLogger();
        return "requisition";
    }

//    @RequestMapping(value = "/requisitionRelevant") //根据申请单编号获取模糊查询的所有相关信息展示，此处代码务必与展示所有申请单信息代码保持一致
//    public String RequisitionControl(@RequestParam("requisition_number") String requisition_number,
//                                      Model model){
//        List<Picture> pictureList = pictureRespository.findAll();
//        List<Requisition> requisitionList = requisitionRespository.getRequisitionListByRequsitionNumber(requisition_number);
////
////                Requisition xx =    requisitionRespository.findOne(1);
////        List<Requisition> requisitionList  = new ArrayList<>();
////        requisitionList.add(xx);
//
//        List<Member> memberList = memberRespository.findAll();
//
//
//        String tempUsername = SecurityContextHolder.getContext().getAuthentication().getName();//获取当前登录的用户信息
//        Member memberRole = memberRespository.findMemberByMember_username(tempUsername);
//
//
//        model.addAttribute("memberRole",memberRole);
//        model.addAttribute("memberList",memberList);
//        model.addAttribute("requisitionList",requisitionList);
//        model.addAttribute("pictureList",pictureList);
//
//        //根据网页端操作，在log日志中存入自定义的信息
//        String operationLog = "使用关键字["+requisition_number+"]查询了相关联的申请单信息";
//        logSaveService.LogInfoSave(operationLog);
//
//
//
//        return "requisition";
//    }

    @RequestMapping(value = "/requisitionDetail") //根据申请单id获取相关信息显示页面
    public String RequisitionDetailControl(@RequestParam("requisition_id") Integer requisition_id,
                                           Model model){
        List<Picture> pictureList = pictureRespository.getByPictureRequisitionId(requisition_id);
        Requisition requisition = requisitionRespository.findOne(requisition_id);
        model.addAttribute("requisition",requisition);
        model.addAttribute("pictureList",pictureList);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了申请单编号为["+requisition.getRequisition_number()+"]的打印无损检测申请单页面";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "requisition_details";
    }

    @RequestMapping(value = "/requisitionReport")
    public String RequisitionReportControl(@RequestParam("requisition_id") Integer requisition_id,

            Model model){

        List<Picture> pictureList = pictureRespository.getByPictureRequisitionId(requisition_id);
        Requisition requisition = requisitionRespository.findOne(requisition_id);
        model.addAttribute("requisition",requisition);
        model.addAttribute("pictureList",pictureList);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了申请单编号为["+requisition.getRequisition_number()+"]的打印无损检测报告页面";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "requisition_report";
    }


//    @RequestMapping(value = "/requisitionImgtable")
//    public  String RequisitionImgtableControl(Model model){
//
//        return "requisition_imgtable";
//    }


    @RequestMapping(value = "/AIprocess")
    public String AIprocessControl(@RequestParam("id") Integer id,
            Model model){

        return "redirect:/requisitionImgtableShow?id="+id;
    }

    @RequestMapping(value = "/requisitionImgtableShow")               //打开具体某张影像图时action
    public String RequisitionImgtable_ImgtableShowControl(@RequestParam("id") Integer id,
                                                          Model model){

        Picture temppicture = pictureRespository.findOne(id);

        if(temppicture.getPicture_transpath() == null || temppicture.getPicture_transpath().isEmpty()) {  //先判断影像图是否存在转格式后存放图的路径

            //String imageFile = "G:\\影像图根目录\\AJ005AJ\\VIDARImage12.tif";
            String imageFile = temppicture.getPicture_dir();

            TifImageTransUtil cTif = new TifImageTransUtil();
            try {

                String path = cTif.generatePath();
                cTif.TifConver(imageFile, path);


                String imageFileJpg = imageFile.replace(".TIF", ".jpg");  //将路径后缀名.tif或者TIF的替换成后缀名为jpg格式的，给新string变量imageFileJpg存储
                imageFileJpg = imageFileJpg.replace(".tif", ".jpg");
                File file2 = new File(imageFileJpg);

                BufferedImage sourceImg = ImageIO.read(new FileInputStream(file2));

                int tempWidth = sourceImg.getWidth();
                int tempHeight = sourceImg.getHeight();


                pictureRespository.updatePictureByIdAndPictureWidthAndHeight(tempWidth, tempHeight, path, id);

            } catch (Exception e) {
                System.out.println("影像图转格式出现问题");
            }
        }



        List<Retangle> retangleList =  retangleRespository.selectRetangleListByPictureId(id);  //根据影像图id取出相关的所有框存入List
        model.addAttribute("retangleList",retangleList);


         Picture picture = pictureRespository.findOne(id);
         model.addAttribute("picture",picture);
        // model.addAttribute("authority",authority);
        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了影像图编号为["+picture.getPicture_number()+"]的详细信息审核页面";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "requisition_imgtable_show";
    }

    /**
     * 根据当前影像图id传入，取得上一页影像图id并重定向到影像图显示页面
     * @param picture_id
     * @return
     */
    @RequestMapping(value = "/upPagePicture")
    public String UpPagePicture(@RequestParam("picture_id") Integer picture_id){

        Picture picture = pictureRespository.findOne(picture_id);
        int requisition_id = picture.getPicture_requisition_id();
        Picture upPagePicture = pictureRespository.getUpPagePicture(picture_id,requisition_id);
        if(upPagePicture != null) {

            int upPagePictureId = upPagePicture.getPicture_id();
            return "redirect:/requisitionImgtableShow?id=" + upPagePictureId;

        }else {

            return "redirect:/requisitionImgtableShow?id=" + picture_id;

        }

    }

    /**
     * 根据当前影像图id传入，取得下一页影像图id并重定向到影像图显示页面
     * @param picture_id
     * @return
     */
    @RequestMapping(value = "/nextPagePicture")
    public String NextPagePicture(@RequestParam("picture_id") Integer picture_id){

        Picture picture = pictureRespository.findOne(picture_id);
        int requisition_id = picture.getPicture_requisition_id();
        Picture nextPagePicture = pictureRespository.getNextPagePicture(picture_id,requisition_id);
        if(nextPagePicture != null) {

            int nextPagePictureId = nextPagePicture.getPicture_id();
            return "redirect:/requisitionImgtableShow?id=" + nextPagePictureId;

        }else {

            return "redirect:/requisitionImgtableShow?id=" + picture_id;

        }
    }

    @RequestMapping(value = "/requisitionImgtableShowUpdate")///imgtableShow
    public String RequisitionImgtable_ImgtableShowUpdateControl(@RequestParam("id") Integer id,
                                                          @RequestParam("permission") Integer permission,
                                                          Model model){
        //获取传递来的影像图id号与权限

        String examresult = "√";           //根据传进来的更改显示的审批状态，传进来0不做任何修改直接显示页面
        String examresult2 = "×";
        if(permission == 1)pictureRespository.updatePictureByIdAndExamFirstresult(examresult,id);
        if(permission == 2)pictureRespository.updatePictureByIdAndExamSecondresult(examresult,id);
        if(permission == 3)pictureRespository.updatePictureByIdAndExamThirdresult(examresult,id);
        if(permission == 4)pictureRespository.updatePictureByIdAndExamFirstresult(examresult2,id);
        if(permission == 5)pictureRespository.updatePictureByIdAndExamSecondresult(examresult2,id);
        if(permission == 6)pictureRespository.updatePictureByIdAndExamThirdresult(examresult2,id);




        Picture picture = pictureRespository.findOne(id);
        model.addAttribute("picture",picture);
        List<DamageType> damageTypeList = damageTypeRespository.findAll();
        model.addAttribute("damageTypeList",damageTypeList);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "更改了影像图编号为["+picture.getPicture_number()+"]的审批状态";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "redirect:/requisition";
    }


    @RequestMapping(value ="/requisitionUpdateInfo",method = RequestMethod.POST)
    public String RequisitionUpdateInfoControl(@RequestParam("requisition_id") Integer requisition_id,
                                               @RequestParam("requisition_number") String requisition_number,
                                               @RequestParam("requisition_name") String requisition_name,
                                               @RequestParam("requisition_structurename") String requisition_structurename,
                                               @RequestParam("requisition_constructunit") String requisition_constructunit,
                                               @RequestParam("requisition_saveplace") String requisition_saveplace,
                                               @RequestParam("requisition_steelnumber") String requisition_steelnumber,
                                               @RequestParam("requisition_weldingmethod") String requisition_weldingmethod,
                                               @RequestParam("requisition_reworktimes") Integer requisition_reworktimes,
                                               @RequestParam("requisition_firstexam_member") Integer requisition_firstexam_member,
                                               @RequestParam("requisition_firstexam") String requisition_firstexam,
                                               @RequestParam("requisition_firstopinion") String requisition_firstopinion,
                                               @RequestParam("requisition_secondexam_member") Integer requisition_secondexam_member,
                                               @RequestParam("requisition_secondexam") String requisition_secondexam,
                                               @RequestParam("requisition_secondopinion") String requisition_secondopinion,
                                               @RequestParam("requisition_thirdexam_member") Integer requisition_thirdexam_member,
                                               @RequestParam("requisition_thirdexam") String requisition_thirdexam,
                                               @RequestParam("requisition_thirdopinion") String requisition_thirdopinion,
                                               @RequestParam("requisition_state") Integer requisition_state
                                               ){


        Requisition requisition =requisitionRespository.findOne(requisition_id);

        requisition.setRequisition_number(requisition_number);
        requisition.setRequisition_name(requisition_name);
        requisition.setRequisition_structurename(requisition_structurename);
        requisition.setRequisition_constructunit(requisition_constructunit);
        requisition.setRequisition_saveplace(requisition_saveplace);
        requisition.setRequisition_steelnumber(requisition_steelnumber);
        requisition.setRequisition_weldingmethod(requisition_weldingmethod);
        requisition.setRequisition_reworktimes(requisition_reworktimes);
        requisition.setRequisition_firstexam_member(requisition_firstexam_member);
        requisition.setRequisition_firstexam(requisition_firstexam);
        requisition.setRequisition_firstopinion(requisition_firstopinion);
        requisition.setRequisition_thirdexam_member(requisition_thirdexam_member);
        requisition.setRequisition_thirdexam(requisition_thirdexam);
        requisition.setRequisition_thirdopinion(requisition_thirdopinion);
        requisition.setRequisition_secondexam_member(requisition_secondexam_member);
        requisition.setRequisition_secondexam(requisition_secondexam);
        requisition.setRequisition_secondopinion(requisition_secondopinion);
        requisition.setRequisition_state(requisition_state);

        requisitionRespository.save(requisition);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "更新了申请单编号为["+requisition.getRequisition_number()+"]的详细信息";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getDBLogger();
//        redirect:/requisitionUpdate?id="+requisition_id
        return "redirect:/requisitionUpdate?id="+requisition_id;
    }

    @RequestMapping(value = "/requisitionUpdate")
    public String RequisitionUpdateControl(@RequestParam("id") Integer id,
                                           Model model){

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        GrantedAuthority[] authorities = userDetails.getAuthorities();
// UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();

        List<Member> memberList = memberRespository.findAll();
        Requisition requisition = requisitionRespository.findOne(id);
        Authority authority = authorityRespository.getAuthorityByRequisitionId(id);
        String tempUsername = SecurityContextHolder.getContext().getAuthentication().getName();//获取当前登录的用户信息
        Member memberRole = memberRespository.findMemberByMember_username(tempUsername);

        model.addAttribute("memberRole",memberRole);
        model.addAttribute("authority",authority);
        model.addAttribute("requisition",requisition);
        model.addAttribute("memberList",memberList);

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了申请单编号为["+requisition.getRequisition_number()+"]的更新信息页面";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "requisition_update";
    }

    @RequestMapping(value = "/requisitionShenpi")  //存入审批完成状态
    public String RequisitionUpdateControl(@RequestParam("requisition_id") Integer requisition_id,
                                           @RequestParam("shenfen") Integer shenfen,
                                           @RequestParam("role") Integer role,
                                           Model model){

        Requisition requisition = requisitionRespository.findOne(requisition_id);
        int ssss = requisition.getRequisition_state();
        requisition.setRequisition_state(requisition.getRequisition_state() +1);
        requisitionRespository.save(requisition);
        if (ssss == 2){
            // 一级审查员
            requisitionRespository.updateRequisitionFirstExamByShenfenAndId("审批完成", requisition_id);
            requisitionRespository.updateRequisitionSecondExamByShenfenAndId("待审核",requisition_id);
        }else if (ssss==3){
            requisitionRespository.updateRequisitionSecondExamByShenfenAndId("审批完成",requisition_id);
            requisitionRespository.updateRequisitionThirdExamByShenfenAndId("待审核",requisition_id);
        }else if ((ssss==4)){
            requisitionRespository.updateRequisitionThirdExamByShenfenAndId("审批完成",requisition_id);
        }


        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "改变了申请编号为["+requisition.getRequisition_number()+"]的审批状态";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getDBLogger();

        return "redirect:/requisition";
    }

    @RequestMapping(value = "/requisitionBohui")  //存入审批完成状态
    public String RequisitionUpdateBohuiControl(@RequestParam("requisition_id") Integer requisition_id,
                                                @RequestParam("shenfen") Integer shenfen,
                                                @RequestParam("role") Integer role,
                                                Model model){

        Requisition requisition = requisitionRespository.findOne(requisition_id);
        int ssss = requisition.getRequisition_state();
        requisition.setRequisition_state(requisition.getRequisition_state() -1);
        requisitionRespository.save(requisition);
        if (ssss==2){
            requisitionRespository.updateRequisitionFirstExamByShenfenAndId("驳回",requisition_id);
        }else if (ssss==3){
            requisitionRespository.updateRequisitionSecondExamByShenfenAndId("驳回",requisition_id);
            requisitionRespository.updateRequisitionFirstExamByShenfenAndId("待审核",requisition_id);
        }else if (ssss==4){
            requisitionRespository.updateRequisitionThirdExamByShenfenAndId("驳回",requisition_id);
            requisitionRespository.updateRequisitionSecondExamByShenfenAndId("待审核",requisition_id);
        }

        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "改变了申请编号为["+requisition.getRequisition_number()+"]的审批状态";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getDBLogger();

        return "redirect:/requisition";
    }


    @RequestMapping(value = "/requisitiontest")
    public String requisitiontest(){

        return "requisition_addbeifen";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/requisitionAdd")
    public String addRequisition(){

        return "requisition_add";
    }

    /**
     * 后端接收ajax请求的接口，
     * @param uploadFile 前端对应的formdata 键
     * @throws IllegalStateException  不合法状态的异常抛出
     * @throws IOException IO流处理异常的抛出
     */
    @RequestMapping(value = "/multiImport", method = RequestMethod.POST)
    @ResponseBody
    public void multiImport(@RequestParam("uploadFile") MultipartFile[] uploadFile) throws IllegalStateException, IOException{

        // 构建上传文件的存放路径
        String destination = "G:\\test";

       // Map<String, Object> result = new HashMap<String, Object>();
        //System.out.println(uploadFile.length);
        for (MultipartFile multipartFile:uploadFile) {

            //通过getOriginalFilename方法取得原始提交路径名称，例如G:\alternatiff-ax-w64-2.0.8\99999.tif
            System.out.println("文件"+multipartFile.getOriginalFilename());

            //调用写的工具类内的保存多个文件的方法
            SaveUploadFile saveUploadFile = new SaveUploadFile();
            saveUploadFile.saveFile(multipartFile, destination);

        }


        //return result;
    }



    @GetMapping("/originalPicture")
    public String getOriginalPicture(@RequestParam("id") Integer id,
                          Model model){


        List<Retangle> retangleList =  retangleRespository.selectRetangleListByPictureId(id);  //根据影像图id取出相关的所有框存入List
        model.addAttribute("retangleList",retangleList);


        Picture picture = pictureRespository.findOne(id);
        model.addAttribute("picture",picture);
        // model.addAttribute("authority",authority);
        //根据网页端操作，在log日志中存入自定义的信息
        String operationLog = "查看了影像图编号为["+picture.getPicture_number()+"]的详细信息审核页面";
        logSaveService.LogInfoSave(operationLog);

        Logger log = LogUtils.getBussinessLogger();

        return "tif";
    }




}

package com.industrialscansystem.test;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Mapper.PictureMapper;
import com.industrialscansystem.Mapper.RequisitionMapper;
import com.industrialscansystem.MybatisBean.Requisition;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RequisitionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testDemo {

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    RequisitionMapper requisitionMapper;

    @Autowired
    RequisitionRespository requisitionRespository;
    @Autowired
    PictureRespository pictureRespository;

    @RequestMapping(value = "/tt")
    public List<Picture> testdd(){
       //String aa = " picture_requisition_id = 59";

        return pictureMapper.getPictureListByDistinctTestmethod();
    }

    @RequestMapping(value = "/td")
    public List<Requisition> td(){
//            Requisition requisition = requisitionMapper.getPictures(59);
        List<Requisition> requisitions = requisitionMapper.getRequisitionListPictures();
        int a = 1;
//        List<Picture> aa = pictureMapper.findPicturesByRequisitionId(59);
//        requisitionRespository.getPictures(59);
        return requisitions;
    }



}

package com.industrialscansystem.respository.Service;

import com.industrialscansystem.Bean.Requisition;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.RequisitionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionService {

    @Autowired
    RequisitionRespository requisitionRespository;

    @Autowired
    PictureRespository pictureRespository;

    public int sumPictureNumByRequisitionId(List<Requisition> requisitions){
        int sum = 0;
        for(int i = 0; i < requisitions.size(); i++){
            int tempSun = pictureRespository.countRequisitionPictureNumber(requisitions.get(i).getRequisition_id());
            sum = tempSun + sum;
        }

        return sum;
    }
}

package com.industrialscansystem.respository.Service;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Polygon;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.respository.PolygonRespository;
import com.industrialscansystem.Controller.util.ImageRotateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class PictureManageService {
    public static String ops_state = "false";
    private static final int class_num = 5;
    @Autowired
    private PolygonRespository polygonRespository;
    @Autowired
    private PictureRespository pictureRespository;

    public static String RotateImg(String path,String type) throws IOException {
        String res = "False";
        if(type.equals("horizatal")){
            res = ImageRotateUtil.HorizatalFlipImg(path);
        }else if (type.equals("vertical")){
            res = ImageRotateUtil.VerticalFlipImg(path);
        }

        return res;
    }
    /**
     * 向算法后端传递 确认标注无误的图片和标注信息
     */
    public String upload_Verified_Picture(int picture_id) throws InterruptedException {
        List<Polygon> polygonList = polygonRespository.selectPolygonListByPictureId(picture_id);
        Picture picture = pictureRespository.getPictureById(picture_id);
        String LabelInfo_str = get_LabelInfo_str(polygonList);
        PictureUploadThread pictureUploadThread = new PictureUploadThread(picture,LabelInfo_str);
        pictureUploadThread.start();
        pictureUploadThread.join();
        return ops_state;
    }

    public String get_LabelInfo_str(List<Polygon> polygonList){
        String LabelInfo_str = "";
        String[] temp_array = new String[class_num];
        for(int i=0;i<temp_array.length;i++){
            temp_array[i]="";
        }
        for(Polygon polygon:polygonList){
            int index = polygon.getPolygon_damage_type();
            if(index<class_num){
                temp_array[index] = temp_array[index] + polygon.getPolygon_pt();
            }
        }
        LabelInfo_str = String.join("?",temp_array);
        return LabelInfo_str;
    }
}

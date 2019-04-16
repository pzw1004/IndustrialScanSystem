package com.industrialscansystem.Controller;


import com.industrialscansystem.Bean.Picture;

import com.industrialscansystem.respository.PictureRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
public class IndexController {


    @Autowired
    private PictureRespository pictureRespository;




//    @RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
//    public Object getPictureList(){
//        List<Picture> pictureList = pictureRespository.findAll();
//
//        return pictureList;
//    }
//
//    @PostMapping (value = "/test_index") //后端分页算法：通过接收前端发送数据链接以及参数page与key，链接数据库得到数据查询结果并返回到前端ajax
//    public Object getPictureListUp6(@RequestParam("page") Integer page,
//                                    @RequestParam("key")String key){
//        Integer num = 6;
//        Integer tiao = (page-1)*num;
//        List<Picture> pictureList = pictureRespository.getByPicture_AIresultBefore6(key,tiao,num);
//
//        return  pictureList;
//    }
//
//    @PostMapping(value = "/zys")
//    public  Integer getPictureListzys(@RequestParam("key")String key){
//            Integer num = 6;
       //     Integer zys = pictureRespository.getByPicture_AIresultBeforezys(key);
//            return zys;
//    }
//
//
//
//
//    @RequestMapping(value = "/test")
//    @ResponseBody
//    public Object Picture(HttpServletRequest request){
//        String name = request.getParameter("picture_number");
//        int id = Integer.parseInt(request.getParameter("picture_id"));
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("success",true);
//        map.put("name",name);
//        map.put("id",id);
//        map.put("message","接收数据测试成功!");
//        return map;
//    }
//
//    @RequestMapping(value = "/PictureDelete")
//    public String deletePicture(@RequestParam("id") Integer picture_id ){
//        pictureRespository.delete(picture_id);
//
//        return "delete over";
//    }
//
//
//   // @RequestMapping("/getjson")
//   // public
//
//    @PostMapping(value = "connect_test")
//    public String contest(@RequestParam("username") String username,
//                          @RequestParam("password") String password){
//
//        String conN = username;
//        String conP = password;
//        return "connect over";
//    }

}

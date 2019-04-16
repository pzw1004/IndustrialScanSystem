package com.industrialscansystem.Controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RequisitionRestContorller {


//    @RequestMapping(value = "/multiImport", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> multiImport(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        System.out.println(uploadFile.length);
//        for (MultipartFile multipartFile:uploadFile) {
//            System.out.println("文件"+multipartFile.getOriginalFilename());
//        }
//        return result;
//    }


}

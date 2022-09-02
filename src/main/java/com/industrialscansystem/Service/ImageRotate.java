package com.industrialscansystem.Service;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.util.EnvironmentPath;
import com.industrialscansystem.util.ImageRotateUtil;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class ImageRotate {

    public String horizontaFilpPicture(Picture picture) throws Exception {
        String path = picture.getPicture_dir();
        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        File f = new File(tomcatPath + path);
        ImageRotateUtil.imageMisro(f,0);
        return "success";
    }

    public String verticalFilpPicture(Picture picture) throws Exception {
        String path = picture.getPicture_dir();
        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        File f = new File(tomcatPath + path);
        ImageRotateUtil.imageMisro(f,1);
        return "success";
    }

}

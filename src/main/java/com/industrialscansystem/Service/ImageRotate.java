package com.industrialscansystem.Service;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.util.EnvironmentPath;
import com.industrialscansystem.util.ImageRotateUtil;

import java.io.*;

public class ImageRotate {

    public String horizontaFilpPicture(Picture picture) throws Exception {
        String path = picture.getPicture_dir();
        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        File f  = new File(tomcatPath + path);
        f = ImageRotateUtil.rotateImage180(f);
        InputStream in = new FileInputStream(f);
        OutputStream o = new FileOutputStream(tomcatPath + path);
        return "success";
    }
}

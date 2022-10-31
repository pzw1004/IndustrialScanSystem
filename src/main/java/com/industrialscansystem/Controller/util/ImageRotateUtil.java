package com.industrialscansystem.Controller.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
/**
 * Author Mythos_Q
 *
 * Time 2011-07-20
 *
 * Description 图片旋转、翻转处理
 */
public class ImageRotateUtil
{

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return " ";}

    public static String HorizatalFlipImg(String path) throws IOException {
        //LoadDlls.loadOpenCVdll();
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File f = new File(path);
        BufferedImage image = null;
        try {
            ImgUtil.flip(ImageIO.read(f), f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static String VerticalFlipImg(String path) throws IOException {
        //LoadDlls.loadOpenCVdll();
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File f = new File(path);
        BufferedImage image = null;
        try {
            ImgUtil.rotate(ImageIO.read(f),180,f);  //先旋转180
            ImgUtil.flip(ImageIO.read(f), f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
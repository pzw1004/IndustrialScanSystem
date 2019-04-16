package com.industrialscansystem.util;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;

import net.coobird.thumbnailator.Thumbnails;
import org.dom4j.io.XMLWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TifImageTransUtil {

    public String convertImage(String imageFile, double zoom) throws Exception{
        try{
            int iIndex = imageFile.lastIndexOf(".");
            String jpgFile = imageFile.substring(0, iIndex)+".jpg";

            if(new File (imageFile).exists())
            {
                FileOutputStream out = new FileOutputStream(jpgFile);
                ImageDecoder decoder = ImageCodec.createImageDecoder("tiff",new File(imageFile),null);
                ImageEncoder encoder = ImageCodec.createImageEncoder("png",out,null);
                encoder.encode( decoder.decodeAsRenderedImage() );
                decoder = null;
                out.close();
            }

            File file = new File(imageFile);
            if(file.exists())
            {
                file.delete();
            }

            return jpgFile;

        }catch(Exception ex){
            throw new Exception(ex.getMessage());

        }
    }

    private String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

    public String generatePath(){

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String picType = ".jpg";
        TifImageTransUtil tifImageTransUtil = new TifImageTransUtil();
        String randomNum1 = tifImageTransUtil.generateWord();
        String randomNum2 = tifImageTransUtil.generateWord();
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\convertImages\\" + randomNum1+sdf.format(dt)+randomNum2+picType;


        return path;
    }

    public static void main(String[] args) throws Exception{
//        String imageFile = "G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage16.tif";
//       TifImageTransUtil cTif = new TifImageTransUtil();
//       String path = cTif.generatePath();
//        cTif.TifConver(imageFile,path);
//
//
//        String imageFileJpg = imageFile.replace(".TIF", ".jpg");  //将路径后缀名.tif或者TIF的替换成后缀名为jpg格式的，给新string变量imageFileJpg存储
//        imageFileJpg = imageFileJpg.replace(".tif", ".jpg");
//        File picture=new File(imageFileJpg);
//        BufferedImage sourceImg=ImageIO.read(new FileInputStream(picture));
//
//        sourceImg.getWidth();
//        sourceImg.getHeight();
//       // System.out.println(String.format("%.1f",picture.length()/1024.0));// 源图大小
//        System.out.println(sourceImg.getWidth());
//        System.out.println(sourceImg.getHeight());



        double a = Math.pow( 3,  3);
        System.out.println(a);

    }

    public  void TifConver(String imageFile,String path)throws Exception{
        //String imageFile = "G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage8.tif";
        String imageFileJpg = imageFile.replace(".TIF", ".jpg");  //将路径后缀名.tif或者TIF的替换成后缀名为jpg格式的，给新string变量imageFileJpg存储
        imageFileJpg = imageFileJpg.replace(".tif", ".jpg");
        double zoom = 9.3;
        TifImageTransUtil cTif = new TifImageTransUtil();
        cTif.convertImage(imageFile, zoom);
        //压缩图片

//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//        String picType = ".jpg";
//        TifImageTransUtil tifImageTransUtil = new TifImageTransUtil();
//        String randomNum1 = tifImageTransUtil.generateWord();
//        String randomNum2 = tifImageTransUtil.generateWord();
       // String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\convertImages\\" + randomNum1+sdf.format(dt)+randomNum2+picType;

        Thumbnails.of(imageFileJpg).scale(0.75f).toFile(path);
       //String tempFile = "G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage8.jpg";
        File file = new File(imageFileJpg);
       // file.delete();     //删除图片
    }

}

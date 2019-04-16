package com.industrialscansystem.util;

import java.io.File;
import java.io.FileOutputStream;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;

import net.coobird.thumbnailator.Thumbnails;
public class ImageTransUtil {

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

    public static void main(String[] args) throws Exception{
        String imageFile = "G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage8.TIF";
        double zoom = 9.3;
        ImageTransUtil cTif = new ImageTransUtil();
        cTif.convertImage(imageFile, zoom);
        //压缩图片
        Thumbnails.of("G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage8.jpg").scale(0.75f).toFile("/src/main/resources/static/convertImages/7777.jpg");
       String tempFile = "G:\\vs2013_workspace\\工业探伤扫描结果\\工业探伤扫描结果\\VIDAR\\VIDARImage8.jpg";
        File file = new File(tempFile);
        file.delete();
    }

}

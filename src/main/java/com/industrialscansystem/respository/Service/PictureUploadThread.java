package com.industrialscansystem.respository.Service;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Controller.util.EnvironmentPath;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PictureUploadThread extends Thread{
    Picture picture;
    String label_info;
    public PictureUploadThread(Picture picture, String label_info) {
        this.picture = picture;
        this.label_info = label_info;
    }

    /**
     * 上传图片原图
     * 上传标注信息字符串 字段名：label_info
     */
    @Override
    public void run() {
        PictureManageService.ops_state = "false";
        String res = null;
        String aiIP = EnvironmentPath.getInstance().getAiIP();
        String sURL= aiIP+"/savePic"; //TODO 算法后端接口
        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        String path = tomcatPath + picture.getPicture_dir();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(sURL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("label_info", this.label_info, ContentType.TEXT_PLAIN);
        // 把文件加到HTTP的post请求中
        File f = new File(path);
        try {
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null==response){
            PictureManageService.ops_state = "false";
        }
        else{
            HttpEntity responseEntity = response.getEntity();
            String sResponse= null;

            try {
                sResponse = EntityUtils.toString(responseEntity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
//
            if(sResponse.equals("success")){
                PictureManageService.ops_state = "success";
            }
        }


    }
}

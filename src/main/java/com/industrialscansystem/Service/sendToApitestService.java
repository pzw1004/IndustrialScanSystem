package com.industrialscansystem.Service;

import com.industrialscansystem.Bean.Polygon;
import com.industrialscansystem.MybatisBean.Picture;
import com.industrialscansystem.util.EnvironmentPath;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.util.List;

public class sendToApitestService {
//    public String sendTo(Picture p, List<Polygon> polygons){
////        String aiIP = EnvironmentPath.getInstance().getAiIP();
////        String sURL= aiIP+"/"; //TODO 写入apitest接口
////        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
////
////        CloseableHttpClient httpClient = HttpClients.createDefault();
////        HttpPost uploadFile = new HttpPost(sURL);
////        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
////        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
////        // 把文件加到HTTP的post请求中
////        File f = new File(path);
////        return "success";
//    }
}

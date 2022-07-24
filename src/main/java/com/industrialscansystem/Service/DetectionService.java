package com.industrialscansystem.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.respository.PictureRespository;
import com.industrialscansystem.util.EnvironmentPath;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Author 宋宗垚
 * @Date 2019/2/25 16:03
 * @Description 用于处理检测的服务类
 */
@Service
public class DetectionService {

    @Autowired
    PictureRespository pictureRespository;


    /**
     * 接收Picture的对象，然后将该对象作为参数发送给远程的服务，接收计算结果，转化为JSONObject返回
     * @param picture
     * @return
     */
    public JSONObject detectOneImage(Picture picture){
        String aiIP = EnvironmentPath.getInstance().getAiIP();
        String sURL= aiIP+"/detectuploadfile_v2";
        //TODO 修改数据存储路径之后需要修改
       // String path = System.getProperty("user.dir") + "/src/main/resources/static/" + picture.getPicture_dir();
        String path = picture.getPicture_dir();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(sURL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
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
            return null;
        }
        HttpEntity responseEntity = response.getEntity();
        String sResponse= null;

        try {
            sResponse = EntityUtils.toString(responseEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("=====================333333========================");
//        System.out.println(sResponse);

        JSONObject jsonObject = JSON.parseObject(sResponse);

        return jsonObject;
    }

    public String detectOneImage_v2(Picture picture){
        String res = null;
        String aiIP = EnvironmentPath.getInstance().getAiIP();
        String sURL= aiIP+"/detectuploadfile_v2";
        //TODO 修改数据存储路径之后需要修改
        // String path = System.getProperty("user.dir") + "/src/main/resources/static/" + picture.getPicture_dir();
        // wpz:加上tomcatpath，否则系统找不到
        String tomcatPath = EnvironmentPath.getInstance().getTomcatPath();
        String path = tomcatPath + picture.getPicture_dir();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(sURL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
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
            return null;
        }
        HttpEntity responseEntity = response.getEntity();
        String sResponse= null;

        try {
            sResponse = EntityUtils.toString(responseEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
//

        res = sResponse;
        return res;
    }


//    /**
//     *接收picture对象，并执行python脚本，生成G-CLAHE结果，存储到指定的路径下，并将其信息与pinture相关联
//     * @return 返回的
//     */
//    public Message generateGclaheData(Picture picture){
//        Message message = new Message();
//
//        String datPath = System.getProperty("user.dir") + "/src/main/resources/static/" + picture.getPicture_dir();
//        String targetPath = "";
//        // 将数据路径以及处理之后的目标路径传递给python文件
//        String args = "H:\\Anaconda\\Anaconda3\\envs\\PyTorch_V1_0\\python.exe H:\\LabelProject\\code\\WeldDection\\Dection\\dection.py" +
//                " " + datPath + " " + targetPath;
//        File file = new File(datPath);
//        if(!file.exists()){
//            // 如果文件不存在
//            message.setSuccess(false);
//            message.setInfo("该文件不存在");
//            return message;
//        }
//        long startTime =  System.currentTimeMillis();
//        System.out.println("---------------------------------------------generateGclaheData开始执行脚本---------------------------------------------");
//        Runtime mt =Runtime.getRuntime();
//        try {
//            Process pr = mt.exec(args);
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                if(line.startsWith("targetPath:")){
//                    String[] splited = line.split(":");
//                    String result = splited[1];
//                    picture.setHasGclaheData(true);
//                    picture.setGclaheDataPath(result);
//                }
//            }
//            in.close();
//            pr.waitFor();
//            pr.destroy();
//        }catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        }
//        long endTime =  System.currentTimeMillis();
//        long usedTime = (endTime-startTime)/1000;
//        System.out.println("---------------------------------------------generateGclaheData执行结束---------------------------------------------");
//        System.out.println("---------------------generateGclaheData用时--------------"+usedTime+"---------------------------------------------");
//
//        return message;
//
//    }
//
//    /**
//     * 接收picture对象，并执行python脚本，生成clustering结果，存储到指定的路径下，并将其信息与pinture相关联
//     */
//    public Message generateClusterData(Picture picture){
//        Message message = new Message();
//
//        String datPath = System.getProperty("user.dir") + "/src/main/resources/static/" + picture.getPicture_dir();
//        String targetPath = "";
//        // 将数据路径以及处理之后的目标路径传递给python文件
//// 将数据路径以及处理之后的目标路径传递给python文件
//        String args = "H:\\Anaconda\\Anaconda3\\envs\\PyTorch_V1_0\\python.exe H:\\LabelProject\\code\\WeldDection\\Dection\\dection.py" +
//                " " + datPath + " " + targetPath;
//        File file = new File(datPath);
//        if(!file.exists()){
//            // 如果文件不存在
//            message.setSuccess(false);
//            message.setInfo("该文件不存在");
//            return message;
//        }
//        long startTime =  System.currentTimeMillis();
//        System.out.println("---------------------------------------------generateGclaheData开始执行脚本---------------------------------------------");
//        Runtime mt =Runtime.getRuntime();
//        try {
//            Process pr = mt.exec(args);
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                if(line.startsWith(":")){
//                    String[] splited = line.split(":");
//                    String result = splited[1];
//                    picture.setHasGclaheData(true);
//                    picture.setGclaheDataPath(result);
//                }
//            }
//            in.close();
//            pr.waitFor();
//            pr.destroy();
//        }catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        }
//        long endTime =  System.currentTimeMillis();
//        long usedTime = (endTime-startTime)/1000;
//        System.out.println("---------------------------------------------generateGclaheData执行结束---------------------------------------------");
//        System.out.println("---------------------generateGclaheData用时--------------"+usedTime+"---------------------------------------------");
//
//
//        return message;
//    }

//    private void excutePython(String args,Message message){
        // 执行python脚本
//        long startTime =  System.currentTimeMillis();
//        System.out.println("---------------------------------------------开始执行脚本---------------------------------------------");
//        Runtime mt =Runtime.getRuntime();
//        try {
//            Process pr = mt.exec(args);
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                if(line.startsWith("targetPath:")){
//                    String[] splited = line.split(":");
//                    String result = splited[1];
//                    //  damage location x: ', x2 ,' y: ',y2
////                    int x = Integer.parseInt(splited[4]);
////                    int y = Integer.parseInt(splited[8]);
////                    System.out.println(x+" "+y);
////                    DamageData dd = new DamageData(x,y,"Algorithm");
////                    message.getDamageDataList().add(dd);
//                }
////                ddd = ddd.concat(line);
////                System.out.println(line);
//            }
//            in.close();
//            pr.waitFor();
//            pr.destroy();
//        }catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            message.setInfo(e.getMessage());
//            message.setSuccess(false);
//        }
//        long endTime =  System.currentTimeMillis();
//        long usedTime = (endTime-startTime)/1000;
//        System.out.println("---------------------------------------------脚本执行结束---------------------------------------------");
//        System.out.println("---------------------用时--------------"+usedTime+"---------------------------------------------");
//    }

}

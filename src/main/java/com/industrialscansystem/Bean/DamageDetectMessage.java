package com.industrialscansystem.Bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 宋宗垚
 * @Date 2019/2/25 16:46
 * @Description 用于将算法检测的json串转化为对象的类
 */
public class DamageDetectMessage {
    // 用于显示状态的变量
    private String status ;
    //消息的详细信息
    private String info;
    //用于记录消息时间的时间戳
    private Timestamp timestamp;
    private List<Retangle> damageDataList;

    public DamageDetectMessage(){
        Date date = new Date();
        this.timestamp  = new Timestamp(date.getTime());
        this.status = "SUCCESS";
        this.damageDataList = new ArrayList<>();
    }

    /**
     * 接收jsonobject，解析其中的数据并将解析结果转化为对应的对象/数组。
     * @param jsonObject
     */
    public DamageDetectMessage(JSONObject jsonObject, int picture_id){
        this.status = jsonObject.getString("status");
        this.info = jsonObject.getString("info");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

            //String ss = jsonObject.getString("timestamp");
            this.timestamp = new Timestamp(System.currentTimeMillis());
//            this.timestamp = new Timestamp(format.parse(jsonObject.getString("timestamp")).getTime());


        JSONArray damageDataListJsonArray = jsonObject.getJSONArray("damageDataList");
        String jsonString=JSONObject.toJSONString(damageDataListJsonArray, SerializerFeature.WriteClassName);//将array数组转换成字符串
        List<JSONObject> damageJsonList = JSONObject.parseArray(jsonString, JSONObject.class);//把字符串转换成集合
        this.damageDataList = new ArrayList<>();
        if (damageJsonList!=null){
            for(int i = 0;i<damageJsonList.size();i++){
                Retangle retangle = new Retangle(damageJsonList.get(i),picture_id);
                this.damageDataList.add(retangle);
            }
        }




    }

    public void addOneDamageData(Retangle data){
        this.damageDataList.add(data);
    }
    public List<Retangle> getDamageDataList(){
        return damageDataList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean b) {
        if (b){
            this.status = "SUCCESS";
        }else {
            this.status = "FAILED";
        }

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = timestamp;
//    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 将message类中的数据转化为Json格式的数据用于返回给调用者
     * @return
     */
    public JSONObject getJsonData(){
        JSONObject result = new JSONObject();

        result.put("timestamp",this.timestamp.toString());
        result.put("status",this.status);
        result.put("info",this.info);
        result.put("damageDataList",this.damageDataList);
        return result;
    }


}

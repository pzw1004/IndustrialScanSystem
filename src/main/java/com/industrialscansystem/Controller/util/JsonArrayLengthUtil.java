package com.industrialscansystem.Controller.util;

import com.alibaba.fastjson.JSONArray;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class JsonArrayLengthUtil {

    public static int getLength(JSONArray jsonArray){ //调用次方法直接获取本地ip地址
        int length = 0;
        while(true){
            if(jsonArray.getString(length) != null)
                length++;
            else
                break;
        }
        return length;
    }
}

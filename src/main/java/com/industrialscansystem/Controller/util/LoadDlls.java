package com.industrialscansystem.Controller.util;

import java.net.URL;

public class LoadDlls {
    public static void loadOpenCVdll(){
        System.setProperty("java.awt.headless","false");
        URL url = ClassLoader.getSystemResource("D:\\work\\back1\\src\\main\\resources\\dlls\\opencv_java320.dll");
        System.load(url.getPath());
    }
}

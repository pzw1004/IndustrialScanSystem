package com.industrialscansystem.Service;

import com.industrialscansystem.Bean.Polygon;
import com.industrialscansystem.MybatisBean.Picture;

import java.util.List;

public class sendToApitestThread extends Thread{
    private Picture picture;
    private  List<Polygon> polygons;
    public sendToApitestThread(Picture picture, List<Polygon> polygons){
        this.picture = picture;
        this.polygons = polygons;
    }

    @Override
    public void run() {
        super.run();
    }
}

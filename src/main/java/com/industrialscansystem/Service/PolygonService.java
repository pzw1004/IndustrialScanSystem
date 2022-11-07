package com.industrialscansystem.Service;

import java.util.Arrays;

import com.industrialscansystem.Bean.Picture;
import com.industrialscansystem.Bean.Polygon;
import com.industrialscansystem.respository.PolygonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolygonService {
    @Autowired
    private PolygonRespository polygonRespository;
    private final int shift_y = 100;

    /**
     * 解决前端数据坐标偏移
     *
     * @param polygons
     * @return
     */
    public List<Polygon> makeShift(List<Polygon> polygons) {
        String shift_points = "";
        for (Polygon p : polygons) {
            if (p.getPolygon_text_y() != null) {
                p.setPolygon_text_y(p.getPolygon_text_y() + shift_y);
            }
            String[] part1 = p.getPolygon_pt().split(" ");
            for (int i = 0; i < part1.length; i++) {
                String a[] = part1[i].split(",");
                a[1] = "" + (Integer.parseInt(a[1]) + shift_y);
                part1[i] = a[0] + "," + a[1];
                shift_points = shift_points + part1[i] + " ";
            }
            p.setPolygon_pt(shift_points);
            shift_points = "";
        }
        return polygons;
    }
    public void getPolygon_flaw_length(List<Polygon> polygonList, Integer picture_real_height){
        for(Polygon p : polygonList){
           float len =  getflawlength(parsePolygon_pt(p.getPolygon_pt()),picture_real_height);
           p.setPolygon_flaw_length(len);
           polygonRespository.save(p);
        }

    }
    public void getPolygon_flaw_position_xy(int picture_id, String crossxy, Picture picture) {
        List<Polygon> polygonList = polygonRespository.selectPolygonListByPictureId(picture_id);
        int[] center = new int[2];
        int[] cross_pt = new int[2];
        int real_width = picture.getPicture_real_width();
        cross_pt[0] = Integer.parseInt(crossxy.split(",")[0]);
        cross_pt[1] = Integer.parseInt(crossxy.split(",")[1]) -100;
        for (Polygon p : polygonList) {
            center =getCenter_pt(parsePolygon_pt(p.getPolygon_pt()));
//            float a = (float) (center[0]-cross_pt[0])/1000 * (float)real_width;
            int px = Math.round((float)(center[0]-cross_pt[0])/1000 * (float)real_width);
            int py = Math.round(-(float)(center[1]-cross_pt[1])/300 * (float)100);
            p.setPolygon_flaw_position_x(px);
            p.setPolygon_flaw_position_y((py));
            polygonRespository.save(p);
        }

    }

    public int[][] parsePolygon_pt(String polygon_pt) {
        String pt[] = polygon_pt.split(" ");
        int[][] xy = new int[2][];
        int len = pt.length;
        int[] x = new int[len];
        int[] y = new int[len];
        for (int i = 0; i < pt.length; i++) {
            x[i] = Integer.parseInt(pt[i].split(",")[0]);
            y[i] = Integer.parseInt(pt[i].split(",")[1]);
        }
        xy[0] = x;
        xy[1] = y;
        return xy;
    }

    public int[] getCenter_pt(int[][] xy) {
        int []c = new int[2];
        int max_x = Arrays.stream(xy[0]).max().getAsInt();
        int min_x = Arrays.stream(xy[0]).min().getAsInt();
        int max_y = Arrays.stream(xy[1]).max().getAsInt();
        int min_y = Arrays.stream(xy[1]).min().getAsInt();
        int center_x = (max_x+min_x) / 2;
        int center_y = (max_y+min_y) / 2;
        c[0]=  center_x;
        c[1]= center_y;
        return c;
    }

    public float getflawlength(int[][] xy,int width){
        int []c = new int[2];
        int max_x = Arrays.stream(xy[0]).max().getAsInt();
        int min_x = Arrays.stream(xy[0]).min().getAsInt();
        int max_y = Arrays.stream(xy[1]).max().getAsInt();
        int min_y = Arrays.stream(xy[1]).min().getAsInt();
        float d_x = max_x -min_x;
        float d_y = max_y -min_y;
        float dd_x = (d_x / 1000) * width;
        float dd_y = (d_y/300) * 100;
        float d = dd_x>dd_y ? dd_x:dd_y;
        d = Math.round(d*100)/100f;
        return d;
    }
}

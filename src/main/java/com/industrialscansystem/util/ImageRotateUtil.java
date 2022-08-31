package com.industrialscansystem.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Author Mythos_Q
 *
 * Time 2011-07-20
 *
 * Description 图片旋转、翻转处理
 */
public class ImageRotateUtil
{
    static int Up_Down_Reverse = 0;
    static int Left_Right_Reverse = 1;
    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage
     *            目标图像
     * @param degree
     *            旋转角度
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,final int degree){
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2 + (w>h?(w-h)/2:(h-w)/2));
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 旋转图片为指定角度
     *
     * @param file
     *            目标图像
     * @param degree
     *            旋转角度(90,180,270)
     * @return
     */
    public static File rotateImage(File file,int degree) throws Exception{
        if(degree==90) return rotateImage90(file);
        if(degree==180) return rotateImage180(file);
        if(degree==270) return rotateImage270(file);
        return null;
    }
    public static Image rotateImage(Image Image,int degree)
    {
        if(degree==90)
            return rotateImage90(Image);
        if(degree==180)
            return rotateImage180(Image);
        if(degree==270)
            return rotateImage270(Image);
        return null;
    }

    private static Image rotateImage90(Image image)
    {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d =
                (img = new BufferedImage(h, w, type) ).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w-h)/2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return BufferedImageToImage(img);

    }

    //左转90度
    public static File rotateImage90(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d =
                (img = new BufferedImage(h, w, type) ).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w-h)/2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }

    //右转90度
    public static File rotateImage270(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(90), w / 2 - (w-h)/2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }

    public static Image rotateImage270(Image image)
    {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(90), w / 2 - (w-h)/2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return BufferedImageToImage(img);
    }
    //对转
    public static File rotateImage180(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(180), w / 2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }
    public static Image rotateImage180(Image image)
    {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(180), w / 2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return BufferedImageToImage(img);
    }
    /***
     * 图片镜像处理
     * @param file
     * @param FX 0 为上下反转 1 为左右反转
     * @return
     */
    public static void imageMisro(File file,int FX)
    {
        try
        {
            BufferedImage bufferedimage = ImageIO.read(file);
            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();

            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            if (FX == 0) {
                for (int i = 0, a = h - 1; i < h; i++, a--) {
                    for (int j = 0; j < w; j++) {
                        tmps[j][a] = datas[j][i];
                    }
                }
            } else if (FX == 1) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0, b = w - 1; j < w; j++, b--) {
                        tmps[b][i] = datas[j][i];
                    }
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }

            ImageIO.write(bufferedimage, "jpg", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * 镜像处理，输入image和方式，返回翻转的新image
     * type = 0 表示上下翻转，type = 1 表示左右翻转
     */
    public static Image imageMisro(Image image,int type )
    {
        try
        {
            //用到了自己写的方法
            BufferedImage  bufferedimage = ImageToBufferedImage(image);

            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();

            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            if (type == 0) {
                for (int i = 0, a = h - 1; i < h; i++, a--) {
                    for (int j = 0; j < w; j++) {
                        tmps[j][a] = datas[j][i];
                    }
                }
            } else if (type == 1) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0, b = w - 1; j < w; j++, b--) {
                        tmps[b][i] = datas[j][i];
                    }
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }


            Image newImage = (Image)bufferedimage;
            return newImage;
            //ImageIO.write(bufferedimage, "jpg", file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Image转换成BufferedImage
    public static BufferedImage ImageToBufferedImage( Image image )
    {
        BufferedImage  bufferedimage = new BufferedImage
                (image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics g =  bufferedimage.createGraphics();
        g.drawImage(image, 0, 0, null);	//这里，大家可能会有疑问，似乎没有对bufferedimage干啥
        g.dispose(); 					//但是是正确的，g调用drawImage就自动保存了
        return bufferedimage;
    }
    //BufferedImage 转换成Image类型
    public static Image BufferedImageToImage( BufferedImage b )
    {
        return (Image)b;
    }
}